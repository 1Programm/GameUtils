package com.gfi.ausbildung.jbecker.swingdbview.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class DBVLanguage
{

  private String                    name;
  private final Map<String, String> map = new HashMap<>();

  public DBVLanguage(String name)
  {
    this.name = name;
  }

  public void put(String key, String value)
  {
    this.map.put(key, value);
  }

  public void loadProperties(Properties p)
  {
    for (Entry<Object, Object> entry : p.entrySet())
    {
      map.put(entry.getKey().toString(), entry.getValue().toString());
    }
  }

  String getName()
  {
    return name;
  }

  Map<String, String> getMap()
  {
    return map;
  }

}
