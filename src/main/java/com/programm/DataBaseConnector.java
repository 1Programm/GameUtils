package com.gfi.ihk.jbecker.tests.dbtest;

import java.sql.*;

public class DataBaseConnector {

    private static final String DEFAULT_SERVER_URL = "jdbc:mysql://localhost";
    private static final String DEFAULT_USER_NAME = "root";
    private static final String DEFAULT_USER_PASSWORD = "";
    private static boolean sqlDriverInitialized;

    public static DataBaseConnectorFactory create(){
        DataBaseConnectorFactory factory = new DataBaseConnectorFactory(DEFAULT_SERVER_URL);
        factory.setUserCredentials(DEFAULT_USER_NAME, DEFAULT_USER_PASSWORD);
        return factory;
    }

    private static void initSqlDriver(){
        if(!sqlDriverInitialized){
            sqlDriverInitialized = true;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException();
            }
        }
    }

    private final String serverUrl;
    private final String dataBaseName;
    private final String[] urlArguments;
    private final String userName;
    private final String userPwd;

    private Connection connection;

    DataBaseConnector(String serverUrl, String dataBaseName, String[] urlArguments, String userName, String userPwd) {
        this.serverUrl = serverUrl;
        this.dataBaseName = dataBaseName;
        this.urlArguments = urlArguments;
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public void connect(){
        new Thread(() -> {
            try{
                connectAndWait();
            }catch (SQLException e){
                //TODO: Handle sql exception
                e.printStackTrace();
            }
        }).start();
    }

    public void connectAndWait() throws SQLException {
        initSqlDriver();

        String url = getConnectionUrl();
        connection = DriverManager.getConnection(url, userName, userPwd);
    }

    public ResultSet execute(SqlCall call) throws SQLException {
        Statement statement = connection.createStatement();
        boolean resultSet = statement.execute(call.get());

        if(resultSet){
            return statement.getResultSet();
        }

        return null;
    }

    public void close() throws SQLException {
        if(connection == null) return;
        connection.close();
    }

    private String getConnectionUrl(){
        StringBuilder sb = new StringBuilder();

        sb.append(serverUrl);
        sb.append("/");
        sb.append(dataBaseName);
        sb.append("?");

        for(int i=0;i<urlArguments.length;i++){
            if(i > 0) sb.append("&");

            String argument = urlArguments[i];
            sb.append(argument);
        }

        return sb.toString();
    }
}
