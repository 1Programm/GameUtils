package com.gfi.ausbildung.jbecker.swingdbview.main;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.gfi.ausbildung.jbecker.swingdbview.tools.DBVLanguage;
import com.gfi.ausbildung.jbecker.swingdbview.tools.DBVTools;
import com.gfi.ausbildung.jbecker.swingdbview.tools.loader.LanguageLoader;
import com.gfi.ausbildung.jbecker.swingdbview.ui.MainWindow;

public class MainApplication
{

  private static final Logger log = Logger.getLogger(MainApplication.class);
  private static String[]     programmArgs;

  public static void main(String[] args)
  {
    log.info("Starting Main Application...");
    programmArgs = args;

    initToolsuit();

    MainWindow mainWin = MainWindow.getInstance();
    mainWin.addOnExitListener(MainApplication::onExit);
    mainWin.setVisible(true);
  }

  private static void initToolsuit()
  {
    log.debug("Initializing tools...");

    log.trace("Initializing main properties...");
    DBVTools.init();

    log.trace("Initializing active language...");
    try
    {
      String setLanguage = DBVTools.getProperty("lang");
      DBVLanguage lang = LanguageLoader.loadLanguage(setLanguage);
      DBVTools.lang.loadLanguage(lang);
    }
    catch (IOException e)
    {
      log.error(e.getMessage());
    }

  }

  public static void restartApplication()
  {
    log.info("Restarting Application...");

    log.debug("Closing Main Window...");
    MainWindow.getInstance().close();

    cleanUp();

    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - -\n");
    main(programmArgs);
  }

  private static void onExit()
  {
    cleanUp();

    log.info("Exiting application...");
    log.info("Bye - till next time :D");
  }

  private static void cleanUp()
  {
    log.info("Cleaning up...");
    DBVTools.cleanUp();

    log.info("Cleanup finished!");
  }

}
