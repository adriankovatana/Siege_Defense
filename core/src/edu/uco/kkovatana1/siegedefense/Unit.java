package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.graphics.Color;

public class Unit extends Actor {
    protected Animator animator;
    protected Circle hitBox;
    protected Circle rangeBox;
    protected ShapeRenderer shapeRenderer;

    public Unit(float x, float y, String atlasFilePath, float hitBoxRadius, float rangeBoxRadius){
        this.setPosition(x,y);
        this.animator = new Animator(atlasFilePath);
        this.setWidth(this.animator.getWidth());
        this.setHeight(this.animator.getHeight());
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        this.hitBox = new Circle(this.getX()+this.getOriginX(),this.getY()+this.getOriginY(),hitBoxRadius);
        this.rangeBox = new Circle(this.getX()+this.getOriginX(),this.getY()+this.getOriginY(),rangeBoxRadius);
        this.shapeRenderer = new ShapeRenderer();
        //this.setDebug(true);
    }

    @Override
    public void draw(Batch batch, float alpha){
        if(this.getDebug()) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.circle(this.rangeBox.x, this.rangeBox.y, this.rangeBox.radius);
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.circle(this.hitBox.x, this.hitBox.y, this.hitBox.radius);
            shapeRenderer.end();
        } else {
            animator.draw(batch, this.getX(), this.getY());
        }
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return Vector2.dst(this.getOriginX(),this.getOriginY(),x,y) <= this.hitBox.radius ? this : null;
        /*return x >= getOriginX() && x < (getWidth() - getOriginX())
                && y >= getOriginY() && y < (getHeight() - getOriginY()) ? this : null;*/
    }
}
