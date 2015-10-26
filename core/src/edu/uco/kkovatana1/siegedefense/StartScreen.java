package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class StartScreen implements Screen {
    GameMain game;
    SpriteBatch batch;
    Stage stage;
    Skin skin;
    //Button.ButtonStyle buttonStyle;

    TextButton defenseBtn;
    TextButton siegeBtn;

    //Image tempBtn;

    public StartScreen(GameMain game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        //buttonStyle = new TextButton.TextButtonStyle();

        //Base Defense button
        defenseBtn = new TextButton("Base Defense", skin, "default");
        defenseBtn.getLabel().setFontScale(3.0f);
        defenseBtn.setWidth(Gdx.graphics.getWidth() * 0.8f);
        defenseBtn.setHeight(Gdx.graphics.getHeight() * 0.1f);
        defenseBtn.setPosition(Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.2f);
        defenseBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.defenseGameScreen);
                return true;
            }
        });
        stage.addActor(defenseBtn);

        //Siege button
        siegeBtn = new TextButton("Siege", skin, "default");
        siegeBtn.getLabel().setFontScale(3.0f);
        siegeBtn.setWidth(Gdx.graphics.getWidth() * 0.8f);
        siegeBtn.setHeight(Gdx.graphics.getHeight() * 0.1f);
        siegeBtn.setPosition(Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.31f);
        siegeBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.siegeGameScreen);
                return true;
            }
        });
        stage.addActor(siegeBtn);

        /*tempBtn = new Image(new Texture(Gdx.files.internal("badlogic.jpg")));
        tempBtn.setPosition(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/8);
        tempBtn.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.defenseGameScreen);
                return true;
            }
        });
        stage.addActor(tempBtn);*/
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw();
        batch.end();
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
