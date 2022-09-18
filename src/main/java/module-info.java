module com.example.inte2512finalproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.inte2512finalproject to javafx.fxml;
    exports com.example.inte2512finalproject;
}