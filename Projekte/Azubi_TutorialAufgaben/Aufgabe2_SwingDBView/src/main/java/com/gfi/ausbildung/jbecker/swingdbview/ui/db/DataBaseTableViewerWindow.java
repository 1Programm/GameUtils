package com.gfi.ausbildung.jbecker.swingdbview.ui.db;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.gfi.ausbildung.jbecker.swingdbview.db.DataBaseConnector;
import com.gfi.ausbildung.jbecker.swingdbview.tools.DBVTools;
import com.gfi.ausbildung.jbecker.swingdbview.ui.prefabs.TableViewPane;
import com.gfi.ausbildung.jbecker.swingdbview.utils.MyInternalFrameAdapter;

public class DataBaseTableViewerWindow extends JInternalFrame
{

  private static final String                       TITLE_KEY = "WINDOW_DB_VIEWER_TITLE";

  private final DataBaseTableViewerWindowController controller;
  private final DataBaseConnector                   connector;

  public DataBaseTableViewerWindow(DataBaseConnector connector)
  {
    super(DBVTools.lang.getValue(TITLE_KEY) + " [" + connector.getDataBaseName() + "]", true, true);

    this.controller = new DataBaseTableViewerWindowController(connector);
    this.connector = connector;

    addInternalFrameListener(new MyInternalFrameAdapter(controller::onInternalFrameClosing,
        controller::onInternalFrameClosing, () -> controller.onInternalFrameActivated(this)));

    JTabbedPane tableViewTabs = createTabs();
    add(new JScrollPane(tableViewTabs));
  }

  private JTabbedPane createTabs()
  {
    List<String> tableNames = null;

    try
    {
      tableNames = connector.getAllTableNames();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }

    JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

    for (String tableName : tableNames)
    {
      TableViewPane tablePane = new TableViewPane(tableName);
      tablePane.addComponentListener(controller);
      tabs.addTab(tableName, tablePane);
    }

    int keytab_verIndex = tabs.indexOfTab("keytab_verw");
    tabs.setSelectedIndex(keytab_verIndex);

    return tabs;
  }

  public String getDataBaseName()
  {
    return connector.getDataBaseName();
  }

}