module com.iadlpc.mazesolver {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.iadlpc.mazesolver to javafx.fxml;
    exports com.iadlpc.mazesolver;
}