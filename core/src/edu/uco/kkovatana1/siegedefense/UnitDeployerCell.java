package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class UnitDeployerCell extends Actor {
    protected TextureRegion cellOutline;
    protected TextureRegion cooldownOverlay;
    protected boolean onCooldown;
    protected float alphaTime;
    protected UnitUpgrades unit;
    protected float cooldown;
    protected int amount;
    protected BitmapFont amountText;
    protected boolean showAmount;

    public UnitDeployerCell(float x, float y){
        onCooldown = false;
        alphaTime = 0;
        cellOutline = new TextureRegion(GameMain.assetManager.get("ui/deployerbox.png", Texture.class));
        cooldownOverlay = new TextureRegion(GameMain.assetManager.get("ui/deployercooldown.png", Texture.class));
        this.setPosition(x,y);
        this.setBounds(x, y, cooldownOverlay.getRegionWidth(), cooldownOverlay.getRegionHeight());
        this.unit = null;
        this.cooldown = 9;
        this.amount = 0;
        this.setTouchable(Touchable.disabled);
        this.amountText = new BitmapFont();
        this.amountText.setColor(Color.BLACK);
        this.amountText.getData().setScale(2);
        this.showAmount = false;
    }

    public void setUnit(UnitUpgrades unit){
        this.unit = unit;
        if(unit == null){
            this.setTouchable(Touchable.disabled);
            this.amount = 0;
        } else {
            this.setTouchable(Touchable.enabled);
            this.amount = unit.amount;
        }
    }

    public Vector2 getStartPosition(){
        return new Vector2(getX() - 50f,
                getY() + getHeight() - 50f);
    }

    public void setOnCooldown(){
        onCooldown = true;
        this.setTouchable(Touchable.disabled);
        this.amount--;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(cellOutline, getX()-3, getY()-3);
        if(unit != null)
            batch.draw(unit.textureRegion, getX(), getY());
        if(amount <= 0)
            batch.draw(cooldownOverlay, getX(), getY());
        else if (onCooldown) {
            if(!Globals.PAUSED)
                alphaTime += Gdx.graphics.getDeltaTime();
            if (alphaTime >= cooldown) {
                alphaTime = alphaTime - cooldown;
                onCooldown = false;
                this.setTouchable(Touchable.enabled);
            } else {
                batch.draw(cooldownOverlay, getX(), getY(),
                        getWidth(), getHeight() - (getHeight() * (alphaTime / cooldown)));
            }
        }
        if(showAmount)
            amountText.draw(batch,""+amount,getX()-5+getWidth()/2,getY()-15);
    }
}
