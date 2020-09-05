package com.gfi.ausbildung.jbecker.swingdbview.ui.menu.mainmenu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import com.gfi.ausbildung.jbecker.swingdbview.ui.MainWindow;
import com.gfi.ausbildung.jbecker.swingdbview.ui.db.DataBaseLoaderWindow;
import com.gfi.ausbildung.jbecker.swingdbview.utils.SwingUtils;

class MenuDataController
{

  public void onItemLoadPressed(ActionEvent e)
  {
    DataBaseLoaderWindow dbLoaderWindow = new DataBaseLoaderWindow();
    MainWindow.addInternalFrameAndMaximise(dbLoaderWindow);

    dbLoaderWindow.show();
  }

  public void onItemQuitPressed(ActionEvent e, MenuData dataMenu)
  {
    JFrame rootFrame = SwingUtils.getRootFrame(dataMenu);
    rootFrame.dispose();
  }

}
