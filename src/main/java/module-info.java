module main.java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens View to javafx.fxml;
    exports View;
    exports Controller;
    exports Model;
}