package com.gfi.ausbildung.jbecker.swingdbview.ui.prefabs;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlignedLabel extends JPanel
{

  private final JLabel label;

  public AlignedLabel(String text, int alignmentX)
  {
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    label = new JLabel(text);
    label.setHorizontalAlignment(alignmentX);

    add(label);
  }

}
