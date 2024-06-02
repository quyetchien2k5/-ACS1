package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.DatabaseConnection;
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
    public AnchorPane Table_Card;

    private HomeCounterController homeCounterController;

    public void setHomeCounterController(HomeCounterController homeCounterController) {
        this.homeCounterController = homeCounterController;
    }

    public List<TableModel> getTable() throws Exception {
        List<TableModel> modelTable = new ArrayList<>();
        String SELECT_TABLE_QUERY = "SELECT * FROM `table`";

        DatabaseConnection databaseConnection = new DatabaseConnection();

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TABLE_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                TableModel tableModel = new TableModel();
                tableModel.setNumber(resultSet.getInt("table_number"));
                tableModel.setStatus(resultSet.getString("status"));
                modelTable.add(tableModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelTable;
    }

    public void setTableData(TableModel tableModel) {
        Table_Number.setText(String.valueOf(tableModel.getNumber()));
        Table_Status.setText(tableModel.getStatus());
        if ("Trống".equals(tableModel.getStatus())) {
            Table_Card.setStyle("-fx-background-color: #5f9ea0;");
        } else {
            Table_Card.setStyle("-fx-background-color: #b9633a;");
        }
    }

    public String getTableNumber() {
        return Table_Number.getText();
    }

    public void Action_Table_Card(MouseEvent mouseEvent) throws Exception {
        if (!"Trống".equals(getTableStatus())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Bàn đã có khách");
            alert.show();
        } else {
            homeCounterController.setSelectedTable(getTableNumber());
            updateTableStatus("Có khách");
            changeTableBackgroundColor();
            homeCounterController.loadTable();
            homeCounterController.insertBill();
            homeCounterController.insertBillItems();
            homeCounterController.printBill();
            homeCounterController.showAlert();
        }
    }

    private void updateTableStatus(String newStatus) {
        String UPDATE_STATUS_QUERY = "UPDATE `table` SET `status` = ? WHERE `table_number` = ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS_QUERY)) {

            preparedStatement.setString(1, newStatus);
            preparedStatement.setString(2, getTableNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeTableBackgroundColor() {
        Table_Card.setStyle("-fx-background-color: #b9633a");
    }

    public String getTableStatus() {
        String tableStatus = "";
        String SELECT_TABLE_STATUS_QUERY = "SELECT status FROM `table` WHERE table_number = ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TABLE_STATUS_QUERY)) {

            preparedStatement.setString(1, getTableNumber());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tableStatus = resultSet.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableStatus;
    }

    public void reloadTable(MouseEvent mouseEvent) throws SQLException {
        String UPDATE_STATUS_QUERY = "UPDATE `table` SET `status` = ? WHERE `table_number` = ?";
        DatabaseConnection databaseConnection = new DatabaseConnection();

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS_QUERY)) {

            preparedStatement.setString(1, "Trống");
            preparedStatement.setString(2, getTableNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Table_Card.setStyle("-fx-background-color: #5f9ea0");
        homeCounterController.loadTable();
    }
}
