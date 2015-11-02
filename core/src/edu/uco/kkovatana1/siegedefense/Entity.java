package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Entity extends Actor {
    protected Animator animator;

    public Entity(float x, float y, String atlasFilePath, float boxDownScale){
        this.setPosition(x,y);
        this.animator = new Animator(atlasFilePath);
        this.setWidth(this.animator.getWidth());
        this.setHeight(this.animator.getHeight());
        this.setOrigin(this.getWidth() * boxDownScale, this.getHeight() * boxDownScale);
        //this.setDebug(true);
    }

    @Override
    public void draw(Batch batch, float alpha){
        animator.draw(batch, this.getX(), this.getY());
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return x >= getOriginX() && x < (getWidth() - getOriginX())
                && y >= getOriginY() && y < (getHeight() - getOriginY()) ? this : null;
    }
}
