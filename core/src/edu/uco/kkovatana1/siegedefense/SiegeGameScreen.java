package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

public class SiegeGameScreen implements Screen {
    protected GameMain game;
    protected SiegeGameLoadoutScreen loadoutScreen;
    private Stage stage;
    protected Globals.GameState gameState;

    private TexturedActor background;
    private Image gameover;
    private Image victory;
    private Actor selectedActor;

    private Group backgroundGroup;
    protected Group gameActorsGroup;
    private SiegeGameUI uiGroup;

    protected List<Unit> unitsList;
    protected static Wall wall;
    protected UnitDeployer unitDeployer;

    public SiegeGameScreen(GameMain game){
        this.game = game;
    }

    @Override
    public void show() {
        gameState = Globals.GameState.LOADOUT;
        Globals.PAUSED = false;
        stage = new Stage();
        loadoutScreen = new SiegeGameLoadoutScreen(this);
        loadoutScreen.show();
    }

    @Override
    public void render(float delta) {
        //UPDATE
        if(gameState == Globals.GameState.PLAYING) {
            if (!Globals.PAUSED) {
                stage.act();
                if (wall.health <= 0) {
                    victory.setVisible(true);
                    Globals.PAUSED = true;
                }
            }
            //RENDER
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.draw();
        } else {
            loadoutScreen.render(delta);
        }
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
        loadoutScreen.dispose();
        stage.dispose();
    }

    public void quit() {
        dispose();
        game.setScreen(game.startScreen);
    }

    public void startSiegeGame(UnitDeployer deployer){
        gameState = Globals.GameState.PLAYING;
        Globals.PAUSED = false;
        stage = new Stage();
        unitsList = new ArrayList<Unit>();
        unitDeployer = new UnitDeployer();
        unitDeployer.cell1.setUnit(deployer.cell1.unit);
        unitDeployer.cell2.setUnit(deployer.cell2.unit);
        unitDeployer.cell3.setUnit(deployer.cell3.unit);
        unitDeployer.cell4.setUnit(deployer.cell4.unit);
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

        //Game Over Overlay
        gameover = new Image(GameMain.assetManager.get("backgrounds/gameover.png", Texture.class));
        gameover.setPosition(0, Gdx.graphics.getHeight() * 0.4f);
        gameover.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.startScreen);
                return true;
            }
        });
        stage.addActor(gameover);
        gameover.setVisible(false);

        //Game Over Overlay
        victory = new Image(GameMain.assetManager.get("backgrounds/victory.png", Texture.class));
        victory.setPosition(0, Gdx.graphics.getHeight() * 0.4f);
        victory.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                loadoutScreen.show();
                gameState = Globals.GameState.LOADOUT;
                return true;
            }
        });
        stage.addActor(victory);
        victory.setVisible(false);

        selectedActor = null;
        gameState = Globals.GameState.PLAYING;
        Gdx.input.setInputProcessor(stage);
    }

    public void addUnit(UnitUpgrades unit, Vector2 position, Vector2 destination){
        Unit tmpUnit;
        switch (unit.type){
            case FOOTMAN:
                tmpUnit = new Footman(position.x, position.y, unit.damage,unit.health);
                break;
            default:
                tmpUnit = null;
                Gdx.app.log("ADD UNIT", "type not found");
                break;
        }
        unitsList.add(tmpUnit);
        gameActorsGroup.addActor(tmpUnit);
        tmpUnit.moveToPosition(destination, false);
    }
}
