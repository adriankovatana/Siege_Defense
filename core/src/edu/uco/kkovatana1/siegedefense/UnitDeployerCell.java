package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class UnitDeployerCell extends Actor {
    protected TextureRegion cellOutline;
    protected TextureRegion icon;
    protected TextureRegion cooldownOverlay;
    protected boolean onCooldown;
    protected float alphaTime;
    protected UnitUpgrades unit;

    public UnitDeployerCell(float x, float y){
        onCooldown = false;
        alphaTime = 0;
        cellOutline = new TextureRegion(GameMain.assetManager.get("ui/deployerbox.png", Texture.class));
        cooldownOverlay = new TextureRegion(GameMain.assetManager.get("ui/deployercooldown.png", Texture.class));
        this.setPosition(x,y);
        this.setBounds(x, y, cooldownOverlay.getRegionWidth(), cooldownOverlay.getRegionHeight());
        this.unit = null;
        this.setTouchable(Touchable.disabled);
    }

    public void setUnit(UnitUpgrades unit){
        this.unit = unit;
        if(unit == null){
            this.setTouchable(Touchable.disabled);
        } else {
            this.setTouchable(Touchable.enabled);
        }
    }

    public Vector2 getStartPosition(){
        return new Vector2(getX() - 50f,
                getY() + getHeight() - 50f);
    }

    public void setOnCooldown(){
        onCooldown = true;
        this.setTouchable(Touchable.disabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(cellOutline, getX()-3, getY()-3);
        if(unit != null)
            batch.draw(unit.textureRegion, getX(), getY());
        if (onCooldown) {
            if(!Globals.PAUSED)
                alphaTime += Gdx.graphics.getDeltaTime();
            if (alphaTime >= 5) {
                alphaTime = 0;
                onCooldown = false;
                this.setTouchable(Touchable.enabled);
            } else {
                batch.draw(cooldownOverlay, getX(), getY(),
                        getWidth(), getHeight() - (getHeight() * (alphaTime / 5)));
            }
        }
    }
}
