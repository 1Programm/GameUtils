package com.gfi.ausbildung.jbecker.swingdbview.ui.menu.mainmenu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.gfi.ausbildung.jbecker.swingdbview.tools.DBVTools;
import com.gfi.ausbildung.jbecker.swingdbview.tools.loader.IconLoader;

public class MenuData extends JMenu
{

  private static final String      MENU_NAME_KEY           = "MENU_DATA_TEXT";
  private static final String      MENU_ITEM_LOAD_NAME_KEY = "MENU_DATA_ITEM_LOAD_TEXT";
  private static final String      MENU_ITEM_QUIT_NAME_KEY = "MENU_DATA_ITEM_QUIT_TEXT";

  private final MenuDataController controller;

  private final JMenuItem          dbLoadMenuItem;
  private final JMenuItem          dbQuitMenuItem;

  public MenuData()
  {
    setText(DBVTools.lang.getValue(MENU_NAME_KEY));

    controller = new MenuDataController();

    dbLoadMenuItem = new JMenuItem(DBVTools.lang.getValue(MENU_ITEM_LOAD_NAME_KEY),
        IconLoader.getIconResource("MenuDataLoad.png"));
    dbLoadMenuItem.addActionListener(controller::onItemLoadPressed);

    dbQuitMenuItem = new JMenuItem(DBVTools.lang.getValue(MENU_ITEM_QUIT_NAME_KEY),
        IconLoader.getIconResource("MenuDataQuit.png"));
    dbQuitMenuItem.addActionListener(e -> controller.onItemQuitPressed(e, this));

    add(dbLoadMenuItem);
    add(dbQuitMenuItem);
  }

}
