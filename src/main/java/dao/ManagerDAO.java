package dao;

import model.DatabaseConnection;
import model.Manager;
import model.data;

import java.sql.*;
import java.util.ArrayList;

public class ManagerDAO implements DAOInterface<Manager>{
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    public static ManagerDAO getInstance(){
        return new ManagerDAO();
    }

    @Override
    public void insert(Manager manager) throws Exception {
        String reData = "INSERT INTO manager(name, email, gender, age, password, date, salary, avatar)"
                + "VALUES(?,?,?,?,?,?,?,?)";

        connect = DatabaseConnection.getConnection();
        System.out.println(manager.getName());

        prepare = connect.prepareStatement(reData);

        prepare.setString(1, manager.getName());
        prepare.setString(2, manager.getEmail());
        prepare.setString(3, manager.getGender());
        prepare.setString(4, String.valueOf(manager.getAge()));
        prepare.setString(5, manager.getPassword());

        prepare.setString(6, String.valueOf(manager.getDate()));
        prepare.setString(7, String.valueOf(manager.getSalary()));
        String path = data.pathAvatar;
        path = path.replace("\\", "\\\\");
        prepare.setString(8, path);


        prepare.executeUpdate();

        DatabaseConnection.closeConnect(connect);
    }

    @Override
    public void update(Manager manager) throws Exception {

    }

    @Override
    public void delete(Manager manager) throws Exception {

    }

    @Override
    public ArrayList<Manager> selectAll() {
        return null;
    }

    @Override
    public Manager selectById(Manager manager) {
        return null;
    }

    @Override
    public ArrayList<Manager> selectByCondition(String condition) throws SQLException {
        ArrayList<Manager> rs = new ArrayList<Manager>();
        String checkEmail = "SELECT * FROM manager WHERE email = ?";
        connect = DatabaseConnection.getConnection();
        prepare = connect.prepareStatement(checkEmail);
        prepare.setString(1, condition);
        result = prepare.executeQuery();
        while(result.next()){
            String name = result.getString("name");
            String email = result.getString("email");
            String gender = result.getString("gender");
            Integer age = result.getInt("age");
            String password = result.getString("password");
            Date date = result.getDate("date");
            Integer salary = result.getInt("salary");
            String avatar = result.getString("avatar");
            Manager manager = new Manager(name, email, gender,age,password,date,salary,avatar);
            rs.add(manager);
        }
        DatabaseConnection.closeConnect(connect);

        return rs;

    }
}
