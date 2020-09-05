package com.gfi.ausbildung.jbecker.swingdbview.ui.menu.mainmenu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.gfi.ausbildung.jbecker.swingdbview.tools.DBVTools;
import com.gfi.ausbildung.jbecker.swingdbview.ui.db.DataBaseTableViewerWindow;

public class MenuContext extends JMenu
{

  private static final String         MENU_NAME_KEY                    = "MENU_CONTEXT_TEXT";
  private static final String         MENU_ITEM_CONTEXT_CLOSE_NAME_KEY = "MENU_CONTEXT_ITEM_CLOSE_TEXT";

  private final MenuContextController controller;
  final JMenuItem                     contextNameLabel;
  private final JMenuItem             contextCloseButton;

  public MenuContext()
  {
    setText(DBVTools.lang.getValue(MENU_NAME_KEY));

    controller = new MenuContextController(this);

    contextNameLabel = new JMenuItem();
    contextNameLabel.setEnabled(false);

    contextCloseButton = new JMenuItem(DBVTools.lang.getValue(MENU_ITEM_CONTEXT_CLOSE_NAME_KEY));
    contextCloseButton.addActionListener(controller::onCloseButtonPressed);

    add(contextNameLabel);
    add(contextCloseButton);

    controller.init(contextNameLabel);
  }

  public void setContext(DataBaseTableViewerWindow o)
  {
    controller.setContext(o);
  }
}
