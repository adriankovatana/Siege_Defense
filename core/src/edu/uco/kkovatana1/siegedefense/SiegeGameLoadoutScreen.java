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

public class SiegeGameLoadoutScreen implements Screen {

    private GameMain game;
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;

    private TextButton unit1Btn;
    private TextButton unit2Btn;
    private TextButton unit3Btn;
    private TextButton unit4Btn;
    private TextButton unit5Btn;
    private TextButton unit6Btn;
    private TextButton defendBtn;
    private Sprite background;
    //private Sprite title;

    public SiegeGameLoadoutScreen(GameMain game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        //Background & title
        background = new Sprite(GameMain.assetManager.get("backgrounds/loadout.png", Texture.class));
        //titleTexture = new Texture(Gdx.files.internal("backgrounds/title.png"));
        //title = new Sprite(titleTexture);
        //title.setPosition(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.55f);

        //Unit Buttons
        unit1Btn = new TextButton("Purchase", skin, "default");
        unit1Btn.getLabel().setFontScale(1.5f);
        unit1Btn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit1Btn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit1Btn.setPosition(Gdx.graphics.getWidth() * 0.125f, Gdx.graphics.getHeight() * 0.71f);
        unit1Btn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Unit1Button", "Pressed");
                return true;
            }
        });
        stage.addActor(unit1Btn);

        unit2Btn = new TextButton("Purchase", skin, "default");
        unit2Btn.getLabel().setFontScale(1.5f);
        unit2Btn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit2Btn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit2Btn.setPosition(Gdx.graphics.getWidth() * 0.62f, Gdx.graphics.getHeight() * 0.71f);
        unit2Btn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Unit2Button", "Pressed");
                return true;
            }
        });
        stage.addActor(unit2Btn);

        unit3Btn = new TextButton("Purchase", skin, "default");
        unit3Btn.getLabel().setFontScale(1.5f);
        unit3Btn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit3Btn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit3Btn.setPosition(Gdx.graphics.getWidth() * 0.125f, Gdx.graphics.getHeight() * 0.41f);
        unit3Btn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Unit3Button", "Pressed");
                return true;
            }
        });
        stage.addActor(unit3Btn);

        unit4Btn = new TextButton("Purchase", skin, "default");
        unit4Btn.getLabel().setFontScale(1.5f);
        unit4Btn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit4Btn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit4Btn.setPosition(Gdx.graphics.getWidth() * 0.62f, Gdx.graphics.getHeight() * 0.41f);
        unit4Btn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Unit4Button", "Pressed");
                return true;
            }
        });
        stage.addActor(unit4Btn);

        unit5Btn = new TextButton("Purchase", skin, "default");
        unit5Btn.getLabel().setFontScale(1.5f);
        unit5Btn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit5Btn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit5Btn.setPosition(Gdx.graphics.getWidth() * 0.125f, Gdx.graphics.getHeight() * 0.11f);
        unit5Btn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Unit5Button", "Pressed");
                return true;
            }
        });
        stage.addActor(unit5Btn);

        unit6Btn = new TextButton("Purchase", skin, "default");
        unit6Btn.getLabel().setFontScale(1.5f);
        unit6Btn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit6Btn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit6Btn.setPosition(Gdx.graphics.getWidth() * 0.62f, Gdx.graphics.getHeight() * 0.11f);
        unit6Btn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Unit6Button", "Pressed");
                return true;
            }
        });
        stage.addActor(unit6Btn);

        //Start Game Button
        defendBtn = new TextButton("Siege!", skin, "default");
        defendBtn.getLabel().setFontScale(2.0f);
        defendBtn.setWidth(Gdx.graphics.getWidth() * 0.6f);
        defendBtn.setHeight(Gdx.graphics.getHeight() * 0.06f);
        defendBtn.setPosition(Gdx.graphics.getWidth() * 0.22f, Gdx.graphics.getHeight() * 0.02f);
        defendBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.siegeGameScreen);
                return true;
            }
        });
        stage.addActor(defendBtn);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        //title.draw(batch);
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
