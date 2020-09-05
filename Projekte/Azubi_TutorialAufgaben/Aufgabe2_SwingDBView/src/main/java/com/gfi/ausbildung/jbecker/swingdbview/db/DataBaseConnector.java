package com.gfi.ausbildung.jbecker.swingdbview.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gfi.ausbildung.jbecker.swingdbview.tools.DBVTools;

public class DataBaseConnector
{

  private static final String DEFAULT_SERVER_URL_KEY    = "DB_SERVER_URL";
  private static final String DEFAULT_USER_NAME_KEY     = "DB_USER_NAME";
  private static final String DEFAULT_USER_PASSWORD_KEY = "DB_USER_PASSWORD";
  private static boolean      sqlDriverInitialized;

  public static DataBaseConnectorFactory create()
  {
    String serverUrl = DBVTools.getProperty(DEFAULT_SERVER_URL_KEY);
    String userName = DBVTools.getProperty(DEFAULT_USER_NAME_KEY);
    String userPassword = DBVTools.getProperty(DEFAULT_USER_PASSWORD_KEY);

    DataBaseConnectorFactory factory = new DataBaseConnectorFactory(serverUrl);
    factory.setUserCredentials(userName, userPassword);
    return factory;
  }

  private static void initSqlDriver()
  {
    if (!sqlDriverInitialized)
    {
      sqlDriverInitialized = true;
      try
      {
        Class.forName("com.mysql.cj.jdbc.Driver");
      }
      catch (ClassNotFoundException e)
      {
        throw new IllegalStateException();
      }
    }
  }

  private final String   serverUrl;
  private final String   dataBaseName;
  private final String[] urlArguments;
  private final String   userName;
  private final String   userPwd;

  private Connection     connection;

  DataBaseConnector(String serverUrl, String dataBaseName, String[] urlArguments, String userName, String userPwd)
  {
    this.serverUrl = serverUrl;
    this.dataBaseName = dataBaseName;
    this.urlArguments = urlArguments;
    this.userName = userName;
    this.userPwd = userPwd;
  }

  public void connectAndWait() throws SQLException
  {
    initSqlDriver();

    String url = getConnectionUrl();
    connection = DriverManager.getConnection(url, userName, userPwd);
  }

  public ResultSet execute(SqlCall call) throws SQLException
  {
    Statement statement = connection.createStatement();
    boolean resultSet = statement.execute(call.get());

    if (resultSet)
    {
      return statement.getResultSet();
    }

    return null;
  }

  public void close() throws SQLException
  {
    if (connection == null)
      return;
    connection.close();
  }

  public String getDataBaseName()
  {
    return dataBaseName;
  }

  public List<String> getAllTableNames() throws SQLException
  {
    DatabaseMetaData md = connection.getMetaData();
    ResultSet rs = md.getTables(dataBaseName, null, "%", null);

    List<String> names = new ArrayList<>();
    while (rs.next())
    {
      names.add(rs.getString(3));
    }

    return names;
  }

  private String getConnectionUrl()
  {
    StringBuilder sb = new StringBuilder();

    sb.append(serverUrl);
    sb.append("/");
    sb.append(dataBaseName);
    sb.append("?");

    for (int i = 0; i < urlArguments.length; i++)
    {
      if (i > 0)
        sb.append("&");

      String argument = urlArguments[i];
      sb.append(argument);
    }

    return sb.toString();
  }
}
