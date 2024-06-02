package dao;

import model.Menu;



import model.DatabaseConnection;
import model.Menu;
import model.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class MenuDAO implements DAOInterface<Menu> {

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;




    public static MenuDAO getInstance(){
        return new MenuDAO();
    }

    @Override
    public void insert(Menu menu) throws SQLException {
        connect = DatabaseConnection.getConnection();

        String insert = "INSERT INTO menu " + "(id, name, type, image, price) "
                + "VALUES(?,?,?,?,?)";

        prepare = connect.prepareStatement(insert);
        prepare.setString(1, menu.getId());
        prepare.setString(2, menu.getName());
        prepare.setString(3, menu.getType());

        String path = data.pathMenuImage;
        data.oldMenuId= menu.getId();
        path = path.replace("\\", "\\\\");
        prepare.setString(4, path);
        prepare.setString(5, String.valueOf(menu.getPrice()));

        prepare.executeUpdate();
  }

    @Override
    public void update(Menu menu) {

    }

    @Override
    public void delete(Menu menu) {
    }

    @Override
    public ArrayList selectAll() {
        return null;
    }

    @Override
    public Menu selectById(Menu menu) {
        return null;
    }


    @Override
    public ArrayList selectByCondition(String condition) {
        return null;
    }
}
