package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class Projectile extends TexturedActor{
    private Unit target;
    private float damage;
    private float range;
    private float movementSpeed;
    private Globals.DamageType type;

    public Projectile(float x, float y, float damage, float range, Unit target, Globals.DamageType type, String filePath) {
        super(filePath);
        this.setPosition(x, y);
        this.damage = damage;
        this.target = target;
        this.range = range;
        this.type = type;
        this.movementSpeed = 0.5f;
        //unit.getX()+unit.getOriginX(),unit.getY()+unit.getOriginY()
        MoveToAction action = new MoveToAction() {
            @Override
            protected void end() {
                super.end();
                if(Projectile.this.target != null)
                    Projectile.this.target.takeDamage(Projectile.this.damage,Projectile.this.type);
                Projectile.this.remove();
            }
        };
        action.setPosition(target.getX()+target.getOriginX(),target.getY()+target.getOriginY());
        action.setDuration((Vector2.dst(getX(),getY(),target.getX()+target.getOriginX(),target.getY()+target.getOriginY())/range)*movementSpeed);
        this.addAction(action);
    }

    public Projectile(float x, float y, float damage, float range, Vector2 target, String filePath) {
        super(filePath);
        this.setPosition(x, y);
        this.damage = damage;
        this.range = range;
        this.movementSpeed = 0.5f;
        //unit.getX()+unit.getOriginX(),unit.getY()+unit.getOriginY()
        MoveToAction action = new MoveToAction() {
            @Override
            protected void end() {
                super.end();
                SiegeGameScreen.wall.takeDamage(Projectile.this.damage);
                Projectile.this.remove();
            }
        };
        action.setPosition(target.x + 100,target.y);
        action.setDuration((Vector2.dst(getX(),getY(),target.x,target.y)/range)*movementSpeed);
        this.addAction(action);
    }
}
