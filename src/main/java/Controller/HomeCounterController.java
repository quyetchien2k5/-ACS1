package Controller;

import Model.DishModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;

import java.sql.SQLException;
import java.util.List;

public class HomeCounterController {
    @FXML
    private ScrollPane Menu_List;

    @FXML
    public void initialize() {
        try {
            loadMenu();
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
}
