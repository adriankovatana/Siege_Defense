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

    public SiegeGameUI(final SiegeGameScreen screen){
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
        this.unitDeployer = this.screen.unitDeployer;
        initUnitDeployer();
        unpausedGroup.addActor(this.unitDeployer);

        //Paused group
        pauseOverlay = new TexturedActor("ui/pauseoverlay.png");
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

    public void initUnitDeployer(){

        unitDeployer.cell1.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(unitDeployer.cell1.unit != null) {
                    SiegeGameUI.this.screen.addUnit(unitDeployer.cell1.unit, unitDeployer.cell1.getStartPosition(),
                            SiegeGameScreen.wall.getPosition(0));
                    unitDeployer.cell1.setOnCooldown();
                }
                return true;
            }
        });

        unitDeployer.cell2.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(unitDeployer.cell2.unit != null) {
                    SiegeGameUI.this.screen.addUnit(unitDeployer.cell2.unit, unitDeployer.cell2.getStartPosition(),
                            SiegeGameScreen.wall.getPosition(1));
                    unitDeployer.cell2.setOnCooldown();
                }
                return true;
            }
        });

        unitDeployer.cell3.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(unitDeployer.cell3.unit != null) {
                    SiegeGameUI.this.screen.addUnit(unitDeployer.cell3.unit, unitDeployer.cell3.getStartPosition(),
                            SiegeGameScreen.wall.getPosition(2));
                    unitDeployer.cell3.setOnCooldown();
                }
                return true;
            }
        });

        unitDeployer.cell4.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(unitDeployer.cell4.unit != null) {
                    SiegeGameUI.this.screen.addUnit(unitDeployer.cell4.unit, unitDeployer.cell4.getStartPosition(),
                            SiegeGameScreen.wall.getPosition(3));
                    unitDeployer.cell4.setOnCooldown();
                }
                return true;
            }
        });

        unpausedGroup.addActor(unitDeployer);
    }
}
