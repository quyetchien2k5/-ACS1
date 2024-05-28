package Controller;

import Model.DishModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeCounterController {
    // Menu id -------------------------------------------------------------
    public ScrollPane Menu_List;

    public void loadMenu() throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Dish.fxml"));

            DishController dishController = new DishController();

            List<DishModel> modelProducts = dishController.getProducts();

            TilePane tilePane = new TilePane();
            tilePane.setPrefColumns(5);  // Đặt số cột
            tilePane.setHgap(10);  // Đặt khoảng cách ngang giữa các nút
            tilePane.setVgap(20);  // Đặt khoảng cách dọc giữa các nút

            for (DishModel productView : modelProducts) {
                // Tải một phiên bản mới của view FXML và lấy controller của nó
                Node productNode = loader.load();
                DishController productController = loader.getController();

                // Thiết lập dữ liệu cho controller
                dishController.setProductData(productView);

                // Thêm node đã tải vào TilePane
                tilePane.getChildren().add((Node) productNode);

                // Tạo một loader mới để sử dụng cho sản phẩm tiếp theo
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
    public List<DishModel> getProducts() throws Exception {
        DishController controllerProduct = new DishController();
        return controllerProduct.getProducts();
    }


}
