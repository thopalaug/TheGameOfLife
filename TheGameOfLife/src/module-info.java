module TheGameOfLife {
    requires javafx.controls;
    requires javafx.fxml;

    opens View;
    opens Model;
    opens Controller;
}