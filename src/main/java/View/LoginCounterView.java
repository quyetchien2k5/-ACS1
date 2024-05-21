package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginCounterView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/LoginCounter.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 372);
        scene.getStylesheets().add(getClass().getResource("/Css/LoginCounter.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();

    }
}
