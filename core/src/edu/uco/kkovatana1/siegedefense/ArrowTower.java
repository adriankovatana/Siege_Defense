package edu.uco.kkovatana1.siegedefense;

public class ArrowTower extends Tower {
    public ArrowTower(float x, float y){
        super(x,y,"characters/towers/tower.png",5,600);
        this.iconPath = "";
        this.projectilePath = "characters/projectiles/arrow.png";
        this.type = Globals.TowerType.ARROW;
    }
}
