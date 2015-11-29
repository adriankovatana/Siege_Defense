package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Wizard extends Unit{
    protected Vector2 target;
    protected String projectilePath;

    public Wizard(float x, float y, float damage, float health, Vector2 target) {
        super(x, y, "characters/units/wizard.atlas", damage, health, 50.0f, 400.0f);
        this.type = Globals.UnitType.WIZARD;
        this.target = target;
        this.projectilePath = "characters/projectiles/orb.png";
        this.movementSpeed = 2.5f;
        this.weakness = Globals.DamageType.PIERCING;
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
                            parentGroup.addActor(new Projectile(getX()+getOriginX(), getY()+getOriginY(), 5, rangeBox.radius, target, projectilePath));
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
}
