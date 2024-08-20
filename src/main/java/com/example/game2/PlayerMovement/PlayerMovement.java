package com.example.game2.PlayerMovement;

import com.example.game2.Map.Map;
import com.example.game2.Map.Wall;

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
        //we need slide on walls
        double speculativeXPos = player.getXPos()+cos(toRadians(player.getAngle()))*step;
        double speculativeYPos = player.getYPos()+sin(toRadians(player.getAngle()))*step;
        double currentXPos = player.getXPos();
        double currentYPos = player.getYPos();

        double Kp = (speculativeYPos-currentYPos)/(speculativeXPos-currentXPos);
        double Bp = speculativeYPos-speculativeXPos*Kp;

        for(Wall wall: map.getWalls()){
            double Kw = (wall.getY1()-wall.getY2())/(wall.getX1()-wall.getX2());
            double Bw = wall.getY1()-Kw*wall.getX1();
            System.out.println(Kp+" "+ Bp+" "+ Kw + " "+  Bw);
            //если оба вектора вертикальны
            if(Double.isInfinite(Kp)&&Double.isInfinite(Kw)){
                continue;
            }
            //если вектор скорости вертикален
            if(Double.isInfinite(Kp)){
                double Yw = Kw*speculativeXPos+Bw;
                if(min(currentYPos, speculativeYPos)<Yw && Yw<max(currentYPos, speculativeYPos) && min(wall.getY1(), wall.getY2()) < Yw && Yw < max(wall.getY1(), wall.getY2())){
                    double[] resultVector = Movement.collide(currentXPos, currentYPos, currentXPos, Yw, wall, step);
                    player.setXPos(player.getXPos()+resultVector[0]);
                    player.setYPos(player.getYPos()+resultVector[1]);
                }
                continue;
            }
            //если стена вертикальна
            if(Double.isInfinite(Kw)){
                double Yp = Kp*wall.getX1()+Bp;
                System.out.println(Yp);
                if(min(wall.getY1(), wall.getY2()) < Yp && Yp < max(wall.getY1(), wall.getY2())&& min(currentYPos, speculativeYPos)< Yp && Yp < max(currentYPos, speculativeYPos)) {
                    double[] resultVector = Movement.collide(currentXPos, currentYPos, wall.getX1(), Yp, wall, step);
                    player.setXPos(player.getXPos() + resultVector[0]);
                    player.setYPos(player.getYPos() + resultVector[1]);
                    continue;
                }
                    continue;
            }
            //если никто не вертикален
            double collideX = (Bw-Bp)/(Kp-Kw);
            double collideY = Kp*collideX+Bp;
            double[] resultVector = Movement.collide(currentXPos, currentYPos, collideX, collideY, wall, step);
            player.setXPos(player.getXPos() + resultVector[0]);
            player.setYPos(player.getYPos() + resultVector[1]);
            //double Kp;
            //if(speculativeXPos==speculativeYPos)
        }




        //y=k
        //player.setXPos();
        //player.setYPos();
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
