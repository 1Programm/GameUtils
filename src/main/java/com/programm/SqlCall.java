package com.gfi.ihk.jbecker.tests.dbtest;

public class SqlCall {

    public static SqlCall SELECT(String what, String who){
        return new SqlCall("SELECT " + what + " FROM " + who);
    }

    public interface CREATE {
        static SqlCall TABLE(String tableName){
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE ").append(tableName).append(" ").append("(").append("\n");
            sb.append("id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY").append("\n");
            sb.append(")");

            return new SqlCall(sb.toString());
        }
    }

    public interface INSERT_INTO {
        static SqlCall TABLE(String tableName, String[] argumentNames, String[] values){
            StringBuilder sb = new StringBuilder();

//            INSERT INTO `test`(`name`) VALUES ("abc")
            sb.append("INSERT INTO ");
            sb.append(tableName);
            sb.append("'");

            return new SqlCall(sb.toString());
        }
    }

    private final String command;

    SqlCall(String command) {
        this.command = command;
    }

    public String get(){
        return command;
    }

}
