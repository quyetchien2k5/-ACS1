module com.example.do_an_co_so_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens com.example.do_an_co_so_1 to javafx.fxml;
    exports com.example.do_an_co_so_1;
}