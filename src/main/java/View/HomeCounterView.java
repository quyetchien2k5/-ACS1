package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeCounterView extends Application {
    @Override
    public void start(Stage stage)throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/HomeCounter.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1210, 700);
            scene.getStylesheets().add(getClass().getResource("/Css/HomeCounter.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();

        }
    }

