package com.gfi.ausbildung.jbecker.swingdbview.tools.loader;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.gfi.ausbildung.jbecker.swingdbview.tools.DBVLanguage;

public class LanguageLoader
{

  private static final Logger log = Logger.getLogger(LanguageLoader.class);

  public static DBVLanguage loadLanguage(String name) throws IOException
  {
    log.debug("Loading language file \"" + name + "\"...");

    Properties langProperties = new Properties();
    langProperties.load(LanguageLoader.class.getResourceAsStream("/lang/" + name + ".properties"));

    DBVLanguage languageMap = new DBVLanguage(name);
    languageMap.loadProperties(langProperties);

    return languageMap;
  }

}
