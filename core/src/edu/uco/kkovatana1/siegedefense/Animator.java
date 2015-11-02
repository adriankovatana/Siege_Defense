package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {
    private int width;
    private int height;
    private float stateTime;
    private Globals.EntityState state;
    private Globals.Direction direction;

    private TextureAtlas textureAtlas;
    private TextureRegion currentFrame;
    private TextureRegion standing;
    private TextureRegion[] walkingN;
    private TextureRegion[] walkingNE;
    private TextureRegion[] walkingE;
    private TextureRegion[] walkingSE;
    private TextureRegion[] walkingS;
    private TextureRegion[] attackingN;
    private TextureRegion[] attackingNE;
    private TextureRegion[] attackingE;
    private TextureRegion[] attackingSE;
    private TextureRegion[] attackingS;
    private TextureRegion[] dyingN;
    private TextureRegion[] dyingS;
    private Animation walkAnim;
    private Animation atkAnim;
    private Animation dthAnim;

//    private Animation walkAnimN;
//    private Animation walkAnimNE;
//    private Animation walkAnimE;
//    private Animation walkAnimSE;
//    private Animation walkAnimS;
//    private Animation atkAnimN;
//    private Animation atkAnimNE;
//    private Animation atkAnimE;
//    private Animation atkAnimSE;
//    private Animation atkAnimS;
//    private Animation deathAnimN;
//    private Animation getDeathAnimS;

    public Animator(String atlasFilePath){
        this.textureAtlas = new TextureAtlas(Gdx.files.internal(atlasFilePath));

        //Standing initial
        this.standing = this.textureAtlas.findRegion("standingS");
        this.width = this.standing.getRegionWidth();
        this.height = this.standing.getRegionHeight();
        this.stateTime = 0f;
        this.state = Globals.EntityState.STANDING;
        this.direction = Globals.Direction.S;

        //Walking South
        TextureRegion textureRegion = this.textureAtlas.findRegion("walkingS");
        TextureRegion[][] tmp = textureRegion
                .split(textureRegion.getRegionWidth(), textureRegion.getRegionHeight()/4);
        this.walkingS = new TextureRegion[4];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 1; j++) {
                this.walkingS[index++] = tmp[i][j];
            }
        }
        this.walkAnim = new Animation(Globals.ANIMATIONRATE, this.walkingS);

        //Walking North
        textureRegion = this.textureAtlas.findRegion("walkingN");
        tmp = textureRegion
                .split(textureRegion.getRegionWidth(), textureRegion.getRegionHeight()/4);
        this.walkingN = new TextureRegion[4];
        index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 1; j++) {
                this.walkingN[index++] = tmp[i][j];
            }
        }

        //Attacking South
        textureRegion = this.textureAtlas.findRegion("attackingS");
        tmp = textureRegion
                .split(textureRegion.getRegionWidth(), textureRegion.getRegionHeight()/4);
        this.attackingS = new TextureRegion[4];
        index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 1; j++) {
                this.attackingS[index++] = tmp[i][j];
            }
        }
        this.atkAnim = new Animation(Globals.ANIMATIONRATE, this.attackingS);

        //Attacking North
        textureRegion = this.textureAtlas.findRegion("attackingN");
        tmp = textureRegion
                .split(textureRegion.getRegionWidth(), textureRegion.getRegionHeight()/4);
        this.attackingN = new TextureRegion[4];
        index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 1; j++) {
                this.attackingN[index++] = tmp[i][j];
            }
        }

        //Dying South
        textureRegion = this.textureAtlas.findRegion("dyingS");
        tmp = textureRegion
                .split(textureRegion.getRegionWidth()/5, textureRegion.getRegionHeight());
        this.dyingS = new TextureRegion[5];
        index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                this.dyingS[index++] = tmp[i][j];
            }
        }
        this.dthAnim = new Animation(Globals.ANIMATIONRATE, this.dyingS);
        this.dthAnim.setPlayMode(Animation.PlayMode.NORMAL);

        //Dying North
        textureRegion = this.textureAtlas.findRegion("dyingN");
        tmp = textureRegion
                .split(textureRegion.getRegionWidth()/5, textureRegion.getRegionHeight());
        this.dyingN = new TextureRegion[5];
        index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                this.dyingN[index++] = tmp[i][j];
            }
        }
    }

    public void draw(Batch batch, float x, float y){
        if(!Globals.PAUSED) {
            this.stateTime += Gdx.graphics.getDeltaTime();
            if (this.state == Globals.EntityState.STANDING) {
                this.currentFrame = this.standing;
            } else if (this.state == Globals.EntityState.WALKING) {
                this.currentFrame = this.walkAnim.getKeyFrame(this.stateTime, true);
            } else if (this.state == Globals.EntityState.ATTACKING) {
                this.currentFrame = this.atkAnim.getKeyFrame(this.stateTime, true);
            } else if (this.state == Globals.EntityState.DYING) {
                if(!this.dthAnim.isAnimationFinished(stateTime))
                    this.currentFrame = this.dthAnim.getKeyFrame(this.stateTime, true);
            }
        }
        batch.draw(this.currentFrame, x, y);
        //batch.draw(this.currentFrame, x, y, this.width/2, this.height/2, this.width,this.height, 2.15f, 2.15f, 0);
    }

    public float getWidth(){
        return this.width;
    }

    public float getHeight(){
        return this.height;
    }

    public Globals.EntityState getState(){
        return this.state;
    }

    public void setState(Globals.EntityState state){
        this.state = state;
        if(this.state == Globals.EntityState.DYING)
            this.stateTime = 0;
    }

    public Globals.Direction getDirection(){
        return this.direction;
    }

    public void setDirection(Globals.Direction direction){
        this.direction = direction;
        switch (this.direction){
            case N:
                dthAnim = new Animation(Globals.ANIMATIONRATE,this.dyingN);
                atkAnim = new Animation(Globals.ANIMATIONRATE,this.attackingN);
                walkAnim = new Animation(Globals.ANIMATIONRATE,this.walkingN);
                standing = this.textureAtlas.findRegion("standingN");
                break;
            case NE:
                break;
            case E:
                break;
            case SE:
                break;
            case S:
                dthAnim = new Animation(Globals.ANIMATIONRATE,this.dyingS);
                atkAnim = new Animation(Globals.ANIMATIONRATE,this.attackingS);
                walkAnim = new Animation(Globals.ANIMATIONRATE,this.walkingS);
                standing = this.textureAtlas.findRegion("standingS");
                break;
            case SW:
                break;
            case W:
                break;
            case NW:
                break;
            default:
                Gdx.app.log("ACTOR setDirection", "direction not recognized");
                break;
        }
        this.stateTime = 0f;
    }
}
