package com.example.game2.PlayerMovement;

import com.example.game2.Map.Map;
import com.example.game2.Map.Wall;

import static java.lang.Math.*;
import static java.lang.Math.toRadians;

public class PlayerMovement {
    private Map map;
    private Player player;
    private final double step = 5;
    private final double dAngle = 5;

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

    private void moveForward() {
        //we need slide on walls
        double[] resultVector = {cos(toRadians(player.getAngle())) * step, sin(toRadians(player.getAngle())) * step};
        double speculativeXPos = player.getXPos() + resultVector[0];
        double speculativeYPos = player.getYPos() + resultVector[1];
        double currentXPos = player.getXPos();
        double currentYPos = player.getYPos();

        //double Kp = (speculativeYPos-currentYPos)/(speculativeXPos-currentXPos);
        double Kp = resultVector[1] / resultVector[0];
        double Bp = speculativeYPos - speculativeXPos * Kp;

        for (Wall wall : map.getWalls()) {
            double Kw = (wall.getY1() - wall.getY2()) / (wall.getX1() - wall.getX2());
            double Bw = wall.getY1() - Kw * wall.getX1();
            //System.out.println(Kp+" "+ Bp+" "+ Kw + " "+  Bw);
            //если оба вектора вертикальны
            if (Double.isInfinite(Kp) && Double.isInfinite(Kw)) {
                continue;
            }
            //если вектор скорости вертикален
            if (Double.isInfinite(Kp)) {
                double Yw = Kw * speculativeXPos + Bw;
                if (min(currentYPos, speculativeYPos) <= Yw && Yw <= max(currentYPos, speculativeYPos) &&
                        min(wall.getY1(), wall.getY2()) <= Yw && Yw <= max(wall.getY1(), wall.getY2()) &&
                        min(wall.getX1(), wall.getX2()) <= speculativeXPos && max(wall.getX1(), wall.getX2()) >= speculativeXPos) {
                    resultVector = Movement.collide(currentXPos, currentYPos, currentXPos, Yw, wall, step);
                }
                continue;
            }
            //если стена вертикальна
            if (Double.isInfinite(Kw)) {
                double Yp = Kp * wall.getX1() + Bp;
                //System.out.println(Yp);
                //System.out.println(wall.getX1() + " " + player.getXPos());
                if (min(wall.getY1(), wall.getY2()) <= Yp && Yp <= max(wall.getY1(), wall.getY2()) &&
                        min(currentYPos, speculativeYPos) <= Yp && Yp <= max(currentYPos, speculativeYPos) &&
                        min(currentXPos, speculativeXPos) <= wall.getX1() && max(currentXPos, speculativeXPos) >= wall.getX1()) {
                    resultVector = Movement.collide(currentXPos, currentYPos, wall.getX1(), Yp, wall, step);
                    System.out.println(resultVector[0] + currentXPos + " " + resultVector[1] + currentYPos);
                    System.out.println(Yp + " " + currentYPos);
                    System.out.println(Kp + " " + Kw);
                    System.out.println(speculativeXPos + " " + currentXPos);
                    //continue;
                }
                continue;
            }
            //если никто не вертикален
            double collideX = (Bw - Bp) / (Kp - Kw);
            double collideY = Kp * collideX + Bp;
            if (min(wall.getX1(), wall.getX2()) <= collideX && min(speculativeXPos, currentXPos) <= collideX &&
                    max(wall.getX1(), wall.getX2()) >= collideX && max(speculativeXPos, currentXPos) <= collideX &&
                    min(wall.getY1(), wall.getY2()) <= collideY && min(speculativeYPos, currentYPos) <= collideY &&
                    max(wall.getY1(), wall.getY2()) >= collideY && max(speculativeYPos, currentYPos) >= collideY) {
                resultVector = Movement.collide(currentXPos, currentYPos, collideX, collideY, wall, step);
            }


            //double Kp;
            //if(speculativeXPos==speculativeYPos)
        }
        player.setXPos(player.getXPos() + resultVector[0]);
        player.setYPos(player.getYPos() + resultVector[1]);


        //y=k
        //player.setXPos();
        //player.setYPos();
        //yPos+=sin(toRadians(angle))*step;
        //polygon.setLayoutX(polygon.getLayoutX() + cos(toRadians(angle))*step);
        //polygon.setLayoutY(polygon.getLayoutY() + sin(toRadians(angle))*step);
    }

    private void moveBackward() {
        player.setXPos(player.getXPos() - cos(toRadians(player.getAngle())) * step);
        player.setYPos(player.getYPos() - sin(toRadians(player.getAngle())) * step);
    }

    private void rotateRight() {
        player.setAngle(player.getAngle() + dAngle);
        //polygon.setRotate(polygon.getRotate()+5);
    }

    private void rotateLeft() {
        player.setAngle(player.getAngle() - dAngle);
    }


}
