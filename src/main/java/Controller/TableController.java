package controller;

import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import model.DatabaseConnection;
import model.DishModel;
import model.TableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableController {
    public Label Table_Status;
    public Label Table_Number;

    private HomeCounterController homeCounterController;

    public void setHomeCounterController(HomeCounterController homeCounterController) {
        this.homeCounterController = homeCounterController;
    }

    public List<TableModel> getTable() throws Exception {
        List<TableModel> modelTable = new ArrayList<>();
        String SELECT_PRODUCTS_QUERY = "SELECT * FROM `table`";

        DatabaseConnection databaseConnection = new DatabaseConnection();

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Process results
            while (resultSet.next()) {
                TableModel tableModel = new TableModel();

                tableModel.setNumber(resultSet.getInt("table_number"));
                tableModel.setStatus(resultSet.getString("status"));


                modelTable.add(tableModel);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
        return modelTable;
    }

    public void setTableData(TableModel tableModel) {
        Table_Number.setText(String.valueOf(tableModel.getNumber()));
        Table_Status.setText(tableModel.getStatus());
    }

    
    public void Action_Table_Card(MouseEvent mouseEvent) {

    }
}
