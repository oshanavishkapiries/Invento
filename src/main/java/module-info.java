module com.invento.invento {
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires javafx.controls;


    opens com.invento.invento.controller to javafx.fxml;
    opens com.invento.invento.controller.layout to javafx.fxml;
    exports com.invento.invento;
}