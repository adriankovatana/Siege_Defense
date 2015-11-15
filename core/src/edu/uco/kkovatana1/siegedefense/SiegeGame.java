package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class SiegeGame {
    protected int level;
    protected int gold;

    protected UnitUpgrades footman;

    public SiegeGame(){
        level = 0;
        gold = 100;

        footman = new UnitUpgrades(Globals.UnitType.FOOTMAN,50,5,10,10,10,
                Gdx.graphics.getWidth()*0.18f,Gdx.graphics.getHeight()*0.8f,
                GameMain.assetManager.get("characters/icons/footmanicon.png", Texture.class));
    }

    public boolean purchaseUnit(UnitUpgrades unit){
        if(gold - unit.cost < 0){
            return false;
        } else {
            unit.purchased = true;
            gold = gold - unit.cost;
            unit.setTouchable(Touchable.enabled);
        }
        return true;
    }

    public boolean upgradeDamage(UnitUpgrades unit){
        if(gold - unit.damageCost < 0)
            return false;
        else {
            unit.damage = unit.damage + (unit.damage * 0.1f);
            gold = gold - unit.damageCost;
            unit.damageCost = unit.damageCost + (int)(unit.damageCost * 0.1f);
        }
        return true;
    }

    public boolean upgradeHealth(UnitUpgrades unit){
        if(gold - unit.healthCost < 0)
            return false;
        else {
            unit.health = unit.health + (unit.health * 0.1f);
            gold = gold - unit.healthCost;
            unit.healthCost = unit.healthCost + (int)(unit.healthCost * 0.1f);
        }
        return true;
    }
}
