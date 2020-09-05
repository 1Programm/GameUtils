package com.gfi.ausbildung.jbecker.swingdbview.ui.db;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.gfi.ausbildung.jbecker.swingdbview.db.DataBaseConnector;
import com.gfi.ausbildung.jbecker.swingdbview.db.SqlCall;
import com.gfi.ausbildung.jbecker.swingdbview.ui.MainWindow;
import com.gfi.ausbildung.jbecker.swingdbview.ui.prefabs.TableViewPane;
import com.gfi.ausbildung.jbecker.swingdbview.utils.DBUtils;

public class DataBaseTableViewerWindowController extends ComponentAdapter
{

  private final static Logger     log     = Logger.getLogger(DataBaseTableViewerWindowController.class);

  private final DataBaseConnector connector;
  private boolean                 refresh = false;

  public DataBaseTableViewerWindowController(DataBaseConnector connector)
  {
    this.connector = connector;
  }

  @Override
  public void componentShown(ComponentEvent e)
  {
    TableViewPane tablePane = (TableViewPane) e.getComponent();

    if (tablePane.getState() == TableViewPane.STATE_NOT_INITIALIZED || refresh)
    {
      log.debug("Loading table...");
      Object[][] data = getTableData(tablePane.getTableName());
      tablePane.loadTable(data);
    }
  }

  public void onInternalFrameClosing()
  {
    MainWindow.getInstance().getContextMenu().setContext(null);
  }

  public void onInternalFrameActivated(DataBaseTableViewerWindow iFrame)
  {
    MainWindow.getInstance().getContextMenu().setContext(iFrame);
  }

  private Object[][] getTableData(String tableName)
  {
    log.debug("Creating table model for table \"" + tableName + "\"...");
    try
    {
      ResultSet rs = connector.execute(SqlCall.SELECT("*", tableName));
      return DBUtils.getTableDataFromResultSet(rs);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      return null;
    }
  }

}
