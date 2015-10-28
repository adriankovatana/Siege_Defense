package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PauseOverlay extends Image{
    private boolean paused;

    public PauseOverlay(Texture texture, float x, float y){
        super(texture);
        this.setPosition(x,y);
        this.paused = false;
        this.setVisible(false);
    }

    public void setPaused(boolean b){
        this.paused = b;
        this.setVisible(this.paused);
    }

    public boolean isPaused(){
        return this.paused;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        if(this.paused)
            super.draw(batch, alpha);
    }
}
