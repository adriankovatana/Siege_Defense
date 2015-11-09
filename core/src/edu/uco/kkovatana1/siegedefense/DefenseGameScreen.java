package edu.uco.kkovatana1.siegedefense;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DefenseGameScreen implements Screen {
    private GameMain game;
    private Stage stage;

    private TexturedActor background;
    private Image gameOver;

    private Group backgroundGroup;
    private Group gameActorsGroup;
    private Group uiGroup;

//    private Wall wall;

    public DefenseGameScreen(GameMain game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        Globals.PAUSED = false;
        backgroundGroup = new Group();
        gameActorsGroup = new Group();
        uiGroup = new DefenseGameUI(this);
        stage.addActor(backgroundGroup);
        stage.addActor(gameActorsGroup);
        stage.addActor(uiGroup);

        //Background
        background = new TexturedActor(GameMain.assetManager.get("backgrounds/basedefense.png", Texture.class));
        backgroundGroup.addActor(background);

        //Game Actors
        /*wall = new Wall();
        gameActorsGroup.addActor(wall);*/

        //Game Over Overlay
        gameOver = new Image(GameMain.assetManager.get("backgrounds/gameover.png", Texture.class));
        gameOver.setPosition(0, Gdx.graphics.getHeight() * 0.4f);
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
    }

    @Override
    public void render(float delta) {
        //UPDATE
        if(!Globals.PAUSED){
            stage.act();
            /*if(wall.health <= 0){
                gameOver.setVisible(true);
                Globals.PAUSED = true;
            }*/
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
