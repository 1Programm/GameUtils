package com.gfi.ausbildung.jbecker.swingdbview.ui.menu.mainmenu;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

import com.gfi.ausbildung.jbecker.swingdbview.ui.db.DataBaseTableViewerWindow;

class MenuContextController
{
  private final MenuContext         contextMenu;
  private DataBaseTableViewerWindow viewerContext;
  private JMenuItem                 contextNameLabel;

  public MenuContextController(MenuContext contextMenu)
  {
    this.contextMenu = contextMenu;
  }

  public void init(JMenuItem contextNameLabel)
  {
    this.contextNameLabel = contextNameLabel;
  }

  public void onCloseButtonPressed(ActionEvent e)
  {
    viewerContext.dispose();
  }

  public void setContext(DataBaseTableViewerWindow o)
  {
    if (viewerContext != null && o == null)
    {
      contextMenu.setEnabled(false);
    }
    else if (viewerContext == null && o != null)
    {
      contextMenu.setEnabled(true);
      onContextEnabled(o);
    }

    this.viewerContext = o;
  }

  private void onContextEnabled(DataBaseTableViewerWindow context)
  {
    contextNameLabel.setText(context.getDataBaseName());
  }
}
