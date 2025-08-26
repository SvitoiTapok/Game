package com.example.game2.PlayerMovement;


import static java.lang.Math.*;
import com.example.game2.Map.Wall;


public class Movement {
    public static double[] calculateVectorAfterCollision(double currentXPos, double currentYPos, double  crossXPos, double crossYPos,
                                                         double speculativeXPos, double speculativeYPos, Wall wall, double step)
    {
        double[] wallVector = {wall.getX1()-wall.getX2(), wall.getY1()-wall.getY2()};
        double wallLength = sqrt(pow(wallVector[0], 2) + pow(wallVector[1],2));
        double[] playerVector = {speculativeXPos-currentXPos, speculativeYPos-currentYPos};
        //can be removed by just step
        //double playerLength = sqrt(pow(playerVector[0], 2) + pow(playerVector[1],2));
        //System.out.println(playerLength);

        //System.out.println(wallLength);

        double scalarMultiplication = playerVector[0]*wallVector[0] + playerVector[1]*wallVector[1];
        double cosBetweenPlayerAndWall = scalarMultiplication/(wallLength*step);
        if(cosBetweenPlayerAndWall<0)
            wallVector = new double[]{-wallVector[0], -wallVector[1]};
        double[] crossVector = {crossXPos-currentXPos, crossYPos-currentYPos};
        double distanceToWall = sqrt(pow(crossVector[0], 2) + pow(crossVector[1],2));
        double proectionLength = abs(cosBetweenPlayerAndWall*(step-distanceToWall));
        double[] unitWallVector = {wallVector[0]/wallLength, wallVector[1]/wallLength};
        double[] resultVector = {crossVector[0]+unitWallVector[0]*proectionLength,  crossVector[1]+unitWallVector[1]*proectionLength};
        //double angleBPAW = acos(cosBetweenPlayerAndWall);

        //System.out.println(playerLength);
        //System.out.println(playerVector[0]+" "+ playerVector[1]);
        //System.out.println(unitWallVector[0] + " " +unitWallVector[1]);
        //System.out.println(proectionLength);
        //System.out.println(resultVector[0]+" "+ resultVector[1]);
        //System.out.println(proectionLength);

        return resultVector;
    }
    public static double[] collide(Wall wall, Player player){
        double step = PlayerMovement.STEP;
        double[] resultVector = {cos(toRadians(player.getAngle())) * step, sin(toRadians(player.getAngle())) * step};
        double speculativeXPos = player.getXPos() + resultVector[0];
        double speculativeYPos = player.getYPos() + resultVector[1];
        double currentXPos = player.getXPos();
        double currentYPos = player.getYPos();

        //double Kp = (speculativeYPos-currentYPos)/(speculativeXPos-currentXPos);
        double Kp = resultVector[1] / resultVector[0];
        double Bp = speculativeYPos - speculativeXPos * Kp;
        double Kw = (wall.getY1() - wall.getY2()) / (wall.getX1() - wall.getX2());
        double Bw = wall.getY1() - Kw * wall.getX1();
        //System.out.println(Kp+" "+ Bp+" "+ Kw + " "+  Bw);
        //если оба вектора вертикальны
        if (Double.isInfinite(Kp) && Double.isInfinite(Kw)) {
            return resultVector;
        }
        //если вектор скорости вертикален
        if (Double.isInfinite(Kp)) {
            double Yw = Kw * speculativeXPos + Bw;
            if (checkCollisionVV(wall, speculativeXPos, speculativeYPos, currentYPos, Yw)) {
                resultVector = Movement.calculateVectorAfterCollision(currentXPos, currentYPos, currentXPos, Yw, speculativeXPos, speculativeYPos, wall, step);
                System.out.println("        velocity vertical");
            }
            return resultVector;
        }
        //если стена вертикальна
        if (Double.isInfinite(Kw)) {
            double Yp = Kp * wall.getX1() + Bp;
            if (checkCollisionWV(wall, speculativeXPos, speculativeYPos, currentXPos, currentYPos, Yp)) {
                resultVector = Movement.calculateVectorAfterCollision(currentXPos, currentYPos, wall.getX1(), Yp, speculativeXPos, speculativeYPos, wall, step);
                System.out.println("        wall vertical");
                //continue;
            }
            return resultVector;
        }
        //если никто не вертикален
        double collideX = (Bw - Bp) / (Kp - Kw);
        double collideY = Kp * collideX + Bp;
        if (checkCollisionNV(wall, speculativeXPos, speculativeYPos, currentXPos, currentYPos, collideX, collideY)) {
            resultVector = Movement.calculateVectorAfterCollision(currentXPos, currentYPos, collideX, collideY, speculativeXPos, speculativeYPos, wall, step);
            System.out.println("        nothing vertical");
        }
        System.out.println(wall);
        System.out.println(player);
        System.out.println("result_vector " + resultVector[0] + " " + resultVector[1]);
        return resultVector;

        //double Kp;
        //if(speculativeXPos==speculativeYPos)
    }

    private static boolean checkCollisionVV(Wall wall, double speculativeXPos, double speculativeYPos, double currentYPos, double Yw){
        return min(currentYPos, speculativeYPos) <= Yw+0.01 && Yw-0.01 <= max(currentYPos, speculativeYPos) &&
                min(wall.getY1(), wall.getY2()) <= Yw+0.01 && Yw-0.01 <= max(wall.getY1(), wall.getY2()) &&
                min(wall.getX1(), wall.getX2()) <= speculativeXPos+0.01 && max(wall.getX1(), wall.getX2()) >= speculativeXPos-0.01;
    }
    private static boolean checkCollisionWV(Wall wall, double speculativeXPos, double speculativeYPos, double currentXPos, double currentYPos, double Yp){
        return min(wall.getY1(), wall.getY2()) <= Yp+0.01 && Yp-0.01 <= max(wall.getY1(), wall.getY2()) &&
                min(currentYPos, speculativeYPos) <= Yp+0.01 && Yp-0.01 <= max(currentYPos, speculativeYPos) &&
                min(currentXPos, speculativeXPos) <= wall.getX1()+0.01 && max(currentXPos, speculativeXPos) >= wall.getX1()-0.01;
    }
    private static boolean checkCollisionNV(Wall wall, double speculativeXPos, double speculativeYPos, double currentXPos, double currentYPos, double collideX, double collideY){
        return min(wall.getX1(), wall.getX2()) <= collideX+0.01 && min(speculativeXPos, currentXPos) <= collideX+0.01 &&
                max(wall.getX1(), wall.getX2()) >= collideX-0.01 && max(speculativeXPos, currentXPos) >= collideX+0.01 &&
                min(wall.getY1(), wall.getY2()) <= collideY+0.01 && min(speculativeYPos, currentYPos) <= collideY+0.01 &&
                max(wall.getY1(), wall.getY2()) >= collideY-0.01 && max(speculativeYPos, currentYPos) >= collideY+0.01;
    }

    //public static void main(String[] args) {
    //    collide(400,198,401.5,200, new Wall(200,200,800, 200), 5);
    //}
}
