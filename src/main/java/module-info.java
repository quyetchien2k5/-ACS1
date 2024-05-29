module main.java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires mysql.connector.j;
    requires java.sql;
    requires java.desktop;


    opens view to javafx.fxml;
    opens controller to javafx.fxml;

    exports view;
    exports controller;
    exports model;


}