package com.gfi.ausbildung.jbecker.swingdbview.utils;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowOnCloseListener implements WindowListener
{

  private final Runnable onClosing;
  private final Runnable onClosed;

  public WindowOnCloseListener(Runnable onClosing, Runnable onClosed)
  {
    this.onClosing = onClosing;
    this.onClosed = onClosed;
  }

  @Override
  public void windowClosing(WindowEvent e)
  {
    if (onClosing != null)
    {
      onClosing.run();
    }
  }

  @Override
  public void windowClosed(WindowEvent e)
  {
    if (onClosed != null)
    {
      onClosed.run();
    }
  }

  @Override
  public void windowOpened(WindowEvent e)
  {
  }

  @Override
  public void windowIconified(WindowEvent e)
  {
  }

  @Override
  public void windowDeiconified(WindowEvent e)
  {
  }

  @Override
  public void windowActivated(WindowEvent e)
  {
  }

  @Override
  public void windowDeactivated(WindowEvent e)
  {
  }

}
