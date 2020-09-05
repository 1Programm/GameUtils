package com.gfi.ausbildung.jbecker.swingdbview.utils;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class MyInternalFrameAdapter extends InternalFrameAdapter
{

  private final Runnable onClosingListener;
  private final Runnable onClosedListener;
  private final Runnable onActivated;

  public MyInternalFrameAdapter(Runnable onClosingListener, Runnable onClosedListener, Runnable onActivated)
  {
    this.onClosingListener = onClosingListener;
    this.onClosedListener = onClosedListener;
    this.onActivated = onActivated;
  }

  @Override
  public void internalFrameClosing(InternalFrameEvent e)
  {
    if (onClosingListener != null)
      onClosingListener.run();
  }

  @Override
  public void internalFrameClosed(InternalFrameEvent e)
  {
    if (onClosedListener != null)
      onClosedListener.run();
  }

  @Override
  public void internalFrameActivated(InternalFrameEvent e)
  {
    if (onActivated != null)
      onActivated.run();
  }

}
