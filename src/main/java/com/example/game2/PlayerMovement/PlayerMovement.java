package com.example.game2.PlayerMovement;

import com.example.game2.Map.Map;

import static java.lang.Math.*;
import static java.lang.Math.toRadians;

public class PlayerMovement {
    private Map map;
    private Player player;
    private final double step=5;
    private final double dAngle=5;

    public PlayerMovement(Map map, Player player) {
        this.map = map;
        this.player = player;
    }

    public void move() {
        if (player.isAPressed())
            rotateLeft();
        if (player.isDPressed())
            rotateRight();
        if (player.isWPressed())
            moveForward();
        if (player.isSPressed())
            moveBackward();
    }

    private void moveForward(){
        player.setXPos(player.getXPos()+cos(toRadians(player.getAngle()))*step);
        player.setYPos(player.getYPos()+sin(toRadians(player.getAngle()))*step);
        //yPos+=sin(toRadians(angle))*step;
        //polygon.setLayoutX(polygon.getLayoutX() + cos(toRadians(angle))*step);
        //polygon.setLayoutY(polygon.getLayoutY() + sin(toRadians(angle))*step);
    }
    private void moveBackward(){
        player.setXPos(player.getXPos()-cos(toRadians(player.getAngle()))*step);
        player.setYPos(player.getYPos()-sin(toRadians(player.getAngle()))*step);
    }
    private void rotateRight(){
        player.setAngle(player.getAngle()+dAngle);
        //polygon.setRotate(polygon.getRotate()+5);
    }
    private void rotateLeft(){
        player.setAngle(player.getAngle()-dAngle);
    }

}
