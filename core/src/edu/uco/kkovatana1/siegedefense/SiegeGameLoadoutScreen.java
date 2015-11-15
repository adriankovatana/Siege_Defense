package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;

public class SiegeGameLoadoutScreen implements Disposable {

    private SiegeGameScreen screen;
    private Stage stage;
    private Skin skin;

    private SiegeGame siegeGame;
    private UnitDeployer unitDeployer;

    private TextButton unit1Btn;
    private TextButton unit2Btn;
    private TextButton unit3Btn;
    private TextButton unit4Btn;
    private TextButton defendBtn;
    private TexturedActor background;
    private Label goldLabel;

    public SiegeGameLoadoutScreen(SiegeGameScreen screen){
        this.screen = screen;
        siegeGame = new SiegeGame();

        siegeGame.footman.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                addToLoadout(siegeGame.footman);
                return true;
            }
        });
    }

    public void show() {
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        background = new TexturedActor(GameMain.assetManager.get("backgrounds/loadout.png", Texture.class));
        stage.addActor(background);

        //Icons
        goldLabel = new Label("Gold: " + siegeGame.gold, GameMain.assetManager.get("ui/uiskin.json", Skin.class));
        goldLabel.setPosition(Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.012f);
        goldLabel.setFontScale(3.0f);
        stage.addActor(goldLabel);

        //Unit Deployer
        unitDeployer = new UnitDeployer();
        unitDeployer.cell1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                unitDeployer.cell1.setUnit(null);
                return true;
            }
        });
        unitDeployer.cell2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                unitDeployer.cell2.setUnit(null);
                return true;
            }
        });
        unitDeployer.cell3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                unitDeployer.cell3.setUnit(null);
                return true;
            }
        });
        unitDeployer.cell4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                unitDeployer.cell4.setUnit(null);
                return true;
            }
        });
        stage.addActor(unitDeployer);

        //Unit Buttons
        stage.addActor(siegeGame.footman);
        if(siegeGame.footman.purchased)
            unit1Btn = new TextButton("Upgrade", skin, "default");
        else
            unit1Btn = new TextButton("Purchase", skin, "default");
        unit1Btn.getLabel().setFontScale(1.5f);
        unit1Btn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit1Btn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit1Btn.setPosition(Gdx.graphics.getWidth() * 0.125f, Gdx.graphics.getHeight() * 0.71f);
        unit1Btn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(!siegeGame.footman.purchased){
                    siegeGame.purchaseUnit(siegeGame.footman);
                    unit1Btn.setText("Upgrade");
                } else {
                    siegeGame.upgradeDamage(siegeGame.footman);
                }
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

        //Start Game Button
        defendBtn = new TextButton("Siege!", skin, "default");
        defendBtn.getLabel().setFontScale(2.0f);
        defendBtn.setWidth(Gdx.graphics.getWidth() * 0.6f);
        defendBtn.setHeight(Gdx.graphics.getHeight() * 0.04f);
        defendBtn.setPosition(Gdx.graphics.getWidth() * 0.22f, Gdx.graphics.getHeight() * 0.22f);
        defendBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                screen.startSiegeGame(unitDeployer);
                return true;
            }
        });
        stage.addActor(defendBtn);
        Gdx.input.setInputProcessor(stage);
    }

    public void render(float delta) {
        stage.act();
        goldLabel.setText("Gold: " + siegeGame.gold);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }

    public void addToLoadout(UnitUpgrades unit){
        if(unitDeployer.cell1.unit == null){
            unitDeployer.cell1.setUnit(unit);
        } else if(unitDeployer.cell2.unit == null){
            unitDeployer.cell2.setUnit(unit);
        } else if(unitDeployer.cell3.unit == null){
            unitDeployer.cell3.setUnit(unit);
        } else if(unitDeployer.cell4.unit == null){
            unitDeployer.cell4.setUnit(unit);
        } else {
            //ALL 4 SLOTS ARE FILLED!
            Gdx.app.log("LOADOUT", "full");
        }
    }
}
