package model;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InputDish extends Application {

    public String UrlImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn một ảnh");

        // Chỉ cho phép chọn các tệp ảnh
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                "Các tệp ảnh", "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp");
        fileChooser.getExtensionFilters().add(imageFilter);

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            // Trả về URL của ảnh được chọn
            return selectedFile.toURI().toString();
        } else {
            return null;
        }
    }

    public void inputData(Stage stage) {
        Scanner scanner = new Scanner(System.in);

        // Lấy dữ liệu từ người dùng
        System.out.print("Nhập tên món ăn: ");
        String nameDish = scanner.nextLine();

        System.out.print("Nhập giá món ăn: ");
        int price = scanner.nextInt();
        scanner.nextLine(); // Đọc bỏ dòng mới

        // Lấy URL của ảnh
        String urlImage = UrlImage(stage);

        // Kiểm tra nếu URL không hợp lệ
        if (urlImage == null) {
            System.out.println("Không có ảnh nào được chọn");
            return;
        }

        String query = "INSERT INTO menu (name, price, image) VALUES (?, ?, ?)";

        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameDish);
            preparedStatement.setInt(2, price);
            preparedStatement.setString(3, urlImage);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Món ăn mới đã được thêm thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chọn ảnh và nhập thông tin món ăn");

        // Chạy inputData trên JavaFX Application Thread
        Platform.runLater(() -> inputData(primaryStage));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
