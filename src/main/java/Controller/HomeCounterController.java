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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import model.DishModel;

import java.sql.SQLException;
import java.text.AttributedCharacterIterator;
import java.util.List;

public class HomeCounterController {

    public Button Bill_Delete_Button;
    public Button Bill_Reset_Button;
    public Button Bill_Pay_Button;
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

    public List<DishModel> getProducts() throws Exception {
        DishController dishController = new DishController();
        return dishController.getProducts();
    }

    public void setTableBill(int number, String name, int price) {
        DishModel newDish = new DishModel(number, name, price);
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
        if (selectedDish != null) {
            Table_Pay.getItems().remove(selectedDish);
        }
    }

    public void Bill_Reset(ActionEvent actionEvent) {
        billData.clear();
    }

    public void Bill_Pay(ActionEvent actionEvent) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            graphics.drawString(billData.toString(), 100, 100); // Văn bản cần in

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
}
