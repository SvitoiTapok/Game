package com.example.game2.PlayerMovement;

import com.example.game2.Map.Map;
import com.example.game2.Map.Wall;

import static java.lang.Math.*;
import static java.lang.Math.toRadians;

public class PlayerMovement {
    private Map map;
    private Player player;
    public static final double STEP = 50;
    public static final double DANGLE = 30;

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
        //System.out.println(player.getXPos() +" " + player.getYPos() + " " + player.getAngle());
    }

    //private static class CrossTheWall{
    //    private final int
    //}

    private void moveForward() {
        //we need slide on walls
        double[] resultVector = {0, 0};
        for (Wall wall : map.getWalls()) {
            resultVector = Movement.collide(wall, player);
        }
        System.out.println("\n\n\n");
        player.setXPos(player.getXPos() + resultVector[0]);
        player.setYPos(player.getYPos() + resultVector[1]);
        //y=k
        //player.setXPos();
        //player.setYPos();
        //yPos+=sin(toRadians(angle))*step;
        //polygon.setLayoutX(polygon.getLayoutX() + cos(toRadians(angle))*step);
        //polygon.setLayoutY(polygon.getLayoutY() + sin(toRadians(angle))*step);
        }

//    }

    private void moveBackward() {
        player.setXPos(player.getXPos() - cos(toRadians(player.getAngle())) * STEP);
        player.setYPos(player.getYPos() - sin(toRadians(player.getAngle())) * STEP);
    }

    private void rotateRight() {
        player.setAngle(player.getAngle() + DANGLE);
        //polygon.setRotate(polygon.getRotate()+5);
    }

    private void rotateLeft() {
        player.setAngle(player.getAngle() - DANGLE);
    }

}
