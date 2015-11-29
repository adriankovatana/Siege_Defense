package edu.uco.kkovatana1.siegedefense;

public class MageTower extends Tower{
    public MageTower(float x, float y){
        super(x,y,"characters/towers/mage.png",5,700);
        this.iconPath = "";
        this.projectilePath = "characters/projectiles/magic.png";
        this.type = Globals.TowerType.MAGE;
        this.damageType = Globals.DamageType.MAGIC;
    }
}
