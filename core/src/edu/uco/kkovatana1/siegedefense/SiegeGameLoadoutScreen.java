package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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

    protected SiegeGame siegeGame;
    private UnitDeployer unitDeployer;

    private TextButton unit1Btn;
    private TextButton unit1HealthBtn;
    private TextButton unit1AmountBtn;
    private Label unit1HealthText;
    private Label unit1DamageText;
    private Label unit1AmountText;

    private TextButton unit2Btn;
    private TextButton unit2HealthBtn;
    private TextButton unit2AmountBtn;
    private Label unit2HealthText;
    private Label unit2DamageText;
    private Label unit2AmountText;

    private TextButton unit3Btn;
    private TextButton unit3HealthBtn;
    private TextButton unit3AmountBtn;
    private Label unit3HealthText;
    private Label unit3DamageText;
    private Label unit3AmountText;

    private TextButton startBtn;
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

        siegeGame.archer.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                addToLoadout(siegeGame.archer);
                return true;
            }
        });

        siegeGame.wizard.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                addToLoadout(siegeGame.wizard);
                return true;
            }
        });
    }

    public void show() {
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        background = new TexturedActor("backgrounds/loadout.png");
        stage.addActor(background);

        //Icons
        goldLabel = new Label("Gold: " + siegeGame.gold, GameMain.assetManager.get("ui/uiskin.json", Skin.class));
        goldLabel.setPosition(Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.02f);
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
        unitDeployer.showAmounts(false);
        stage.addActor(unitDeployer);

        //Unit Buttons
            //Unit 1
        stage.addActor(siegeGame.footman);
        if(siegeGame.footman.purchased)
            unit1Btn = new TextButton("Upgrade - "+siegeGame.footman.damageCost+"g", skin, "default");
        else
            unit1Btn = new TextButton("Purchase - 50g", skin, "default");
        unit1Btn.getLabel().setFontScale(1.5f);
        unit1Btn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit1Btn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit1Btn.setPosition(Gdx.graphics.getWidth() * 0.372f, Gdx.graphics.getHeight() * 0.8375f);
        unit1Btn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!siegeGame.footman.purchased) {
                    siegeGame.purchaseUnit(siegeGame.footman);
                    unit1Btn.setText("Upgrade - " + siegeGame.footman.damageCost + "g");
                    unit1HealthBtn.setVisible(true);
                    unit1AmountBtn.setVisible(true);
                } else {
                    siegeGame.upgradeDamage(siegeGame.footman);
                    unit1Btn.setText("Upgrade - " + siegeGame.footman.damageCost + "g");
                    unit1DamageText.setText(String.format("%.0f", siegeGame.footman.damage));
                }
                return true;
            }
        });
        stage.addActor(unit1Btn);

        unit1HealthBtn = new TextButton("Upgrade - "+siegeGame.footman.healthCost+"g", skin, "default");
        unit1HealthBtn.getLabel().setFontScale(1.5f);
        unit1HealthBtn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit1HealthBtn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit1HealthBtn.setPosition(Gdx.graphics.getWidth() * 0.372f, Gdx.graphics.getHeight() * 0.8968f);
        unit1HealthBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                siegeGame.upgradeHealth(siegeGame.footman);
                unit1HealthBtn.setText("Upgrade - " + siegeGame.footman.healthCost + "g");
                unit1HealthText.setText(String.format("%.0f", siegeGame.footman.health));
                return true;
            }
        });
        stage.addActor(unit1HealthBtn);

        unit1AmountBtn = new TextButton("Upgrade - "+siegeGame.footman.amountCost+"g", skin, "default");
        unit1AmountBtn.getLabel().setFontScale(1.5f);
        unit1AmountBtn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit1AmountBtn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit1AmountBtn.setPosition(Gdx.graphics.getWidth() * 0.372f, Gdx.graphics.getHeight() * 0.7812f);
        unit1AmountBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                siegeGame.upgradeAmount(siegeGame.footman);
                unit1AmountBtn.setText("Upgrade - " + siegeGame.footman.amountCost + "g");
                unit1AmountText.setText("" + siegeGame.footman.amount);
                return true;
            }
        });
        stage.addActor(unit1AmountBtn);

        if(!siegeGame.footman.purchased) {
            unit1HealthBtn.setVisible(false);
            unit1AmountBtn.setVisible(false);
        }

        unit1HealthText = new Label(String.format("%.0f", siegeGame.footman.health), GameMain.assetManager.get("ui/uiskin.json", Skin.class));
        unit1HealthText.setPosition(Gdx.graphics.getWidth() * 0.8417f, Gdx.graphics.getHeight() * 0.9078f);
        unit1HealthText.setFontScale(1.5f);
        stage.addActor(unit1HealthText);

        unit1DamageText = new Label(String.format("%.0f", siegeGame.footman.damage), GameMain.assetManager.get("ui/uiskin.json", Skin.class));
        unit1DamageText.setPosition(Gdx.graphics.getWidth() * 0.8542f, Gdx.graphics.getHeight() * 0.8515f);
        unit1DamageText.setFontScale(1.5f);
        stage.addActor(unit1DamageText);

        unit1AmountText = new Label("" + siegeGame.footman.amount, GameMain.assetManager.get("ui/uiskin.json", Skin.class));
        unit1AmountText.setPosition(Gdx.graphics.getWidth() * 0.8542f, Gdx.graphics.getHeight() * 0.7968f);
        unit1AmountText.setFontScale(1.5f);
        stage.addActor(unit1AmountText);

            //Unit 2
        stage.addActor(siegeGame.archer);
        if(siegeGame.archer.purchased)
            unit2Btn = new TextButton("Upgrade - "+siegeGame.archer.damageCost+"g", skin, "default");
        else
            unit2Btn = new TextButton("Purchase - 50g", skin, "default");
        unit2Btn.getLabel().setFontScale(1.5f);
        unit2Btn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit2Btn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit2Btn.setPosition(Gdx.graphics.getWidth() * 0.372f, Gdx.graphics.getHeight() * 0.5718f);
        unit2Btn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!siegeGame.archer.purchased) {
                    siegeGame.purchaseUnit(siegeGame.archer);
                    unit2Btn.setText("Upgrade - " + siegeGame.archer.damageCost + "g");
                    unit2HealthBtn.setVisible(true);
                    unit2AmountBtn.setVisible(true);
                } else {
                    siegeGame.upgradeDamage(siegeGame.archer);
                    unit2Btn.setText("Upgrade - " + siegeGame.archer.damageCost + "g");
                    unit2DamageText.setText(String.format("%.0f", siegeGame.archer.damage));
                }
                return true;
            }
        });
        stage.addActor(unit2Btn);

        unit2HealthBtn = new TextButton("Upgrade - "+siegeGame.archer.healthCost+"g", skin, "default");
        unit2HealthBtn.getLabel().setFontScale(1.5f);
        unit2HealthBtn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit2HealthBtn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit2HealthBtn.setPosition(Gdx.graphics.getWidth() * 0.372f, Gdx.graphics.getHeight() * 0.6265f);
        unit2HealthBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                siegeGame.upgradeHealth(siegeGame.archer);
                unit2HealthBtn.setText("Upgrade - " + siegeGame.archer.healthCost + "g");
                unit2HealthText.setText(String.format("%.0f", siegeGame.archer.health));
                return true;
            }
        });
        stage.addActor(unit2HealthBtn);

        unit2AmountBtn = new TextButton("Upgrade - "+siegeGame.archer.amountCost+"g", skin, "default");
        unit2AmountBtn.getLabel().setFontScale(1.5f);
        unit2AmountBtn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit2AmountBtn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit2AmountBtn.setPosition(Gdx.graphics.getWidth() * 0.372f, Gdx.graphics.getHeight() * 0.5148f);
        unit2AmountBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                siegeGame.upgradeAmount(siegeGame.archer);
                unit2AmountBtn.setText("Upgrade - " + siegeGame.archer.amountCost + "g");
                unit2AmountText.setText("" + siegeGame.archer.amount);
                return true;
            }
        });
        stage.addActor(unit2AmountBtn);

        if(!siegeGame.archer.purchased) {
            unit2HealthBtn.setVisible(false);
            unit2AmountBtn.setVisible(false);
        }

        unit2HealthText = new Label(String.format("%.0f", siegeGame.archer.health), GameMain.assetManager.get("ui/uiskin.json", Skin.class));
        unit2HealthText.setPosition(Gdx.graphics.getWidth() * 0.8417f, Gdx.graphics.getHeight() * 0.6421f);
        unit2HealthText.setFontScale(1.5f);
        stage.addActor(unit2HealthText);

        unit2DamageText = new Label(String.format("%.0f", siegeGame.archer.damage), GameMain.assetManager.get("ui/uiskin.json", Skin.class));
        unit2DamageText.setPosition(Gdx.graphics.getWidth() * 0.8542f, Gdx.graphics.getHeight() * 0.5859f);
        unit2DamageText.setFontScale(1.5f);
        stage.addActor(unit2DamageText);

        unit2AmountText = new Label("" + siegeGame.archer.amount, GameMain.assetManager.get("ui/uiskin.json", Skin.class));
        unit2AmountText.setPosition(Gdx.graphics.getWidth() * 0.8542f, Gdx.graphics.getHeight() * 0.5312f);
        unit2AmountText.setFontScale(1.5f);
        stage.addActor(unit2AmountText);

            //Unit 3
        stage.addActor(siegeGame.wizard);
        if(siegeGame.wizard.purchased)
            unit3Btn = new TextButton("Upgrade - "+siegeGame.wizard.damageCost+"g", skin, "default");
        else
            unit3Btn = new TextButton("Purchase - 50g", skin, "default");
        unit3Btn.getLabel().setFontScale(1.5f);
        unit3Btn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit3Btn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit3Btn.setPosition(Gdx.graphics.getWidth() * 0.372f, Gdx.graphics.getHeight() * 0.3218f);
        unit3Btn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!siegeGame.wizard.purchased) {
                    siegeGame.purchaseUnit(siegeGame.wizard);
                    unit3Btn.setText("Upgrade - " + siegeGame.wizard.damageCost + "g");
                    unit3HealthBtn.setVisible(true);
                    unit3AmountBtn.setVisible(true);
                } else {
                    siegeGame.upgradeDamage(siegeGame.wizard);
                    unit3Btn.setText("Upgrade - " + siegeGame.wizard.damageCost + "g");
                    unit3DamageText.setText(String.format("%.0f", siegeGame.wizard.health));
                }
                return true;
            }
        });
        stage.addActor(unit3Btn);

        unit3HealthBtn = new TextButton("Upgrade - "+siegeGame.wizard.healthCost+"g", skin, "default");
        unit3HealthBtn.getLabel().setFontScale(1.5f);
        unit3HealthBtn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit3HealthBtn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit3HealthBtn.setPosition(Gdx.graphics.getWidth() * 0.372f, Gdx.graphics.getHeight() * 0.3796f);
        unit3HealthBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                siegeGame.upgradeHealth(siegeGame.wizard);
                unit3HealthBtn.setText("Upgrade - " + siegeGame.wizard.healthCost + "g");
                unit3HealthText.setText(String.format("%.0f", siegeGame.wizard.health));
                return true;
            }
        });
        stage.addActor(unit3HealthBtn);

        unit3AmountBtn = new TextButton("Upgrade - "+siegeGame.wizard.amountCost+"g", skin, "default");
        unit3AmountBtn.getLabel().setFontScale(1.5f);
        unit3AmountBtn.setWidth(Gdx.graphics.getWidth() * 0.26f);
        unit3AmountBtn.setHeight(Gdx.graphics.getHeight() * 0.05f);
        unit3AmountBtn.setPosition(Gdx.graphics.getWidth() * 0.372f, Gdx.graphics.getHeight() * 0.2656f);
        unit3AmountBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                siegeGame.upgradeAmount(siegeGame.wizard);
                unit3AmountBtn.setText("Upgrade - " + siegeGame.wizard.amountCost + "g");
                unit3AmountText.setText("" + siegeGame.wizard.amount);
                return true;
            }
        });
        stage.addActor(unit3AmountBtn);

        if(!siegeGame.wizard.purchased) {
            unit3HealthBtn.setVisible(false);
            unit3AmountBtn.setVisible(false);
        }

        unit3HealthText = new Label(String.format("%.0f", siegeGame.wizard.health), GameMain.assetManager.get("ui/uiskin.json", Skin.class));
        unit3HealthText.setPosition(Gdx.graphics.getWidth() * 0.8417f, Gdx.graphics.getHeight() * 0.3937f);
        unit3HealthText.setFontScale(1.5f);
        stage.addActor(unit3HealthText);

        unit3DamageText = new Label(String.format("%.0f", siegeGame.wizard.damage), GameMain.assetManager.get("ui/uiskin.json", Skin.class));
        unit3DamageText.setPosition(Gdx.graphics.getWidth() * 0.8542f, Gdx.graphics.getHeight() * 0.3343f);
        unit3DamageText.setFontScale(1.5f);
        stage.addActor(unit3DamageText);

        unit3AmountText = new Label("" + siegeGame.wizard.amount, GameMain.assetManager.get("ui/uiskin.json", Skin.class));
        unit3AmountText.setPosition(Gdx.graphics.getWidth() * 0.8542f, Gdx.graphics.getHeight() * 0.2812f);
        unit3AmountText.setFontScale(1.5f);
        stage.addActor(unit3AmountText);

        //Start Game Button
        startBtn = new TextButton("Start Level "+siegeGame.level, skin, "default");
        startBtn.getLabel().setFontScale(2.0f);
        startBtn.setWidth(Gdx.graphics.getWidth() * 0.6f);
        startBtn.setHeight(Gdx.graphics.getHeight() * 0.04f);
        startBtn.setPosition(Gdx.graphics.getWidth() * 0.2028f, Gdx.graphics.getHeight() * 0.1609f);
        startBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                screen.startSiegeGame(unitDeployer);
                return true;
            }
        });
        stage.addActor(startBtn);
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
