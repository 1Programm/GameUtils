package com.gfi.ausbildung.jbecker.swingdbview.ui.menu.mainmenu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.gfi.ausbildung.jbecker.swingdbview.tools.DBVTools;

public class MenuLanguage extends JMenu
{

  private static final String              MENU_NAME_KEY             = "MENU_LANGUAGE_TEXT";
  private static final String              MENU_ITEM_SELECT_NAME_KEY = "MENU_LANGUAGE_ITEM_SELECT_TEXT";

  private final MenuLanguageController controller;

  public MenuLanguage()
  {
    setText(DBVTools.lang.getValue(MENU_NAME_KEY));

    controller = new MenuLanguageController();

    JMenuItem selectMenuItem = new JMenuItem(DBVTools.lang.getValue(MENU_ITEM_SELECT_NAME_KEY));
    selectMenuItem.addActionListener(controller::onLanguageMenuPressed);

    add(selectMenuItem);
  }

}
