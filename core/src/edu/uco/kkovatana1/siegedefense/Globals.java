package edu.uco.kkovatana1.siegedefense;

public class Globals {
    public static final float ANIMATIONRATE = 0.15f;
    public static boolean PAUSED = false;
    public static float DIFFICULTY = 1.0f;

    public enum EntityState{
        STANDING, WALKING, ATTACKING, DYING
    }

    public enum Direction{
        N,NE,E,SE,S,SW,W,NW
    }

    public enum UnitType{
        NONE, FOOTMAN, GRUNT,
        ARCHER,
        WIZARD
    }

    public enum TowerType{
        NONE, ARROW, CANNON, MAGE
    }

    public enum GameState{
        LOADOUT, PLAYING
    }

    public enum DamageType{
        NONE, PIERCING, SIEGE, MAGIC
    }
}
