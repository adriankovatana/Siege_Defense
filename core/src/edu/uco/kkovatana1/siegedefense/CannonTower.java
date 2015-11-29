package edu.uco.kkovatana1.siegedefense;

public class CannonTower extends Tower{
    public CannonTower(float x, float y){
        super(x,y,"characters/towers/cannon.png",5,550);
        this.iconPath = "";
        this.projectilePath = "characters/projectiles/fireball.png";
        this.type = Globals.TowerType.CANNON;
        this.damageType = Globals.DamageType.SIEGE;
    }
}
