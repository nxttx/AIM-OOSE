package dao;

import java.sql.*;
import java.util.Properties;

public class TestConnection {
    //https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
    private String userName = "root";
    private String password= "";
    private String dbms = "mysql";
    private String serverName="localhost";
    private int portNumber = 3306;

    public Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        if (this.dbms.equals("mysql")) {
            conn = DriverManager.getConnection(
                    "jdbc:" + this.dbms + "://" +
                            this.serverName +
                            ":" + this.portNumber + "/",
                    connectionProps);
        }
        System.out.println("Connected to database");
        return conn;
    }

    public void restoreDatabase() {
        String sql = "select * from track";
            //todo buid database and auto build it in here. 

        try (Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();


        }catch(Exception e){

        }
    }
}
