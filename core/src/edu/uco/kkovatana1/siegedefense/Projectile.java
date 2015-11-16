package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class Projectile extends TexturedActor{
    private Unit target;
    private float damage;
    private float range;
    private float movementSpeed;

    public Projectile(float x, float y, float damage, float range, Unit target, String filePath) {
        super(filePath);
        this.setPosition(x, y);
        this.damage = damage;
        this.target = target;
        this.range = range;
        this.movementSpeed = 0.5f;
        //unit.getX()+unit.getOriginX(),unit.getY()+unit.getOriginY()
        MoveToAction action = new MoveToAction() {
            @Override
            protected void end() {
                super.end();
                if(Projectile.this.target != null)
                    Projectile.this.target.takeDamage(Projectile.this.damage);
                Projectile.this.remove();
            }
        };
        action.setPosition(target.getX()+target.getOriginX(),target.getY()+target.getOriginY());
        action.setDuration((Vector2.dst(getX(),getY(),target.getX()+target.getOriginX(),target.getY()+target.getOriginY())/range)*movementSpeed);
        this.addAction(action);
    }
}
