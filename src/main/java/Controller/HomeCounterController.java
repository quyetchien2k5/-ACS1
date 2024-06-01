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
import model.DishModel;
import model.TableModel;

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

    @FXML
    public void initialize() {
        try {
            loadMenu();
            setupRowSelectionListener();  // Gọi phương thức lắng nghe sự kiện chọn hàng
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadMenu() throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Dish.fxml"));
            List<DishModel> modelProducts = getProducts();
            System.out.println("Loaded products: " + modelProducts);

            TilePane tilePane = new TilePane();
            tilePane.setPrefColumns(5);
            tilePane.setHgap(10);
            tilePane.setVgap(20);

            for (DishModel productView : modelProducts) {
                System.out.println("Loading product: " + productView.getNameProduct());

                Node productNode = loader.load();
                DishController productController = loader.getController();
                productController.setHomeCounterController(this);
                productController.setProductData(productView);
                tilePane.getChildren().add(productNode);

                loader = new FXMLLoader(getClass().getResource("/Fxml/Dish.fxml"));
            }

            if (Menu_List == null) {
                System.out.println("Menu_List is null, initializing...");
                Menu_List = new ScrollPane();
            } else {
                System.out.println("Menu_List is already initialized.");
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
            List<TableModel> modelTable= getTable();


            TilePane tilePane = new TilePane();
            tilePane.setPrefColumns(5);
            tilePane.setHgap(10);
            tilePane.setVgap(20);

            for (TableModel productView : modelTable) {

                Node productNode = loader.load();
                TableController tableController = loader.getController();
                tableController.setHomeCounterController(this);
               tableController.setTableData(productView);
                tilePane.getChildren().add(productNode);

                loader = new FXMLLoader(getClass().getResource("/Fxml/Table.fxml"));
            }

            if (Table_List == null) {

                Table_List = new ScrollPane();
            } else {
                System.out.println("Table list is already initialized.");
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
        DishModel newDish = new DishModel(number, name, price*number);
        billData.add(newDish);
        System.out.println(number + name + price);

        Table_Pay_Number.setCellValueFactory(new PropertyValueFactory<>("number"));
        Table_Pay_Name.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        Table_Pay_Price.setCellValueFactory(new PropertyValueFactory<>("price"));

        Table_Pay.setItems(billData);
    }

    private void setupRowSelectionListener() {
        Table_Pay.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DishModel>() {
            @Override
            public void changed(ObservableValue<? extends DishModel> observable, DishModel oldValue, DishModel newValue) {
                // Enable or disable the delete button based on row selection
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
        // Lấy thời gian hiện tại
        LocalDateTime currentTime = LocalDateTime.now();

        // Định dạng thời gian theo ý muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Chuyển đổi thời gian thành chuỗi
        String formattedTime = currentTime.format(formatter);

        // Trả về chuỗi thời gian đã định dạng
        return formattedTime;
    }
    public void Bill_Pay(ActionEvent actionEvent) throws SQLException {
        Oder_Scene.setVisible(true);
        Menu_Scene.setVisible(false);
        Back.setVisible(true);
        loadTable();
//        PrinterJob printerJob = PrinterJob.getPrinterJob();
//
//        printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
//            if (pageIndex > 0) {
//                return Printable.NO_SUCH_PAGE;
//            }
//
//            graphics.drawString(billData.toString(), 100, 100); // Văn bản cần in
//
//            return Printable.PAGE_EXISTS;
//        });
//
//        if (printerJob.printDialog()) {
//            try {
//                printerJob.print();
//            } catch (PrinterException e) {
//                e.printStackTrace();
//            }
//        }

    }

    public void calculateTotalPrice() {
        // Get all items in the TableView
        ObservableList<DishModel> items = Table_Pay.getItems();

        // Initialize the total price
        int totalPrice = 0;

        // Calculate the total price by summing up the prices from each row
        for (DishModel item : items) {
            Integer price = Table_Pay_Price.getCellData(item);
            if (price != null) {
                totalPrice += price;
            }
        }

        Bill_Total.setText(String.valueOf(totalPrice));
    }

    public void Back_Action(MouseEvent mouseEvent) {
        Menu_Scene.setVisible(true);
        Oder_Scene.setVisible(false);
    }
}
