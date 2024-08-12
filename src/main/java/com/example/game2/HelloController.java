package com.example.game2;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private Button knpka;

    @FXML
    private void click(ActionEvent event) {
        knpka.setText("Вы кликнули!");    }
}