package edu.uco.kkovatana1.siegedefense;

public class Footman extends Unit {

    public Footman(float x, float y, Wall target) {
        super(x, y, "characters/units/footman200.atlas", 50.0f, 100.0f, target);
        this.health = this.maxHealth = 10;
        this.damage = 5;
    }
}
