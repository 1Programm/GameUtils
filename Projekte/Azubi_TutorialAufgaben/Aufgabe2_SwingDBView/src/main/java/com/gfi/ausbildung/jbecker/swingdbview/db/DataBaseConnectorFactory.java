package com.gfi.ausbildung.jbecker.swingdbview.db;

import java.util.ArrayList;
import java.util.List;

public class DataBaseConnectorFactory
{

  private final String       serverUrl;
  private String             dataBaseName;
  private final List<String> urlArguments = new ArrayList<>();
  private String             userName;
  private String             userPwd;

  DataBaseConnectorFactory(String serverUrl)
  {
    this.serverUrl = serverUrl;
  }

  public DataBaseConnectorFactory setDataBaseName(String name)
  {
    this.dataBaseName = name;
    return this;
  }

  public DataBaseConnectorFactory addUrlArgument(String argument)
  {
    this.urlArguments.add(argument);
    return this;
  }

  public DataBaseConnectorFactory setUserCredentials(String name, String pwd)
  {
    this.userName = name;
    this.userPwd = pwd;
    return this;
  }

  public DataBaseConnector build() throws DataBaseConnectorFactoryException
  {
    if (dataBaseName == null)
    {
      throw new DataBaseConnectorFactoryException("Database Name cannot be null!");
    }

    if (userName == null)
    {
      throw new DataBaseConnectorFactoryException("User name cannot be null!");
    }

    return new DataBaseConnector(serverUrl, dataBaseName, urlArguments.toArray(new String[0]), userName, userPwd);
  }
}
