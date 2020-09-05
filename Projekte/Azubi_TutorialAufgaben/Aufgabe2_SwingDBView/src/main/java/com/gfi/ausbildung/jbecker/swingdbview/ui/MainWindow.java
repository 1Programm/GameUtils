package com.gfi.ausbildung.jbecker.swingdbview.ui;

import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import org.apache.log4j.Logger;

import com.gfi.ausbildung.jbecker.swingdbview.tools.DBVTools;
import com.gfi.ausbildung.jbecker.swingdbview.ui.menu.mainmenu.MainWindowMenuBar;
import com.gfi.ausbildung.jbecker.swingdbview.ui.menu.mainmenu.MenuContext;
import com.gfi.ausbildung.jbecker.swingdbview.utils.WindowOnCloseListener;

public class MainWindow extends JFrame
{

  private static final Logger log       = Logger.getLogger(MainWindow.class);

  private static final String TITLE_KEY = "WINDOW_MAIN_TITLE";
  private static final int    WIDTH     = 600, HEIGHT = WIDTH / 12 * 9;

  private static MainWindow   instance;

  public static MainWindow getInstance()
  {
    if (instance == null)
    {
      instance = new MainWindow();
    }

    return instance;
  }

  public static void addInternalFrame(JInternalFrame iFrame)
  {
    instance.add(iFrame);
  }

  public static void addInternalFrameAndCenter(JInternalFrame iFrame)
  {
    instance.add(iFrame);
    centerInternalFrame(iFrame);
  }

  public static void addInternalFrameAndMaximise(JInternalFrame iFrame)
  {
    addInternalFrame(iFrame);

    try
    {
      iFrame.setMaximum(true);
    }
    catch (PropertyVetoException e)
    {
      log.error(e.getMessage());
    }
  }

  public static void centerInternalFrame(JInternalFrame iFrame)
  {
    int mainWidth = instance.getContentPane().getWidth();
    int mainHeight = instance.getContentPane().getHeight();

    iFrame.setLocation(mainWidth / 2 - iFrame.getWidth() / 2, mainHeight / 2 - iFrame.getHeight() / 2);
  }

  public static JInternalFrame createInternalFrame(String title)
  {
    JInternalFrame internalFrame = new JInternalFrame(title);
    addInternalFrame(internalFrame);
    return internalFrame;
  }

  public static JInternalFrame createMaximisedInternalFrame(String title)
  {
    JInternalFrame internalFrame = new JInternalFrame(title);
    addInternalFrameAndMaximise(internalFrame);
    return internalFrame;
  }

  private final MainWindowMenuBar menuBar;

  private MainWindow()
  {
    setTitle(DBVTools.lang.getValue(TITLE_KEY));
    setSize(WIDTH, HEIGHT);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(true);
    setLocationRelativeTo(null);

    setContentPane(new JDesktopPane());

    menuBar = new MainWindowMenuBar();
    setJMenuBar(menuBar);
  }

  public void addOnExitListener(Runnable onExitListener)
  {
    addWindowListener(new WindowOnCloseListener(onExitListener, null));
  }

  public void close()
  {
    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    instance = null;
  }

  public MenuContext getContextMenu()
  {
    return menuBar.getContextMenu();
  }

}
