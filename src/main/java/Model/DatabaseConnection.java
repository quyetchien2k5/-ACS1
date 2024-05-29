package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {


    public static Connection getConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/đacs1","root","");

            return connect;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }
    public static void closeConnect(Connection c){
        try {
            c.close();
            System.out.println("Đóng kết nối csdl thành công!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
