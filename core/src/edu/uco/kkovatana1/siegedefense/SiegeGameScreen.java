package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SiegeGameScreen implements Screen {
    private GameMain game;
    private SpriteBatch batch;
    private Stage stage;

    private Texture backgroundTexture;
    private Texture settingsTexture;

    private Sprite background;
    private Image settings;

    private PauseOverlay pauseOverlay;
    private PauseOverlay resumeBtn;
    private PauseOverlay quitBtn;

    private Entity footman;
    private Entity footman2;
    private Entity footman3;

    public SiegeGameScreen(GameMain game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        Globals.PAUSED = false;

        //Background
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/siege.png"));
        background = new Sprite(backgroundTexture);
        settingsTexture = new Texture(Gdx.files.internal("ui/buttons/settings.png"));
        settings = new Image(settingsTexture);
        settings.setPosition(Gdx.graphics.getWidth() * 0.92f, Gdx.graphics.getHeight() * 0.955f);
        settings.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!Globals.PAUSED) {
                    Globals.PAUSED = !Globals.PAUSED;
                    pauseOverlay.setPaused(Globals.PAUSED);
                    resumeBtn.setPaused(Globals.PAUSED);
                    quitBtn.setPaused(Globals.PAUSED);
                    Gdx.app.log("Button", "Settings");
                    return true;
                }
                Gdx.app.log("Button", "Settings failed");
                return false;
            }
        });
        stage.addActor(settings);

        //Game actors for testing
        footman = new Entity(Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.25f,
                "characters/units/footman200.atlas", 0.25f);
        footman.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (footman.animator.getState() == Globals.EntityState.STANDING) {
                    footman.animator.setState(Globals.EntityState.ATTACKING);
                } else {
                    footman.animator.setState(Globals.EntityState.STANDING);
                }
                return true;
            }
        });
        stage.addActor(footman);

        footman2 = new Entity(Gdx.graphics.getWidth() * 0.4f, Gdx.graphics.getHeight() * 0.25f,
                "characters/units/footman200.atlas", 0.25f);
        footman2.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(footman2.animator.getState() == Globals.EntityState.STANDING) {
                    footman2.animator.setState(Globals.EntityState.WALKING);
                }
                else {
                    footman2.animator.setState(Globals.EntityState.STANDING);
                }
                return true;
            }
        });
        stage.addActor(footman2);

        footman3 = new Entity(Gdx.graphics.getWidth() * 0.7f, Gdx.graphics.getHeight() * 0.25f,
                "characters/units/footman200.atlas", 0.25f);
        footman3.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(footman3.animator.getState() == Globals.EntityState.STANDING) {
                    footman3.animator.setState(Globals.EntityState.DYING);
                }
                else {
                    footman3.animator.setState(Globals.EntityState.STANDING);
                }
                return true;
            }
        });
        stage.addActor(footman3);

        //PAUSE MENU
        //Pause Overlay
        pauseOverlay = new PauseOverlay(new Texture(Gdx.files.internal("ui/pauseoverlay.png")),0,0);
        stage.addActor(pauseOverlay);
        //Resume Button
        resumeBtn = new PauseOverlay(new Texture(Gdx.files.internal("ui/buttons/resume.png")),
                Gdx.graphics.getWidth()*0.25f, Gdx.graphics.getHeight()*0.55f);
        resumeBtn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if( Globals.PAUSED) {
                    Globals.PAUSED = ! Globals.PAUSED;
                    pauseOverlay.setPaused( Globals.PAUSED);
                    resumeBtn.setPaused( Globals.PAUSED);
                    quitBtn.setPaused( Globals.PAUSED);
                    Gdx.app.log("Button", "Resume");
                    return true;
                }
                Gdx.app.log("Button", "Resume failed");
                return false;
            }
        });
        stage.addActor(resumeBtn);
        //Quit Button
        quitBtn = new PauseOverlay(new Texture(Gdx.files.internal("ui/buttons/quit.png")),
                Gdx.graphics.getWidth()*0.25f, Gdx.graphics.getHeight()*0.45f);
        quitBtn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if( Globals.PAUSED) {
                    game.setScreen(game.startScreen);
                    return true;
                }
                return false;
            }
        });
        stage.addActor(quitBtn);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        //INPUT
        if(Globals.PAUSED){
            //Currently Nothing!
        }
        // Unpaused
        else{
            if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
                footman.animator.setDirection(Globals.Direction.W);
                footman2.animator.setDirection(Globals.Direction.W);
                footman3.animator.setDirection(Globals.Direction.W);
                Gdx.app.log("FOOTMAN", "West");
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                footman.animator.setDirection(Globals.Direction.N);
                footman2.animator.setDirection(Globals.Direction.N);
                footman3.animator.setDirection(Globals.Direction.N);
                Gdx.app.log("FOOTMAN", "North");
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
                footman.animator.setDirection(Globals.Direction.E);
                footman2.animator.setDirection(Globals.Direction.E);
                footman3.animator.setDirection(Globals.Direction.E);
                Gdx.app.log("FOOTMAN", "East");
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                footman.animator.setDirection(Globals.Direction.S);
                footman2.animator.setDirection(Globals.Direction.S);
                footman3.animator.setDirection(Globals.Direction.S);
                Gdx.app.log("FOOTMAN", "South");
            }
        }

        //UPDATE
        if(!Globals.PAUSED){
            stage.act();
        }

        //RENDER
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
