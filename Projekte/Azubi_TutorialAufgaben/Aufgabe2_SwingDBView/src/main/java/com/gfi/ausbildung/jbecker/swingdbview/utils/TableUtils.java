package com.gfi.ausbildung.jbecker.swingdbview.utils;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TableUtils
{

  public static TableModel buildTableModelFromArray(Object[][] arr)
  {
    int numRows = arr.length;
    int maxColumnLength = 0;

    for (int i = 0; i < numRows; i++)
    {
      int colLength = arr[i].length;
      if (maxColumnLength < colLength)
      {
        maxColumnLength = colLength;
      }
    }

    TableModel model = new DefaultTableModel(numRows, maxColumnLength);

    // Populate

    for (int r = 0; r < numRows; r++)
    {
      Object[] row = arr[r];
      for (int c = 0; c < maxColumnLength; c++)
      {
        if (c < row.length)
        {
          model.setValueAt(row[c], r, c);
        }
      }
    }

    return model;
  }

}
