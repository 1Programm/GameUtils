package com.gfi.ausbildung.jbecker.swingdbview.utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class MouseMultiClickListener extends MouseAdapter
{

  private final int                  clicks;
  private final Consumer<MouseEvent> listener;

  public MouseMultiClickListener(int clicks, Consumer<MouseEvent> listener)
  {
    this.clicks = clicks;
    this.listener = listener;
  }

  @Override
  public void mouseClicked(MouseEvent e)
  {
    if (e.getClickCount() == clicks)
    {
      listener.accept(e);
    }
  }

}
