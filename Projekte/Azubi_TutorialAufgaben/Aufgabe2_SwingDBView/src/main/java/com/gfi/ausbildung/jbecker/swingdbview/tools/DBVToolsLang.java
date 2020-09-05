package com.gfi.ausbildung.jbecker.swingdbview.tools;

import org.apache.log4j.Logger;

public class DBVToolsLang
{

  private static final Logger log = Logger.getLogger(DBVToolsLang.class);

  private DBVLanguage         data;
  private boolean             fallback;

  DBVToolsLang()
  {
  }

  public void loadLanguage(DBVLanguage lang)
  {
    log.debug("Loading language \"" + lang.getName() + "\"...");
    this.data = lang;
  }

  public void allowFallback()
  {
    this.fallback = true;
  }

  public String getValue(String key)
  {
    if (data == null)
    {
      if (!fallback)
      {
        log.error("Language map not loaded!");
        throw new IllegalStateException();
      }
      else
      {
        log.warn("Language map not loaded! - Fallback: returning key value \"" + key + "\".");
        return key;
      }
    }

    String value = data.getMap().get(key);

    if (value == null)
    {
      if (!fallback)
      {
        log.error("Key \"" + key + "\" not specified in language \"" + data.getName() + "\"!");
        throw new IllegalArgumentException();
      }
      else
      {
        log.warn("Key \"" + key + "\" not specified in language \"" + data.getName()
            + "\"! - Fallback: returning key value \"" + key + "\".");
        return key;
      }
    }

    return data.getMap().get(key);
  }

}
