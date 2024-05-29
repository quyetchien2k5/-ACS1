package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginManagerView extends Application {

<<<<<<< HEAD:src/main/java/View/LoginManagerView.java
=======

>>>>>>> ea442ad5cb4c4dd7c589c95c1089734311f14d6c:src/main/java/view/LoginManagerView.java
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/loginManager.fxml"));
            Scene scene = new Scene(root);

            stage.setResizable(false);

            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
