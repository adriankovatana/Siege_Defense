package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Footman extends Unit {
    protected TextureRegion iconRegion;

    public Footman(float x, float y, float damage, float health) {
        super(x, y, "characters/units/footman200.atlas", damage, health, 50.0f, 100.0f);
        this.type = Globals.UnitType.FOOTMAN;
    }
}
