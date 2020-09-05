package com.gfi.ausbildung.jbecker.swingdbview.ui.prefabs;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.gfi.ausbildung.jbecker.swingdbview.utils.TableUtils;

public class TableViewPane extends JTable
{
  public static final int STATE_NOT_INITIALIZED = 1;
  public static final int STATE_INITIALIZED     = 2;

  private final String    tableName;

  private int             state                 = STATE_NOT_INITIALIZED;

  public TableViewPane(String tableName)
  {
    this.tableName = tableName;
    // this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
  }

  public void loadTable(Object[][] data)
  {
    TableModel model = TableUtils.buildTableModelFromArray(data);
    setModel(model);
  }

  public String getTableName()
  {
    return tableName;
  }

  public int getState()
  {
    return state;
  }

  public void setState(int state)
  {
    this.state = state;
  }

}
