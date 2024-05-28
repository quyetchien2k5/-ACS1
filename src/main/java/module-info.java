module main.java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires mysql.connector.j;
    requires java.sql;

    opens View to javafx.fxml;
    opens Controller to javafx.fxml;
    exports View;
    exports Controller;
    exports Model;
}