package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UnitDeployerCell extends Group {
    protected TexturedActor cellOutline;
    protected Image icon;
    protected TexturedActor cooldownOverlay;
    protected Unit unit;

    public UnitDeployerCell(float x, float y){
        cellOutline = new TexturedActor(GameMain.assetManager.get("ui/deployerbox.png", Texture.class));
        cellOutline.setPosition(x-3, y-3);
        icon = new Image();
        icon.setPosition(x, y);
        cooldownOverlay = new TexturedActor(GameMain.assetManager.get("ui/deployercooldown.png", Texture.class)){
            @Override
            public void draw(Batch batch, float parentAlpha){
                super.draw(batch, parentAlpha);

            }
        };
        cooldownOverlay.setPosition(x, y);
        cooldownOverlay.setVisible(false);

        this.addActor(cellOutline);
        this.addActor(icon);
        this.addActor(cooldownOverlay);
    }

    public void setUnit(Unit unit){
        this.unit = unit;
        this.unit.setPosition(cooldownOverlay.getX()-(unit.getWidth()/2 - unit.hitBox.radius),
                cellOutline.getY()+cellOutline.getHeight()-(unit.getHeight()/2 - unit.hitBox.radius));
        icon.setDrawable(new TextureRegionDrawable(new TextureRegion(GameMain.assetManager.get(unit.iconPath, Texture.class))));
    }
}
