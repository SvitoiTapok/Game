package com.example.game2.Map;

import java.util.ArrayList;

public class Map {
    private static Map INSTANCE;
    private final ArrayList<Wall> walls = new ArrayList<Wall>();

    private Map(){
        walls.add(new Wall(200,200,800, 200));
        walls.add(new Wall(200,200,200, 800));
        walls.add(new Wall(800,200,800, 800));
        walls.add(new Wall(200,800,800, 800));
    }

    public static Map getINSTANCE() {
        if(INSTANCE == null)
            INSTANCE = new Map();
        return INSTANCE;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
