package edu.uco.kkovatana1.siegedefense;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DefenseGameScreen implements Screen {
    private GameMain game;
    private SpriteBatch batch;
    private Stage stage;

    private Texture backgroundTexture;
    private Texture settingsTexture;

    private Sprite background;
    private Image settings;

    private boolean paused;
    private PauseOverlay pauseOverlay;
    private PauseOverlay resumeBtn;
    private PauseOverlay quitBtn;

    public DefenseGameScreen(GameMain game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        paused = false;

        //Background
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/basedefense.png"));
        background = new Sprite(backgroundTexture);
        settingsTexture = new Texture(Gdx.files.internal("ui/buttons/settings.png"));
        settings = new Image(settingsTexture);
        settings.setPosition(Gdx.graphics.getWidth() * 0.92f, Gdx.graphics.getHeight() * 0.955f);
        settings.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!paused) {
                    paused = !paused;
                    pauseOverlay.setPaused(paused);
                    resumeBtn.setPaused(paused);
                    quitBtn.setPaused(paused);
                    Gdx.app.log("Button", "Settings");
                    return true;
                }
                Gdx.app.log("Button", "Settings failed");
                return false;
            }
        });
        stage.addActor(settings);

        //Pause Menu
        pauseOverlay = new PauseOverlay(new Texture(Gdx.files.internal("ui/pauseoverlay.png")),0,0);
        stage.addActor(pauseOverlay);
        resumeBtn = new PauseOverlay(new Texture(Gdx.files.internal("ui/buttons/resume.png")),
                Gdx.graphics.getWidth()*0.25f, Gdx.graphics.getHeight()*0.55f);
        resumeBtn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(paused) {
                    paused = !paused;
                    pauseOverlay.setPaused(paused);
                    resumeBtn.setPaused(paused);
                    quitBtn.setPaused(paused);
                    Gdx.app.log("Button", "Resume");
                    return true;
                }
                Gdx.app.log("Button", "Resume failed");
                return false;
            }
        });
        stage.addActor(resumeBtn);
        quitBtn = new PauseOverlay(new Texture(Gdx.files.internal("ui/buttons/quit.png")),
                Gdx.graphics.getWidth()*0.25f, Gdx.graphics.getHeight()*0.45f);
        quitBtn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(paused) {
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
        //Input

        //Update
        stage.act();

        //Render
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
