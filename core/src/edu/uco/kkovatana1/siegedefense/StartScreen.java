package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class StartScreen implements Screen {
    private GameMain game;
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;

    private TextButton defenseBtn;
    private TextButton siegeBtn;
    private TextButton scoreBtn;
    private Sprite background;
    private Sprite title;

    private Texture backgroundTexture;
    private Texture titleTexture;

    public StartScreen(GameMain game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        //Background & title
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/startscreen.png"));
        background = new Sprite(backgroundTexture);
        titleTexture = new Texture(Gdx.files.internal("backgrounds/title.png"));
        title = new Sprite(titleTexture);
        title.setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.55f);

        //Base Defense button
        defenseBtn = new TextButton("Base\nDefense", skin, "default");
        defenseBtn.getLabel().setFontScale(2.0f);
        defenseBtn.setWidth(Gdx.graphics.getWidth() * 0.36f);
        defenseBtn.setHeight(Gdx.graphics.getHeight() * 0.1f);
        defenseBtn.setPosition(Gdx.graphics.getWidth() * 0.03f, Gdx.graphics.getHeight() * 0.02f);
        defenseBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.defenseLoadoutScreen);
                return true;
            }
        });
        stage.addActor(defenseBtn);

        //Siege button
        siegeBtn = new TextButton("Siege", skin, "default");
        siegeBtn.getLabel().setFontScale(2.0f);
        siegeBtn.setWidth(Gdx.graphics.getWidth() * 0.34f);
        siegeBtn.setHeight(Gdx.graphics.getHeight() * 0.1f);
        siegeBtn.setPosition(Gdx.graphics.getWidth() * 0.41f, Gdx.graphics.getHeight() * 0.02f);
        siegeBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.siegeLoadoutScreen);
                return true;
            }
        });
        stage.addActor(siegeBtn);

        //Score button
        scoreBtn = new TextButton("Quit", skin, "default");
        scoreBtn.getLabel().setFontScale(2.0f);
        scoreBtn.setWidth(Gdx.graphics.getWidth() * 0.2f);
        scoreBtn.setHeight(Gdx.graphics.getHeight() * 0.1f);
        scoreBtn.setPosition(Gdx.graphics.getWidth() * 0.77f, Gdx.graphics.getHeight() * 0.02f);
        scoreBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });
        stage.addActor(scoreBtn);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        title.draw(batch);
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
