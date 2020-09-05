package com.gfi.ausbildung.jbecker.swingdbview.ui.menu.mainmenu;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JMenu;

class MenuToolsController extends JMenu
{

  public void onStartXamppPressed(ActionEvent e)
  {
    ProcessBuilder startXamppProcessBuilder = new ProcessBuilder("C:\\xampp\\xampp-control.exe");
    try
    {
      startXamppProcessBuilder.start();
    }
    catch (IOException e1)
    {
      e1.printStackTrace();
    }
  }

}
