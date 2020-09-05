package com.gfi.ausbildung.jbecker.swingdbview.tools.loader;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import com.gfi.ausbildung.jbecker.swingdbview.main.Paths;

public class IconLoader
{

  private static final Logger log = Logger.getLogger(IconLoader.class);

  public static ImageIcon getIconResource(String name)
  {
    String path = Paths.PATH_RESOURCE_ICONS_FOLDER + name;
    try
    {
      URL inputUrl = ClassLoader.getSystemResource(path);
      if (inputUrl != null)
      {
        return new ImageIcon(ImageIO.read(inputUrl));
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    log.warn("Icon \"" + name + "\" could not be found at path: \"" + path + "\". Using ImageNotFound.png ...");

    try
    {

      return new ImageIcon(
          ImageIO.read(ClassLoader.getSystemResource(Paths.PATH_RESOURCE_ICONS_FOLDER + "ImageNotFound.png")));
    }
    catch (

    IOException e)
    {
      e.printStackTrace();
      throw new IllegalStateException();
    }
  }

}
