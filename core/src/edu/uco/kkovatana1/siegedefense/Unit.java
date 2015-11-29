package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Unit extends Actor {
    protected UnitAnimator unitAnimator;
    protected Circle hitBox;
    protected Circle rangeBox;
    protected SequenceAction seqAction;
    protected float movementSpeed;
    protected ShapeRenderer shapeRenderer;
    protected float health;
    protected float maxHealth;
    protected float damage;
    protected boolean attacked;
    protected boolean dead;
    protected Globals.UnitType type;
    protected Globals.DamageType weakness;
    protected String iconPath;
    protected TextureRegion healthbarBack;
    protected TextureRegion healthbarFill;
    protected Group parentGroup;

    public Unit(float x, float y, String atlasFilePath, float damage, float health, float hitBoxRadius, float rangeBoxRadius){
        this.setPosition(x,y);
        this.unitAnimator = new UnitAnimator(atlasFilePath);
        this.setWidth(this.unitAnimator.getWidth());
        this.setHeight(this.unitAnimator.getHeight());
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        this.hitBox = new Circle(this.getX()+this.getOriginX(),this.getY()+this.getOriginY(),hitBoxRadius);
        this.rangeBox = new Circle(this.getX()+this.getOriginX(),this.getY()+this.getOriginY(),rangeBoxRadius);
        this.seqAction = new SequenceAction();
        this.movementSpeed = 4f;
        this.health = this.maxHealth = health;
        this.damage = damage;
        this.attacked = false;
        this.type = Globals.UnitType.NONE;
        this.weakness = Globals.DamageType.NONE;
        this.iconPath = "";
        this.dead = false;
        this.parentGroup = SiegeGameScreen.gameActorsGroup;

        this.healthbarBack = new TextureRegion(GameMain.assetManager.get("characters/units/healthbarbackground.png", Texture.class));
        this.healthbarFill = new TextureRegion(GameMain.assetManager.get("characters/units/healthbarfill.png", Texture.class));
        this.shapeRenderer = new ShapeRenderer();
        //this.setDebug(true);
    }

    @Override
    public void draw(Batch batch, float alpha){
        updateCircles();
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
//            unitAnimator.draw(batch, this.getX(), this.getY());
            if(!Globals.PAUSED) {
                unitAnimator.stateTime += Gdx.graphics.getDeltaTime();
                if (unitAnimator.state == Globals.EntityState.STANDING) {
                    unitAnimator.currentFrame = unitAnimator.standing;
                } else if (unitAnimator.state == Globals.EntityState.WALKING) {
                    unitAnimator.currentFrame = unitAnimator.walkAnim.getKeyFrame(unitAnimator.stateTime, true);
                } else if (unitAnimator.state == Globals.EntityState.ATTACKING) {
                    if(!unitAnimator.atkAnim.isAnimationFinished(unitAnimator.stateTime)) {
                        unitAnimator.currentFrame = unitAnimator.atkAnim.getKeyFrame(unitAnimator.stateTime, true);
                        attacked = false;
                    } else {
                        if(!attacked) {
                            SiegeGameScreen.wall.takeDamage(this.damage);
                            attacked = true;
                            unitAnimator.stateTime = 0;
                        }
                    }
                } else if (unitAnimator.state == Globals.EntityState.DYING) {
                    if(!unitAnimator.dthAnim.isAnimationFinished(unitAnimator.stateTime))
                        unitAnimator.currentFrame = unitAnimator.dthAnim.getKeyFrame(unitAnimator.stateTime, true);
                    else
                        this.dead = true;
                }
            }
            batch.draw(unitAnimator.currentFrame, this.getX(), this.getY());
            batch.draw(healthbarBack, this.getX()+60, this.getY()+24);
            batch.draw(healthbarFill, this.getX()+60 + 1, this.getY()+24 + 1, healthbarFill.getRegionWidth() * (health / maxHealth), healthbarFill.getRegionHeight());
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
                Unit.this.unitAnimator.setState(Globals.EntityState.WALKING);
            }
            @Override
            protected void end(){
                super.end();
                if(health > 0)
                    Unit.this.unitAnimator.setState(Globals.EntityState.ATTACKING);
            }
        };
        action.setPosition(position.x, position.y - this.rangeBox.radius);
        action.setDuration(this.movementSpeed);
        if(sequenced)
            seqAction.addAction(action);
        else
            this.addAction(action);
    }

    public void takeDamage(float damage, Globals.DamageType weakness){
        if(this.weakness == weakness)
            this.health -= (damage * 1.5f);
        else
            this.health -= damage;
        if(this.health <= 0){
            this.health = 0;
            if(unitAnimator.getState() != Globals.EntityState.DYING) {
                this.clearActions();
                this.unitAnimator.setState(Globals.EntityState.DYING);
            }
        }
    }

    protected void updateCircles(){
        rangeBox.setPosition(this.getX()+this.getOriginX(),this.getY()+this.getOriginY());
        hitBox.setPosition(this.getX()+this.getOriginX(),this.getY()+this.getOriginY());
    }


}
