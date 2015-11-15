package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class UnitUpgrades extends TexturedActor{
    protected boolean purchased;
    protected int cost;
    protected float damage;
    protected int damageCost;
    protected float health;
    protected int healthCost;
    protected Globals.UnitType type;

    public UnitUpgrades(Globals.UnitType type, int cost, float damage, int damageCost, float health,
                        int healthCost, float x, float y, Texture texture){
        super(texture);
        this.setPosition(x, y);
        this.setBounds(x, y, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
        purchased = false;
        this.cost = cost;
        this.damage = damage;
        this.damageCost = damageCost;
        this.health = health;
        this.healthCost = healthCost;
        this.type = type;
        this.setTouchable(Touchable.disabled);
    }
}
