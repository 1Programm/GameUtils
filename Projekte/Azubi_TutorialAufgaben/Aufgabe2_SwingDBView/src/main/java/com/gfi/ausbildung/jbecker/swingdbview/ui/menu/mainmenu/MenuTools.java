package com.gfi.ausbildung.jbecker.swingdbview.ui.menu.mainmenu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.gfi.ausbildung.jbecker.swingdbview.tools.DBVTools;

public class MenuTools extends JMenu
{

  private static final String       MENU_NAME_KEY           = "MENU_TOOLS_TEXT";
  private static final String       MENU_ITEM_LOAD_NAME_KEY = "MENU_TOOLS_XMAPP_START_TEXT";

  private final MenuToolsController controller;

  public MenuTools()
  {
    setText(DBVTools.lang.getValue(MENU_NAME_KEY));

    controller = new MenuToolsController();

    JMenuItem xamppStartItem = new JMenuItem(DBVTools.lang.getValue(MENU_ITEM_LOAD_NAME_KEY));
    xamppStartItem.addActionListener(controller::onStartXamppPressed);
    add(xamppStartItem);
  }

}
