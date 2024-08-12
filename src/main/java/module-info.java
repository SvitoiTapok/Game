module com.example.game2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.game2 to javafx.fxml;
    exports com.example.game2;
    exports com.example.game2.PlayerMovement;
    opens com.example.game2.PlayerMovement to javafx.fxml;
}