package com.example.game2;

import com.example.game2.Map.Map;
import com.example.game2.Map.Wall;
import com.example.game2.PlayerMovement.Player;
import com.example.game2.PlayerMovement.PlayerMovement;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
        Scene scene = new Scene(borderPane, 1200, 1200);
        borderPane.setCenter(pane);
        DrawMap(Map.getINSTANCE(), pane);
        Polygon triangle = new Polygon(new double[]{
                0,0,
                20,10,
                0, 20
        });
        Player player = new Player(500,500, 0, triangle);
        PlayerMovement playerMovement = new PlayerMovement(Map.getINSTANCE(), player);

        //pane.setLayoutX(100);
        //pane.setLayoutY(100);
        pane.getChildren().add(triangle);
        //borderPane.setOnKeyPressed(keyEvent -> {
        //    if(keyEvent.getCode().equals(KeyCode.W))
        //        player.setMoveMultiplier(1);
//
        //    if(keyEvent.getCode().equals(KeyCode.A))
        //        player.setRotationMultiplier(-1);
//
        //    if(keyEvent.getCode().equals(KeyCode.D))
        //        player.setRotationMultiplier(1);
//
        //    if(keyEvent.getCode().equals(KeyCode.S))
        //        player.setMoveMultiplier(-1);
//
        //});
        borderPane.setOnKeyPressed(keyEvent -> {
                if(keyEvent.getCode().equals(KeyCode.W))
                    player.setWPressed(true);
//
                if(keyEvent.getCode().equals(KeyCode.A))
                    player.setAPressed(true);
//
                if(keyEvent.getCode().equals(KeyCode.D))
                    player.setDPressed(true);
//
                if(keyEvent.getCode().equals(KeyCode.S))
                    player.setSPressed(true);
//
            });
        borderPane.setOnKeyReleased(keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.W))
                player.setWPressed(false);
//
            if(keyEvent.getCode().equals(KeyCode.A))
                player.setAPressed(false);
//
            if(keyEvent.getCode().equals(KeyCode.D))
                player.setDPressed(false);
//
            if(keyEvent.getCode().equals(KeyCode.S))
                player.setSPressed(false);
//
        });
        //borderPane.setOnKeyReleased(keyEvent -> {
        //    if(keyEvent.getCode().equals(KeyCode.W)||keyEvent.getCode().equals(KeyCode.S))
        //        player.setMoveMultiplier(0);
//
        //    if(keyEvent.getCode().equals(KeyCode.A)||keyEvent.getCode().equals(KeyCode.D))
        //        player.setRotationMultiplier(0);
        //});
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                playerMovement.move();
            }
        };
        animationTimer.start();

        //borderPane.setOnKeyPressed(keyEvent -> {
        //    if(keyEvent.getCode().equals(KeyCode.S)) {
        //        player.moveBackward();
        //    }
        //});
        borderPane.requestFocus();
//        borderPane.setOnMousePressed(mouseEvent -> {
//            System.out.println("asdfasdf");
//        });
//        borderPane.setOnMouseDragged(mouseEvent -> {
//            System.out.println("asdfasdf");
//        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static void DrawMap(Map map, Pane pane){
        ArrayList<Wall> walls = map.getWalls();
        for(Wall wall: walls){
            Line line = new Line(wall.getX1(), wall.getY1(), wall.getX2(), wall.getY2());
            line.setStrokeWidth(5);
            pane.getChildren().add(line);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
