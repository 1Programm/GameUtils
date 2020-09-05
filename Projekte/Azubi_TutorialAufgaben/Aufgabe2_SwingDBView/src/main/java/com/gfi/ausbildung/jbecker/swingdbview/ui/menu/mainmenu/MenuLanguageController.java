package com.gfi.ausbildung.jbecker.swingdbview.ui.menu.mainmenu;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.gfi.ausbildung.jbecker.swingdbview.main.MainApplication;
import com.gfi.ausbildung.jbecker.swingdbview.tools.DBVTools;
import com.gfi.ausbildung.jbecker.swingdbview.ui.MainWindow;

class MenuLanguageController
{

  private static final String LANGUAGE_SELECTION_WINDOW_TITLE_KEY   = "WINDOW_LANGUAGE_SELECT_TITLE";
  private static final String LANGUAGE_SELECTION_WINDOW_MESSAGE_KEY = "WINDOW_LANGUAGE_SELECT_MESSAGE";

  public void onLanguageMenuPressed(ActionEvent e)
  {
    String titleText = DBVTools.lang.getValue(LANGUAGE_SELECTION_WINDOW_TITLE_KEY);
    String messageText = DBVTools.lang.getValue(LANGUAGE_SELECTION_WINDOW_MESSAGE_KEY);

    String currentLang = DBVTools.getProperty("lang");
    String supportedLanguagesLine = DBVTools.getProperty("supported_lang");
    String[] supportedLanguages = supportedLanguagesLine.split(",");

    String result = (String) JOptionPane.showInputDialog(MainWindow.getInstance(), messageText, titleText,
        JOptionPane.PLAIN_MESSAGE, null, supportedLanguages, currentLang);

    if (result != null)
    {

      if (!currentLang.equals(result))
      {
        DBVTools.putProperty("lang", result);
        MainApplication.restartApplication();
      }
    }
  }

}
