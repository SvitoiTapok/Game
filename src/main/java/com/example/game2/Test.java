package com.example.game2;

import javafx.animation.PathTransition;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import javafx.util.Duration;

import java.util.Random;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        BorderPane pane = new BorderPane();

        Scene scene = new Scene(pane, 400, 400);
        scene.getStylesheets().add("style.css");
        Rectangle rectangle = new Rectangle(200,200);
        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.getStyleClass().add("rect");



        Button button = new Button("hahfahahfhahf");
        button.setOnAction(new RectDestroer(rectangle));
        //Pane pane1 = new Pane();
        PositionCatcher positionCatcher = new PositionCatcher(rectangle);
        //pane1.setOnMouseDragEntered(positionCatcher);
        //rectangle.setOnMousePressed(mouseEvent -> );
        rectangle.setOnMousePressed(positionCatcher);
        rectangle.setOnMouseDragged(new MouseDragged(positionCatcher));
        //pane1.getChildren().add(rectangle);
        //pane.getChildren().add(rectangle);
        //pane.getChildren().add(button);
        Pane pane1 = new Pane();
        pane1.getChildren().add(rectangle);
        pane.setCenter(pane1);
        pane.setBottom(button);


        //Circle
        //
        Circle circle = new Circle(50);
        pane.getChildren().add(circle);
        Line line = new Line(0,0,500,500);
        PathTransition pathTransition = new PathTransition(Duration.millis(1000), line, circle);
        pathTransition.setCycleCount(1000);
        //pathTransition.play();
        circle.centerXProperty().bindBidirectional(rectangle.xProperty());
        circle.centerYProperty().bindBidirectional(rectangle.yProperty());




        //Polygon
        Polygon triangle = new Polygon(new double[]{
                0, 0,
                20, 10,
                10, 20
        });
        triangle.setRotate(20);
        triangle.setLayoutX(200);
        triangle.setLayoutY(200);
        pane.getChildren().add(triangle);
        pane.setOnKeyPressed(keyEvent -> {
            System.out.println(keyEvent.getCode());
            if(keyEvent.getCode().equals(KeyCode.W)) {
                System.out.println("dlkfajdsf");
            }
        });
        pane.setOnMousePressed(mouseEvent -> {
            System.out.println("asdfasdf");
        });




        primaryStage.setScene(scene);
        primaryStage.show();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sample.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        ////Parent root = FXMLLoader.load(getClass().getResource("resources/com/example/game2/sample.fxml"));
        ////primaryStage.setTitle("Hello World");
        ////primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.setScene(scene);
        //primaryStage.show();
    }
    class RectDestroer implements EventHandler<ActionEvent>{
        private Rectangle rectangle;
        public RectDestroer(Rectangle rectangle){
            this.rectangle = rectangle;
        }
        @Override
        public void handle(ActionEvent actionEvent) {
            Random random = new Random();
            rectangle.setWidth(rectangle.getWidth()+random.nextDouble(-50, 50));
        }
    }

    class PositionCatcher implements EventHandler<MouseEvent>{
        private double offsetX;
        private double offsetY;
        private Rectangle rectangle;

        public PositionCatcher(Rectangle rectangle) {
            this.rectangle = rectangle;
        }

        @Override
        public void handle(MouseEvent mouseEvent) {
            this.offsetX = mouseEvent.getX() - rectangle.getX();
            this.offsetY = mouseEvent.getY() - rectangle.getY();
            System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
        }

        public double getOffsetX() {
            return offsetX;
        }

        public double getOffsetY() {
            return offsetY;
        }

        public Rectangle getRectangle() {
            return rectangle;
        }
    }

    class MouseDragged implements EventHandler<MouseEvent>{
        private PositionCatcher positionCatcher;

        public MouseDragged(PositionCatcher positionCatcher) {
            this.positionCatcher = positionCatcher;
        }

        @Override
        public void handle(MouseEvent mouseEvent) {
            positionCatcher.getRectangle().setX(-positionCatcher.getOffsetX()+mouseEvent.getX());
            positionCatcher.getRectangle().setY(-positionCatcher.getOffsetY()+mouseEvent.getY());
        }
    }
    class TestKeyboard implements EventHandler<KeyEvent>{
        public void handle(KeyEvent e) {
            switch (e.getCode()) {
                case W -> System.out.println("w");
                case A -> System.out.println("a");
                case S -> System.out.println("s");
                case D -> System.out.println("d");
            }
        }
    }

}