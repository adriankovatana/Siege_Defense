package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class SiegeGame {
    protected int level;
    protected int gold;

    protected UnitUpgrades footman;
    protected UnitUpgrades archer;
    protected UnitUpgrades wizard;

    public SiegeGame(){
        level = 1;
        gold = 150;

        footman = new UnitUpgrades(Globals.UnitType.FOOTMAN,50,6,10,60,10,5,50,
                Gdx.graphics.getWidth()*0.1138f,Gdx.graphics.getHeight()*0.825f,
                "characters/icons/footmanicon.png");
        archer = new UnitUpgrades(Globals.UnitType.ARCHER,50,6,10,50,10,5,50,
                Gdx.graphics.getWidth()*0.1138f,Gdx.graphics.getHeight()*0.5625f,
                "characters/icons/archericon.png");
        wizard = new UnitUpgrades(Globals.UnitType.WIZARD,50,7,10,40,10,5,50,
                Gdx.graphics.getWidth()*0.1138f,Gdx.graphics.getHeight()*0.3094f,
                "characters/icons/wizardicon.png");

        Globals.DIFFICULTY = 1.0f;
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
            unit.damage++;
            gold -= unit.damageCost;
            unit.damageCost = (int)(unit.damageCost + (unit.damageCost * 0.2f));
        }
        return true;
    }

    public boolean upgradeHealth(UnitUpgrades unit){
        if(gold - unit.healthCost < 0)
            return false;
        else {
            unit.health += 10;
            gold = gold - unit.healthCost;
            unit.healthCost = (int)(unit.healthCost + (unit.healthCost * 0.2f));
        }
        return true;
    }

    public boolean upgradeAmount(UnitUpgrades unit){
        if(gold - unit.amountCost < 0)
            return false;
        else {
            unit.amount++;
            gold -= unit.amountCost;
            unit.amountCost = (int)(unit.amountCost + (unit.amountCost * 0.2f));
        }
        return true;
    }
}
