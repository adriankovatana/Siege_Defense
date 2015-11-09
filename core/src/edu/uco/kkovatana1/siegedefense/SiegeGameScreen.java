package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SiegeGameScreen implements Screen {
    private GameMain game;
    private Stage stage;

    private TexturedActor background;
    private Image gameOver;
    private Actor selectedActor;

    private Group backgroundGroup;
    private Group gameActorsGroup;
    private Group uiGroup;

    //Temp Units
    private Footman footman;
    private Footman footman2;
    private Footman footman3;
    private Footman footman4;
    private Wall wall;

    public SiegeGameScreen(GameMain game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        Globals.PAUSED = false;
        backgroundGroup = new Group();
        gameActorsGroup = new Group();
        uiGroup = new SiegeGameUI(this);
        stage.addActor(backgroundGroup);
        stage.addActor(gameActorsGroup);
        stage.addActor(uiGroup);

        //Background
        background = new TexturedActor(GameMain.assetManager.get("backgrounds/siege.png", Texture.class));
        backgroundGroup.addActor(background);

        //Game Actors
        wall = new Wall();
        gameActorsGroup.addActor(wall);

        //Game actors for testing
        footman = new Footman(-10f, 40f, wall);
        footman.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (footman.animator.getState() == Globals.EntityState.STANDING) {
                    footman.moveToPosition(wall.getPosition(0), false);
                } else {
                    footman.animator.setState(Globals.EntityState.DYING);
                }
                return true;
            }
        });
        gameActorsGroup.addActor(footman);

        footman2 = new Footman(170f, 40f, wall);
        footman2.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (footman2.animator.getState() == Globals.EntityState.STANDING) {
                    footman2.moveToPosition(wall.getPosition(1), false);
                } else {
                    footman2.animator.setState(Globals.EntityState.DYING);
                }
                return true;
            }
        });
        gameActorsGroup.addActor(footman2);

        footman3 = new Footman(350f, 40f, wall);
        footman3.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (footman3.animator.getState() == Globals.EntityState.STANDING) {
                    footman3.moveToPosition(wall.getPosition(2), false);
                } else {
                    footman3.animator.setState(Globals.EntityState.DYING);
                }
                return true;
            }
        });
        gameActorsGroup.addActor(footman3);

        footman4 = new Footman(530f, 40f, wall);
        footman4.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (footman4.animator.getState() == Globals.EntityState.STANDING) {
                    footman4.moveToPosition(wall.getPosition(3), false);
                } else {
                    footman4.animator.setState(Globals.EntityState.DYING);
                }
                return true;
            }
        });
        gameActorsGroup.addActor(footman4);

        //Game Over Overlay
        gameOver = new Image(GameMain.assetManager.get("backgrounds/gameover.png", Texture.class));
        gameOver.setPosition(0, Gdx.graphics.getHeight()*0.4f);
        gameOver.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.startScreen);
                return true;
            }
        });
        stage.addActor(gameOver);
        gameOver.setVisible(false);

        Gdx.input.setInputProcessor(stage);
        selectedActor = null;
    }

    @Override
    public void render(float delta) {
        //UPDATE
        if(!Globals.PAUSED){
            stage.act();
            if(wall.health <= 0){
                gameOver.setVisible(true);
                Globals.PAUSED = true;
            }
        }

        //RENDER
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

    public void quit() {
        dispose();
        game.setScreen(game.startScreen);
    }
}
