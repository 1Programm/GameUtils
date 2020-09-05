package com.gfi.ausbildung.jbecker.swingdbview.ui.db;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.gfi.ausbildung.jbecker.swingdbview.db.DataBaseConnector;
import com.gfi.ausbildung.jbecker.swingdbview.db.DataBaseConnectorFactoryException;
import com.gfi.ausbildung.jbecker.swingdbview.main.Paths;
import com.gfi.ausbildung.jbecker.swingdbview.ui.MainWindow;
import com.gfi.ausbildung.jbecker.swingdbview.ui.prefabs.InputWithLabel;
import com.gfi.ausbildung.jbecker.swingdbview.utils.StringResourceList;

public class DataBaseLoaderWindowController
{

  private static final Logger log = Logger.getLogger(DataBaseLoaderWindowController.class);

  private Runnable            closeCallback;
  private InputWithLabel      dbNameInput;
  private StringResourceList  dbNameHistoryResourceList;
  private JList<String>       dbNameHistoryList;

  public void init(Runnable closeCallback, InputWithLabel dbNameInput, JList<String> dbNameHistory,
      DefaultListModel<String> dbNameHistoryModel)
  {
    this.closeCallback = closeCallback;
    this.dbNameInput = dbNameInput;
    this.dbNameHistoryList = dbNameHistory;

    File dbNameHistoryFile = new File(Paths.PATH_DB_NAME_HISTORY_FOR_FILE);
    dbNameHistoryResourceList = new StringResourceList(dbNameHistoryFile, ",", true);

    for (String name : dbNameHistoryResourceList.get())
    {
      dbNameHistoryModel.addElement(name);
    }
  }

  public void onCancelPressed(ActionEvent e)
  {
    log.debug("Cancel Database loader.");
    closeCallback.run();
  }

  public void onLoadPressed(ActionEvent evt)
  {
    String dbNameInputText = dbNameInput.getInputText();

    if (dbNameInputText.isEmpty())
    {
      if (dbNameHistoryList.getSelectedIndex() == -1)
      {
        JOptionPane.showMessageDialog(null, "Database name cannot be empty");
        return;
      }

      dbNameInputText = dbNameHistoryList.getSelectedValue();
    }

    DataBaseConnector connector;

    try
    {
      connector = DataBaseConnector.create().setDataBaseName(dbNameInputText).build();
    }
    catch (DataBaseConnectorFactoryException e)
    {
      log.error("Error in DataBaseConnectorFactory: \"" + e.getMessage() + "\".");
      throw new IllegalStateException(e.getMessage());
    }

    log.info("Connecting to \"" + dbNameInputText + "\"...");

    try
    {
      connector.connectAndWait();
    }
    catch (SQLException e)
    {
      JOptionPane.showMessageDialog(null, e.getMessage());
      return;
    }

    log.info("Successfully connected to \"" + dbNameInputText + "\"!");

    closeCallback.run();

    DataBaseTableViewerWindow dbViewerWindow = new DataBaseTableViewerWindow(connector);
    dbViewerWindow.setSize(300, 300);
    MainWindow.addInternalFrameAndCenter(dbViewerWindow);

    dbViewerWindow.show();
  }

  public void onHistoryDoubleClicked(MouseEvent e)
  {
    int index = dbNameHistoryList.locationToIndex(e.getPoint());

    if (index != -1)
    {
      onLoadPressed(null);
    }
  }

}
