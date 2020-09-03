package com.gfi.ihk.jbecker.tests.dbtest;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            String dbName = "mysql";
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName + "?serverTimezone=UTC","root","");
//
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from emp");
//
//            while(rs.next()) {
//                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
//            }
//            con.close();
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
        try {
            new Main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Main() throws Exception {
        DataBaseConnector dbConnector = DataBaseConnector.create()
                .setDataBaseName("mysql")
                .addUrlArgument("serverTimezone=UTC")
                .build();

        dbConnector.connectAndWait();

        SqlCall selectCall = SqlCall.SELECT("*", "test");

        ResultSet res = dbConnector.execute(selectCall);

        if(res != null){
            System.out.println("Result:");
            while(res.next()) {
                System.out.println(res.getInt(1));
            }
            System.out.println("--------");
        }

        dbConnector.close();
    }

}
