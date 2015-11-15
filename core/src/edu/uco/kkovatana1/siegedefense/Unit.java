package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Unit extends Actor {
    protected Animator animator;
    protected Circle hitBox;
    protected Circle rangeBox;
    protected SequenceAction seqAction;
    protected float movementSpeed;
    protected ShapeRenderer shapeRenderer;
    protected float health;
    protected float maxHealth;
    protected float damage;
    protected boolean attacked;
    protected Globals.UnitType type;
    protected String iconPath;

    public Unit(float x, float y, String atlasFilePath, float damage, float health, float hitBoxRadius, float rangeBoxRadius){
        this.setPosition(x,y);
        this.animator = new Animator(atlasFilePath);
        this.setWidth(this.animator.getWidth());
        this.setHeight(this.animator.getHeight());
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        this.hitBox = new Circle(this.getX()+this.getOriginX(),this.getY()+this.getOriginY(),hitBoxRadius);
        this.rangeBox = new Circle(this.getX()+this.getOriginX(),this.getY()+this.getOriginY(),rangeBoxRadius);
        this.seqAction = new SequenceAction();
        this.movementSpeed = 1f;
        this.health = this.maxHealth = health;
        this.damage = damage;
        this.attacked = false;
        this.type = Globals.UnitType.NONE;
        this.iconPath = "";

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
//            animator.draw(batch, this.getX(), this.getY());
            if(!Globals.PAUSED) {
                animator.stateTime += Gdx.graphics.getDeltaTime();
                if (animator.state == Globals.EntityState.STANDING) {
                    animator.currentFrame = animator.standing;
                } else if (animator.state == Globals.EntityState.WALKING) {
                    animator.currentFrame = animator.walkAnim.getKeyFrame(animator.stateTime, true);
                } else if (animator.state == Globals.EntityState.ATTACKING) {
                    if(!animator.atkAnim.isAnimationFinished(animator.stateTime)) {
                        animator.currentFrame = animator.atkAnim.getKeyFrame(animator.stateTime, true);
                        attacked = false;
                    } else {
                        if(!attacked) {
                            SiegeGameScreen.wall.takeDamage(this.damage);
                            attacked = true;
                            animator.stateTime = 0;
                        }
                    }
                } else if (animator.state == Globals.EntityState.DYING) {
                    if(!animator.dthAnim.isAnimationFinished(animator.stateTime))
                        animator.currentFrame = animator.dthAnim.getKeyFrame(animator.stateTime, true);
                }
            }
            batch.draw(animator.currentFrame, this.getX(), this.getY());
        }
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (touchable && getTouchable() != Touchable.enabled) return null;
        return Vector2.dst(this.getOriginX(), this.getOriginY(), x, y) <= this.hitBox.radius ? this : null;
        /*return x >= getOriginX() && x < (getWidth() - getOriginX())
                && y >= getOriginY() && y < (getHeight() - getOriginY()) ? this : null;*/
    }

    public void moveToPosition(Vector2 position, boolean sequenced){
        MoveToAction action = new MoveToAction(){
            @Override
            protected void begin(){
                super.begin();
                Unit.this.animator.setState(Globals.EntityState.WALKING);
            }
            @Override
            protected void end(){
                super.end();
                Unit.this.animator.setState(Globals.EntityState.ATTACKING);
            }
        };
        action.setPosition(position.x, position.y - this.rangeBox.radius);
        action.setDuration(this.movementSpeed*4);
        if(sequenced)
            seqAction.addAction(action);
        else
            this.addAction(action);
    }

    public void takeDamage(float damage){
        this.health -= damage;
        if(this.health <= 0){
            this.health = 0;
        }
    }
}
