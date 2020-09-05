package com.gfi.ausbildung.jbecker.swingdbview.utils;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;

public class SwingUtils
{

  public static JFrame getRootFrame(Component component)
  {
    Container parent;

    while ((parent = component.getParent()) != null)
    {
      component = parent;
    }

    return (JFrame) component;
  }

}