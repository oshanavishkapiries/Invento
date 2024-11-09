module com.invento.invento {
    requires javafx.fxml;
    requires javafx.controls;
    requires static lombok;
    requires java.sql;


    opens com.invento.invento.controller to javafx.fxml;
    opens com.invento.invento.controller.layout to javafx.fxml;
    opens com.invento.invento.controller.components.inventory to javafx.fxml;

    exports com.invento.invento;
}