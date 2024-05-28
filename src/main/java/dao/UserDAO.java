package dao;

import model.DatabaseConnection;
import model.User;
import model.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO implements DAOInterface<User> {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public static UserDAO getInstance(){
        return new UserDAO();
    }
    @Override
    public void insert(User user) throws Exception {
        String reData = "INSERT INTO user(name, email, gender, age, password, position, date, salary, avatar)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        connect = DatabaseConnection.getConnection();
        System.out.println(user.getName());

                prepare = connect.prepareStatement(reData);

                prepare.setString(1, user.getName());
                prepare.setString(2, user.getEmail());
                prepare.setString(3, user.getGender());
                prepare.setString(4, String.valueOf(user.getAge()));
                prepare.setString(5, data.encodePassword(user.getPassword()));
                prepare.setString(6, user.getPosition());

                prepare.setString(7, String.valueOf(user.getDate()));
                prepare.setString(8, String.valueOf(user.getSalary()));
                String path = data.path;
                path = path.replace("\\", "\\\\");

                prepare.setString(9, path);

                prepare.executeUpdate();

                DatabaseConnection.closeConnect(connect);
            }

    @Override
    public void update(User user) throws Exception {

    }

    @Override
    public void delete(User user) throws Exception {

    }

    @Override
    public ArrayList<User> selectAll() {
        return null;
    }

    @Override
    public User selectById(User user) {
        return null;
    }

    @Override
    public ArrayList<User> selectByCondition(String condition) {
        return null;
    }
}

