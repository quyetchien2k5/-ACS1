package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private final String URL = "jdbc:mysql://localhost:3306/đacs1";
    private final String USER = "root";
    private final String PASSWORD = "";

    public Connection getConnection() throws Exception{
        Connection connection = null;
            // Đăng ký driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Tạo kết nối
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Kiểm tra kết nối
            if (connection != null) {
                System.out.println("Kết nối thành công tới cơ sở dữ liệu!");
            } else {
                System.out.println("Kết nối thất bại!");
            }
        return connection;
    }

}
