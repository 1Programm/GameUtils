package com.gfi.ausbildung.jbecker.swingdbview.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * StringResourceList represents contents of a file separated by a {@code separator}. A StringResourceList can be loaded
 * from and saved to a file.
 * 
 * @author becker_j
 */
public class StringResourceList
{

  private static final Logger log        = Logger.getLogger(StringResourceList.class);

  private final File          file;
  private final String        separator;
  private final List<String>  properties = new ArrayList<>();

  public StringResourceList(File file, String separator, boolean forceCreate)
  {
    if (!(file.exists() && file.isFile()) && forceCreate)
    {
      String path = file.getAbsolutePath();
      log.debug("File does not exist at path: \"" + path + "\" - Force creating new File!");
      try
      {
        boolean success = file.createNewFile();

        if (!success)
        {
          log.warn("File \"" + file.getName() + "\" could not be created at path: \"" + path + "\"!");
        }
      }
      catch (IOException e)
      {
        String errMsg = "Error creating new PropertyListFile at path: \"" + path + "\": " + e.getMessage();
        log.error(errMsg);
        throw new IllegalStateException(errMsg);
      }
    }

    this.file = file;
    this.separator = separator;
    this.reload();
  }

  public void reload()
  {
    properties.clear();

    String allText = readConentFromFile();
    String[] allTextSplit = allText.split(separator);
    for (String split : allTextSplit)
    {
      properties.add(split);
    }
  }

  public void save()
  {
    String content = buildContentFromList();

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));)
    {
      bw.write(content);
    }
    catch (IOException e)
    {
      log.error("File is open or does not exist!");
      throw new IllegalStateException();
    }
  }

  public List<String> get()
  {
    return properties;
  }

  private String readConentFromFile()
  {
    StringBuilder sb = new StringBuilder();

    try (BufferedReader br = new BufferedReader(new FileReader(file)))
    {
      String line;
      while ((line = br.readLine()) != null)
      {
        sb.append(line);
      }
    }
    catch (FileNotFoundException e)
    {
      log.error("File \"" + file.getAbsolutePath()
          + "\" was not found. It could have been deleted after constructor was called.");
      throw new IllegalStateException();
    }
    catch (IOException e)
    {
      String errMsg = "IOException: " + e.getMessage();
      log.error(errMsg);
      throw new IllegalStateException(errMsg);
    }

    return sb.toString();
  }

  private String buildContentFromList()
  {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < properties.size(); i++)
    {
      if (i > 0)
      {
        sb.append(separator);
      }
      sb.append(properties.get(i));
    }

    return sb.toString();
  }

}
