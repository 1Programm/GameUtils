package com.gfi.ausbildung.jbecker.swingdbview.ui.menu.mainmenu;

import javax.swing.JMenuBar;

public class MainWindowMenuBar extends JMenuBar
{

  private final MenuData     dataMenu;
  private final MenuLanguage languageMenu;
  private final MenuContext  contextMenu;
  private final MenuTools    toolsMenu;

  public MainWindowMenuBar()
  {
    dataMenu = new MenuData();
    languageMenu = new MenuLanguage();
    contextMenu = new MenuContext();
    toolsMenu = new MenuTools();

    add(dataMenu);
    add(languageMenu);
    add(contextMenu);
    add(toolsMenu);

    contextMenu.setEnabled(false);
  }

  public MenuContext getContextMenu()
  {
    return contextMenu;
  }

}
