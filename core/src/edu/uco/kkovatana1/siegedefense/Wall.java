package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Wall extends Actor{
    protected float health;
    protected float maxHealth;
    protected TextureRegion healthbarBack;
    protected TextureRegion healthbarFill;
    protected Vector2[] positions;

//    protected ShapeRenderer shapeRenderer;

    public Wall(){
        this.setPosition(5, 1000);
        this.setWidth(Gdx.graphics.getWidth() - 10);
        this.setHeight(10f);
        this.health = this.maxHealth = 1200f;
        this.healthbarBack = new TextureRegion(GameMain.assetManager.get("ui/healthbarback.png", Texture.class));
        this.healthbarFill = new TextureRegion(GameMain.assetManager.get("ui/healthbarfill.png", Texture.class));
        this.positions = new Vector2[4];
        this.positions[0] = new Vector2(0,this.getY());
        this.positions[1] = new Vector2(Gdx.graphics.getWidth() * 0.25f, this.getY());
        this.positions[2] = new Vector2(Gdx.graphics.getWidth() * 0.5f, this.getY());
        this.positions[3] = new Vector2(Gdx.graphics.getWidth() * 0.75f, this.getY());
        /*this.positions[0] = new Vector2(Gdx.graphics.getWidth() * 0.125f,this.getY());
        this.positions[1] = new Vector2(Gdx.graphics.getWidth() * 0.375f, this.getY());
        this.positions[2] = new Vector2(Gdx.graphics.getWidth() * 0.625f, this.getY());
        this.positions[3] = new Vector2(Gdx.graphics.getWidth() * 0.875f, this.getY());*/

//        shapeRenderer = new ShapeRenderer();
//        this.setDebug(true);
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.draw(healthbarBack, this.getX(), this.getY() + 250f);
        batch.draw(healthbarFill, this.getX() + 1, this.getY() + 251f, healthbarFill.getRegionWidth() * (health / maxHealth), healthbarFill.getRegionHeight());
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.BLACK);
//        shapeRenderer.rect(this.getX(), Gdx.graphics.getHeight()-10,this.getWidth(),this.getHeight());
//        shapeRenderer.setColor(Color.RED);
//        shapeRenderer.rect(this.getX()+2, Gdx.graphics.getHeight()-10+2,(this.getWidth()-4), this.getHeight()-4);
//        shapeRenderer.setColor(Color.GREEN);
//        shapeRenderer.rect(this.getX()+2, Gdx.graphics.getHeight()-10+2,(this.getWidth()-4)*(this.health/this.maxHealth), this.getHeight()-4);
//        shapeRenderer.end();
    }

    public void takeDamage(float damage){
        this.health -= damage;
        if(this.health <= 0){
            this.health = 0;
        }
    }

    public float getHealth(){
        return this.health;
    }

    public Vector2 getPosition(int wallNum){
        return this.positions[wallNum];
    }
}
