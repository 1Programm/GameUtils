package com.gfi.ausbildung.jbecker.swingdbview.ui.db;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.gfi.ausbildung.jbecker.swingdbview.tools.DBVTools;
import com.gfi.ausbildung.jbecker.swingdbview.ui.prefabs.AlignedLabel;
import com.gfi.ausbildung.jbecker.swingdbview.ui.prefabs.InputWithLabel;
import com.gfi.ausbildung.jbecker.swingdbview.utils.MouseMultiClickListener;

public class DataBaseLoaderWindow extends JInternalFrame
{

  private static final String                  TITLE_KEY               = "WINDOW_DB_LOADER_TITLE";
  private static final String                  PANE_DBNAME_TEXT        = "WINDOW_DB_LOADER_PN_DBNAME_TEXT";
  private static final String                  BUTTON_CANCEL_TEXT      = "WINDOW_DB_LOADER_BTN_CANCEL_TEXT";
  private static final String                  BUTTON_LOAD_TEXT        = "WINDOW_DB_LOADER_BTN_LOAD_TEXT";
  private static final String                  PANE_DBNAMEHISTORY_TEXT = "WINDOW_DB_LOADER_PN_DBNAME_HISTORY_TEXT";

  private final DataBaseLoaderWindowController controller;
  private InputWithLabel                       dbNameInput;
  private DefaultListModel<String>             dbNameHistoryModel;
  private JList<String>                        dbNameHistory;

  public DataBaseLoaderWindow()
  {
    super(DBVTools.lang.getValue(TITLE_KEY));
    getContentPane().setLayout(new BorderLayout());

    controller = new DataBaseLoaderWindowController();

    JPanel inputLabelPane = createInputLabelPane();
    JPanel controllPane = createControllButtonsPane();

    add(inputLabelPane, BorderLayout.NORTH);
    add(controllPane, BorderLayout.SOUTH);

    controller.init(this::dispose, dbNameInput, dbNameHistory, dbNameHistoryModel);
  }

  private JPanel createInputLabelPane()
  {
    JPanel pane = new JPanel();
    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
    pane.setBorder(new EmptyBorder(10, 10, 10, 10));

    dbNameInput = InputWithLabel.createWithOffsets(DBVTools.lang.getValue(PANE_DBNAME_TEXT), 10);
    dbNameInput.addActionListener(controller::onLoadPressed);
    pane.add(dbNameInput);

    AlignedLabel dbNameHistoryLabel = new AlignedLabel(DBVTools.lang.getValue(PANE_DBNAMEHISTORY_TEXT), JLabel.LEFT);
    dbNameHistoryLabel.setBorder(new EmptyBorder(10, 0, 2, 0));
    pane.add(dbNameHistoryLabel);

    dbNameHistoryModel = new DefaultListModel<>();
    dbNameHistory = new JList<>(dbNameHistoryModel);
    dbNameHistory.addMouseListener(new MouseMultiClickListener(2, controller::onHistoryDoubleClicked));

    pane.add(new JScrollPane(dbNameHistory));

    return pane;
  }

  private JPanel createControllButtonsPane()
  {
    JPanel pane = new JPanel();
    pane.setLayout(new FlowLayout());

    JButton dbCancelButton = new JButton(DBVTools.lang.getValue(BUTTON_CANCEL_TEXT));
    dbCancelButton.addActionListener(controller::onCancelPressed);

    JButton dbLoadButton = new JButton(DBVTools.lang.getValue(BUTTON_LOAD_TEXT));
    dbLoadButton.addActionListener(controller::onLoadPressed);

    pane.add(dbCancelButton);
    pane.add(dbLoadButton);

    return pane;
  }

}
