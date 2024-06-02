package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HomeManagerView extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/homeManager.fxml"));
            Scene scene = new Scene(root);
            Image image = new Image(getClass().getResourceAsStream("/Image/logo.png"));
            stage.getIcons().add(image);
            stage.setTitle("Restaurant Management System");
            stage.setResizable(false);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}