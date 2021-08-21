module seng202.group6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens seng202.group6.Controllers to javafx.fxml;
    exports seng202.group6.Controllers;
}
