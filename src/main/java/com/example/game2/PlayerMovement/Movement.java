package com.example.game2.PlayerMovement;


import static java.lang.Math.*;
import com.example.game2.Map.Wall;


public class Movement {
    public static double[] collide(double currentXPos, double currentYPos, double  crossXPos, double crossYPos,
                                   Wall wall, double step)
    {
        double[] wallVector = {wall.getX1()-wall.getX2(), wall.getY1()-wall.getY2()};
        double wallLength = sqrt(pow(wallVector[0], 2) + pow(wallVector[1],2));
        double[] playerVector = {crossXPos-currentXPos, crossYPos-currentYPos};
        double playerLength = sqrt(pow(playerVector[0], 2) + pow(playerVector[1],2));

        //System.out.println(wallLength);

        double scalarMultiplication = playerVector[0]*wallVector[0] + playerVector[1]*wallVector[1];
        double cosBetweenPlayerAndWall = scalarMultiplication/(wallLength*playerLength);
        if(cosBetweenPlayerAndWall<0)
            wallVector = new double[]{-wallVector[0], -wallVector[1]};
        double proectionLength = abs(cosBetweenPlayerAndWall*(step-playerLength));
        double[] unitWallVector = {wallVector[0]/wallLength, wallVector[1]/wallLength};
        double[] resultVector = {playerVector[0]+unitWallVector[0]*proectionLength,  playerVector[1]+unitWallVector[1]*proectionLength};
        //double angleBPAW = acos(cosBetweenPlayerAndWall);

        //System.out.println(playerLength);
        //System.out.println(playerVector[0]+" "+ playerVector[1]);
        //System.out.println(unitWallVector[0] + " " +unitWallVector[1]);
        //System.out.println(proectionLength);
        //System.out.println(resultVector[0]+" "+ resultVector[1]);
        //System.out.println(proectionLength);

        return resultVector;
    }

    //public static void main(String[] args) {
    //    collide(400,198,401.5,200, new Wall(200,200,800, 200), 5);
    //}
}
