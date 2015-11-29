package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
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
    protected static Group gameActorsGroup;
    private SiegeGameUI uiGroup;

    protected List<Unit> unitsList;
    protected List<Unit> unitDeadList;
    protected List<Tower> towersList;
    protected static Wall wall;
    protected UnitDeployer unitDeployer;

    private Tower tower1;
    private Tower tower2;
    private Tower tower3;
    private Tower tower4;

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
                for(Tower tower : towersList) {
                    for (Unit unit : unitsList) {
                        if (unit.dead) {
                            if(!unitDeadList.contains(unit))
                                unitDeadList.add(unit);
                            continue;
                        }
                        if (!tower.onCooldown) {
                            if (Intersector.overlaps(unit.hitBox, tower.rangeBox)) {
                                if(unit.unitAnimator.getState() != Globals.EntityState.DYING) {
                                    gameActorsGroup.addActor(tower.shoot(unit));
                                }
                            }
                        }
                    }
                }
                stage.act();
                for (Unit unit : unitDeadList) {
                    unit.remove();
                    unitsList.remove(unit);
                }
                unitDeadList.clear();
                if (wall.health <= 0) {
                    victory.setVisible(true);
                    Globals.PAUSED = true;
                    distributeVictoryLoot();
                } else if(isGameOver()){
                    gameover.setVisible(true);
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
        game.setScreen(game.startScreen);
    }

    public void startSiegeGame(UnitDeployer deployer){
        gameState = Globals.GameState.PLAYING;
        Globals.PAUSED = false;
        stage = new Stage();
        unitsList = new ArrayList<Unit>();
        unitDeadList = new ArrayList<Unit>();
        towersList = new ArrayList<Tower>();
        unitDeployer = new UnitDeployer();
        unitDeployer.cell1.setUnit(deployer.cell1.unit);
        unitDeployer.cell2.setUnit(deployer.cell2.unit);
        unitDeployer.cell3.setUnit(deployer.cell3.unit);
        unitDeployer.cell4.setUnit(deployer.cell4.unit);
        unitDeployer.showAmounts(true);
        backgroundGroup = new Group();
        gameActorsGroup = new Group();
        uiGroup = new SiegeGameUI(this);
        stage.addActor(backgroundGroup);
        stage.addActor(gameActorsGroup);
        stage.addActor(uiGroup);

        //Background
        background = new TexturedActor("backgrounds/siege.png");
        backgroundGroup.addActor(background);

        //Game Actors
        wall = new Wall();
        gameActorsGroup.addActor(wall);

        tower1 = getRandomTower(new Vector2(Gdx.graphics.getWidth()*0.0555f, Gdx.graphics.getHeight()*0.875f));
        gameActorsGroup.addActor(tower1);
        towersList.add(tower1);

        tower2 = getRandomTower(new Vector2(Gdx.graphics.getWidth()*0.3055f, Gdx.graphics.getHeight()*0.875f));
        gameActorsGroup.addActor(tower2);
        towersList.add(tower2);

        tower3 = getRandomTower(new Vector2(Gdx.graphics.getWidth()*0.5555f, Gdx.graphics.getHeight()*0.875f));
        gameActorsGroup.addActor(tower3);
        towersList.add(tower3);

        tower4 = getRandomTower(new Vector2(Gdx.graphics.getWidth()*0.8055f, Gdx.graphics.getHeight()*0.875f));
        gameActorsGroup.addActor(tower4);
        towersList.add(tower4);

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
        wall.maxHealth = wall.health = 1200*Globals.DIFFICULTY;
        gameState = Globals.GameState.PLAYING;
        Gdx.input.setInputProcessor(stage);
    }

    public void addUnit(UnitUpgrades unit, Vector2 position, Vector2 destination){
        Unit tmpUnit;
        switch (unit.type){
            case FOOTMAN:
                tmpUnit = new Footman(position.x, position.y, unit.damage,unit.health);
                break;
            case ARCHER:
                tmpUnit = new Archer(position.x, position.y, unit.damage,unit.health, destination);
                break;
            case WIZARD:
                tmpUnit = new Wizard(position.x, position.y, unit.damage,unit.health, destination);
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

    public Tower getRandomTower(Vector2 position){
        int random = (int)(Math.random()*100);
        switch (random%3){
            case 0:
                return new ArrowTower(position.x, position.y);
            case 1:
                return new CannonTower(position.x, position.y);
            case 2:
                return new MageTower(position.x, position.y);
            default:
                return null;
        }
    }

    private boolean isGameOver(){
        if(unitDeployer.cell1.amount <=0 && unitDeployer.cell2.amount <= 0 &&
                unitDeployer.cell3.amount <=0 && unitDeployer.cell4.amount <=0 &&
                unitsList.isEmpty() && unitDeadList.isEmpty())
            return true;
        else
            return false;
    }

    private void distributeVictoryLoot(){
        loadoutScreen.siegeGame.level++;
        Globals.DIFFICULTY += 0.2f;

        int gold = 0;
        gold += unitDeployer.cell1.amount * 25;
        gold += unitDeployer.cell2.amount * 25;
        gold += unitDeployer.cell3.amount * 25;
        gold += unitDeployer.cell4.amount * 25;
        loadoutScreen.siegeGame.gold += gold;
    }
}
