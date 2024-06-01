package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.DatabaseConnection;
import model.DishModel;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class DishController {
    private HomeCounterController homeCounterController;

    public void setHomeCounterController(HomeCounterController homeCounterController) {
        this.homeCounterController = homeCounterController;
    }

    public ImageView Dish_Image;
    public Label Dish_Name;
    public Label Dish_Price;
    public Spinner Dish_Number;
    public ImageView Dish_Add;


    DishModel dishModel = new DishModel();

    public List<DishModel> getProducts() throws Exception {
        List<DishModel> modelProducts = new ArrayList<>();
        String SELECT_PRODUCTS_QUERY = "SELECT * FROM menu";
        DatabaseConnection databaseConnection = new DatabaseConnection();

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Process results
            while (resultSet.next()) {
                DishModel dishModel = new DishModel();

                dishModel.setIdProduct(resultSet.getInt("id"));
                dishModel.setNameProduct(resultSet.getString("name"));
                dishModel.setPrice(resultSet.getInt("price"));
                dishModel.setImage(resultSet.getString("image"));

                modelProducts.add(dishModel);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
        return modelProducts;
    }

    public void setProductData(DishModel modelProduct) {
        Dish_Name.setText(modelProduct.getNameProduct());
        Dish_Price.setText(String.valueOf(modelProduct.getPrice()));
        System.out.println(modelProduct.getNameProduct());

        try {
            String imagePathproduct = modelProduct.getImage(); // Lấy đường dẫn từ CSDL
            Image image = new Image(imagePathproduct);
            Dish_Image.setImage(image);
            Dish_Image.setPreserveRatio(true); // Đảm bảo tỷ lệ khung hình được bảo toàn
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // Xử lý hoặc ghi nhận ngoại lệ một cách thích hợp
        }
        // Thiết lập giá trị Spinner
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        Dish_Number.setValueFactory(valueFactory);
    }

    public void Action_AddDish(MouseEvent mouseEvent) {
        System.out.println("input data bill"+ Dish_Name.getText()+ Dish_Price.getText()+ Dish_Number.getValue());
        homeCounterController.setTableBill(Integer.parseInt(Dish_Number.getValue().toString()), Dish_Name.getText(), Integer.parseInt(Dish_Price.getText()));
        homeCounterController.calculateTotalPrice();
    }


}
