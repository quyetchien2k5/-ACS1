package dao;

import model.DatabaseConnection;
import model.Employee;
import model.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeDAO implements DAOInterface<Employee> {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    public static EmployeeDAO getInstance(){
        return new EmployeeDAO();
    }

    @Override
    public void insert(Employee employee) throws Exception {
        String reData = "INSERT INTO employee(name, phone, password, date, job, workingdays, salary, avatar, manager_email)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        connect = DatabaseConnection.getConnection();
        System.out.println(employee.getName());

        prepare = connect.prepareStatement(reData);

        prepare.setString(1, employee.getName());
        prepare.setString(2, employee.getPhone());
        prepare.setString(3, employee.getPassword());
        prepare.setString(4, String.valueOf(employee.getDate()));
        prepare.setString(5, String.valueOf(employee.getJob()));

        prepare.setString(6, String.valueOf(employee.getWorkingdays()));
        prepare.setString(7, String.valueOf(employee.getSalary()));
        String path = data.pathAvatar;
        path = path.replace("\\", "\\\\");
        prepare.setString(8, path);

        prepare.setString(9, employee.getManager_email());


        prepare.executeUpdate();

        DatabaseConnection.closeConnect(connect);
    }

    @Override
    public void update(Employee employee) throws Exception {

    }

    @Override
    public void delete(Employee employee) throws Exception {

    }

    @Override
    public ArrayList<Employee> selectAll() {
        return null;
    }

    @Override
    public Employee selectById(Employee employee) {
        return null;
    }

    @Override
    public ArrayList<Employee> selectByCondition(String condition) {
        return null;
    }
}
