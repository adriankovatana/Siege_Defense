package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SiegeGameUI extends Group{
    private SiegeGameScreen screen;
    private Group unpausedGroup;
    private Group pausedGroup;
    private Image settings;
    private TexturedActor pauseOverlay;
    private Image resumeBtn;
    private Image quitBtn;
    private UnitDeployer unitDeployer;

    public SiegeGameUI(SiegeGameScreen screen){
        this.screen = screen;
        unpausedGroup = new Group();
        pausedGroup = new Group();
        this.addActor(unpausedGroup);
        this.addActor(pausedGroup);
        pausedGroup.setVisible(false);

        //Unpaused group
        settings = new Image(GameMain.assetManager.get("ui/buttons/settings.png", Texture.class));
        settings.setPosition(Gdx.graphics.getWidth() * 0.92f, Gdx.graphics.getHeight() * 0.01f);
        settings.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Globals.PAUSED = true;
                pausedGroup.setVisible(true);
                return true;
            }
        });
        unpausedGroup.addActor(settings);

        unitDeployer = new UnitDeployer();
        unitDeployer.cell1.icon.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Add unit to game
                return true;
            }
        });

        //Paused group
        pauseOverlay = new TexturedActor(GameMain.assetManager.get("ui/pauseoverlay.png", Texture.class));
        pauseOverlay.setPosition(0, 0);
        pausedGroup.addActor(pauseOverlay);

        resumeBtn = new Image(GameMain.assetManager.get("ui/buttons/resume.png", Texture.class));
        resumeBtn.setPosition(Gdx.graphics.getWidth()*0.25f, Gdx.graphics.getHeight()*0.55f);
        resumeBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Globals.PAUSED = false;
                pausedGroup.setVisible(false);
                return true;
            }
        });
        pausedGroup.addActor(resumeBtn);

        quitBtn = new Image(GameMain.assetManager.get("ui/buttons/quit.png", Texture.class));
        quitBtn.setPosition(Gdx.graphics.getWidth()*0.25f, Gdx.graphics.getHeight()*0.45f);
        quitBtn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SiegeGameUI.this.screen.quit();
                return true;
            }
        });
        pausedGroup.addActor(quitBtn);
    }
}
