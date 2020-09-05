package com.gfi.ausbildung.jbecker.swingdbview.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;

public class ActionWrapper extends AbstractAction
{

  private final ActionListener listener;

  public ActionWrapper(ActionListener listener)
  {
    this.listener = listener;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    listener.actionPerformed(e);
  }

}