package com.example.game2.PlayerMovement;
import javafx.scene.shape.Polygon;

public class Player {
    private double xPos;
    private double yPos;
    private double angle;

    private final Polygon polygon;

    private boolean isWPressed = false;
    private boolean isSPressed = false;
    private boolean isAPressed = false;
    private boolean isDPressed = false;

    public Player(double xPos, double yPos, double angle, Polygon polygon) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
        polygon.setLayoutX(xPos);
        polygon.setLayoutY(yPos);
        this.polygon = polygon;
    }

    public double getAngle() {
        return angle;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }


    public void setWPressed(boolean WPressed) {
        isWPressed = WPressed;
    }

    public void setSPressed(boolean SPressed) {
        isSPressed = SPressed;
    }

    public void setAPressed(boolean APressed) {
        isAPressed = APressed;
    }

    public void setDPressed(boolean DPressed) {
        isDPressed = DPressed;
    }

    //public void setMoveMultiplier(double moveMultiplier) {
    //    this.moveMultiplier = moveMultiplier;
    //}
//
    //public void setRotationMultiplier(double rotationMultiplier) {
    //    this.rotationMultiplier = rotationMultiplier;
    //}

    public boolean isWPressed() {
        return isWPressed;
    }

    public boolean isDPressed() {
        return isDPressed;
    }

    public boolean isSPressed() {
        return isSPressed;
    }

    public boolean isAPressed() {
        return isAPressed;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
        polygon.setLayoutX(xPos);
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
        polygon.setLayoutY(yPos);
    }

    public void setAngle(double angle) {
        this.angle = angle;
        polygon.setRotate(angle);
    }
//xPos+=cos(toRadians(angle))*step*moveMultiplier;
        //yPos+=sin(toRadians(angle))*step*moveMultiplier;
        //angle+=dAngle*rotationMultiplier;
//
        //polygon.setRotate(polygon.getRotate()+dAngle*rotationMultiplier);
        //polygon.setLayoutX(polygon.getLayoutX() + cos(toRadians(angle))*step*moveMultiplier);
        //polygon.setLayoutY(polygon.getLayoutY() + sin(toRadians(angle))*step*moveMultiplier);

    //public void setAngle(double angle) {
    //    this.angle = angle;
    //}
//
    //public void setxPos(double xPos) {
    //    this.xPos = xPos;
    //}
//
    //public void setyPos(double yPos) {
    //    this.yPos = yPos;
    //}
}
