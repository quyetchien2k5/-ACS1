package controller;

import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import model.DatabaseConnection;
import model.DishModel;
import model.TableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HomeCounterController {

    public Button Bill_Delete_Button;
    public Button Bill_Reset_Button;
    public Button Bill_Pay_Button;
    public Label Bill_Total;
    public ScrollPane Table_List;
    public AnchorPane Order_Scene;
    public ImageView Back;
    public AnchorPane Menu_Scene;
    public AnchorPane Oder_Scene;
    @FXML
    private TableView<DishModel> Table_Pay;
    @FXML
    private TableColumn<DishModel, Integer> Table_Pay_Number;
    @FXML
    private TableColumn<DishModel, String> Table_Pay_Name;
    @FXML
    private TableColumn<DishModel, Integer> Table_Pay_Price;
    @FXML
    private Button deleteButton;
    @FXML
    private ScrollPane Menu_List;

    private ObservableList<DishModel> billData = FXCollections.observableArrayList();
    private String selectedTable;

    @FXML
    public void initialize() {
        try {
            loadMenu();
            setupRowSelectionListener();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadMenu() throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Dish.fxml"));
            List<DishModel> modelProducts = getProducts();

            TilePane tilePane = new TilePane();
            tilePane.setPrefColumns(5);
            tilePane.setHgap(10);
            tilePane.setVgap(20);

            for (DishModel productView : modelProducts) {
                Node productNode = loader.load();
                DishController productController = loader.getController();
                productController.setHomeCounterController(this);
                productController.setProductData(productView);
                tilePane.getChildren().add(productNode);

                loader = new FXMLLoader(getClass().getResource("/Fxml/Dish.fxml"));
            }

            if (Menu_List == null) {
                Menu_List = new ScrollPane();
            }
            ScrollPane scrollPane = new ScrollPane(tilePane);
            Menu_List.setContent(scrollPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTable() throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Table.fxml"));
            List<TableModel> modelTable = getTable();

            TilePane tilePane = new TilePane();
            tilePane.setPrefColumns(5);
            tilePane.setHgap(10);
            tilePane.setVgap(20);

            for (TableModel tableView : modelTable) {
                Node tableNode = loader.load();
                TableController tableController = loader.getController();
                tableController.setHomeCounterController(this);
                tableController.setTableData(tableView);
                tilePane.getChildren().add(tableNode);

                loader = new FXMLLoader(getClass().getResource("/Fxml/Table.fxml"));
            }

            if (Table_List == null) {
                Table_List = new ScrollPane();
            }
            ScrollPane scrollPane = new ScrollPane(tilePane);
            Table_List.setContent(scrollPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TableModel> getTable() throws Exception {
        TableController tableController = new TableController();
        return tableController.getTable();
    }

    public List<DishModel> getProducts() throws Exception {
        DishController dishController = new DishController();
        return dishController.getProducts();
    }

    public void setTableBill(int number, String name, int price) {
        DishModel newDish = new DishModel(number, name, price * number);
        billData.add(newDish);

        Table_Pay_Number.setCellValueFactory(new PropertyValueFactory<>("number"));
        Table_Pay_Name.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        Table_Pay_Price.setCellValueFactory(new PropertyValueFactory<>("price"));

        Table_Pay.setItems(billData);
    }

    private void setupRowSelectionListener() {
        Table_Pay.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DishModel>() {
            @Override
            public void changed(ObservableValue<? extends DishModel> observable, DishModel oldValue, DishModel newValue) {
                Bill_Delete_Button.setDisable(newValue == null);
            }
        });
    }

    public void Bill_Delete(ActionEvent actionEvent) {
        DishModel selectedDish = Table_Pay.getSelectionModel().getSelectedItem();
        Table_Pay.getItems().remove(selectedDish);
        calculateTotalPrice();
    }

    public void Bill_Reset(ActionEvent actionEvent) {
        billData.clear();
        calculateTotalPrice();
    }

    public static String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentTime.format(formatter);
    }

    public void Bill_Pay(ActionEvent actionEvent) throws SQLException {

        Menu_Scene.setVisible(false);
        Back.setVisible(true);
        loadTable();
        if(selectedTable == null){
            Oder_Scene.setVisible(true);

        }else{
            printBill();
        }

    }

    public void calculateTotalPrice() {
        ObservableList<DishModel> items = Table_Pay.getItems();
        int totalPrice = 0;
        for (DishModel item : items) {
            totalPrice += item.getPrice();
        }
        Bill_Total.setText(String.valueOf(totalPrice));
    }

    public void Back_Action(MouseEvent mouseEvent) {
        Menu_Scene.setVisible(true);
        Oder_Scene.setVisible(false);
    }
    public void printBill() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            // Thiết lập nội dung cần in
            StringBuilder sb = new StringBuilder();
            sb.append("Table Number: ").append(selectedTable).append("\n");
            sb.append("Dishes:\n");

// Duyệt qua danh sách các món ăn và thêm thông tin của mỗi món vào StringBuilder
            for (DishModel dish : billData) {
                sb.append(dish.getNameProduct()).append(" - ").append(dish.getNumber()).append(" x ").append(dish.getPrice()).append("\n");
            }

            sb.append("Total: ").append(Bill_Total.getText()).append("\n");

// Tính toán vị trí y cho mỗi món ăn, bắt đầu từ 100 và tăng dần
            int yPosition = 100;

// In nội dung theo hàng theo món
            for (String line : sb.toString().split("\n")) {
                graphics.drawString(line, 100, yPosition);
                yPosition += 20; // Tăng yPosition để điều chỉnh vị trí của dòng tiếp theo
            }

            return Printable.PAGE_EXISTS;

        });

        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSelectedTable(String table) {
        this.selectedTable = table;
    }
    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null); // Không có tiêu đề
        alert.setContentText("Đã xác nhận bill");
        alert.show();
        selectedTable = null;
    }

    public void Take_Home(ActionEvent actionEvent) {
        this.selectedTable = "Take home";
        insertBill();
        insertBillItems();
        printBill();
        showAlert();
    }
    TableController tableController = new TableController();
    public void insertBill() {
        String insertBillQuery = "INSERT INTO bill (table_number, total, time) VALUES (?, ?, ?)";

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertBillQuery);
            preparedStatement.setString(1, selectedTable);
            preparedStatement.setString(2, Bill_Total.getText());
            preparedStatement.setString(3, getCurrentTime());
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new bill inserted!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getLastInsertedBillId() {
        String query = "SELECT id FROM bill ORDER BY id DESC LIMIT 1";
        int lastId = -1;

        try (
                // Thiết lập kết nối cơ sở dữ liệu
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
        ) {
            // Kiểm tra xem có kết quả từ truy vấn không
            if (resultSet.next()) {
                lastId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(lastId);
        return lastId;
    }



    public void insertBillItems() {
        String insertBillItemQuery = "INSERT INTO bill_items (bill_id,item_name, quantity, price) VALUES (?, ?, ?, ?)";

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();

            for (DishModel dish : billData) {
                PreparedStatement billItemStatement = connection.prepareStatement(insertBillItemQuery);
                billItemStatement.setInt(1, getLastInsertedBillId());
                billItemStatement.setString(2, dish.getNameProduct());
                billItemStatement.setInt(3, dish.getNumber());
                billItemStatement.setDouble(4, dish.getPrice());
                billItemStatement.executeUpdate();
            }
            System.out.println("Bill items inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
