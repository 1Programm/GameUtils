package com.gfi.ausbildung.jbecker.swingdbview.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.gfi.ausbildung.jbecker.swingdbview.main.Paths;

public class DBVTools
{

  private static final Logger              log            = Logger.getLogger(DBVTools.class);

  private static final Map<String, Object> propertyMap    = new HashMap<>();
  private static final Properties          mainProperties = new Properties();
  public static final DBVToolsLang         lang           = new DBVToolsLang();

  public static void init()
  {
    try
    {
      InputStream mainPropertiesInputStream = DBVTools.class.getResourceAsStream(Paths.PATH_MAIN_PROPERTIES);
      mainProperties.load(mainPropertiesInputStream);
      for (Entry<Object, Object> entry : mainProperties.entrySet())
      {
        propertyMap.put(entry.getKey().toString(), entry.getValue());
      }
    }
    catch (IOException e)
    {
      String errMsg = "IOException while loading main properties... \"" + e.getMessage() + "\".";
      log.error(errMsg);
      throw new IllegalStateException(errMsg);
    }
  }

  public static void cleanUp()
  {
    try
    {
      FileWriter fileWriter = new FileWriter(
          new File(DBVTools.class.getResource(Paths.PATH_MAIN_PROPERTIES).getFile()));
      mainProperties.store(fileWriter, null);

    }
    catch (IOException e)
    {
      String errMsg = "IOException while storing main properties... \"" + e.getMessage() + "\".";
      log.error(errMsg);
      throw new IllegalStateException(errMsg);
    }
  }

  public static String getProperty(String key)
  {
    return propertyMap.get(key).toString();
  }

  public static <T> T getProperty(String key, Class<T> cls)
  {
    Object property = propertyMap.get(key);

    if (property != null && cls.isInstance(property))
    {
      return cls.cast(property);
    }

    return null;
  }

  public static void putProperty(String key, Object value)
  {
    propertyMap.put(key, value);
    if (mainProperties.containsKey(key))
    {
      mainProperties.put(key, value);
    }
  }

}
