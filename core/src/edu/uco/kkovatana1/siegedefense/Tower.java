package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Tower extends TexturedActor{
    protected String iconPath;
    protected String projectilePath;
    protected float damage;
    protected Circle rangeBox;
    protected SequenceAction seqAction;
    protected Globals.TowerType type;
    protected float alphaTime;
    protected boolean onCooldown;

    public Tower(float x, float y, String filePath, float damage, float rangeBoxRadius){
        super(filePath);
        this.setPosition(x, y);
        this.setWidth(textureRegion.getRegionWidth());
        this.setHeight(textureRegion.getRegionHeight());
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        this.damage = damage;
        this.rangeBox = new Circle(this.getX()+this.getOriginX(),this.getY()+this.getOriginY(),rangeBoxRadius);
        this.seqAction = new SequenceAction();
        this.iconPath = "";
        this.projectilePath = "characters/projectiles/arrow.png";
        this.type = Globals.TowerType.NONE;
        this.onCooldown = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch,parentAlpha);
        if (onCooldown) {
            if(!Globals.PAUSED)
                alphaTime += Gdx.graphics.getDeltaTime();
            if (alphaTime >= 2) {
                alphaTime = alphaTime - 1;
                onCooldown = false;
            }
        }

    }

    public Projectile shoot(Unit unit){
        onCooldown = true;
        return new Projectile(getX()+getOriginX(), getY()+getOriginY(), 5, rangeBox.radius, unit, projectilePath);
    }
}
