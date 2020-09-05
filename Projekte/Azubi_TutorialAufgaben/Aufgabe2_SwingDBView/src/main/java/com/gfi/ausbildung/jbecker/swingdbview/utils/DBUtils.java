package com.gfi.ausbildung.jbecker.swingdbview.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils
{

  public static Object[][] getTableDataFromResultSet(ResultSet rs) throws SQLException
  {
    ResultSetMetaData metaData = rs.getMetaData();
    // int numColumns = metaData.getColumnCount();

    List<String> columnNames = new ArrayList<>();

    int columns = metaData.getColumnCount();
    for (int i = 1; i <= columns; i++)
    {
      columnNames.add(metaData.getColumnName(i));
    }

    List<Object[]> data = new ArrayList<>();

    while (rs.next())
    {
      List<Object> row = new ArrayList<>();
      for (int i = 1; i <= columns; i++)
      {
        row.add(rs.getObject(i));
      }

      Object[] rowData = row.toArray(new Object[0]);
      data.add(rowData);
    }

    return data.toArray(new Object[0][]);
  }

}
