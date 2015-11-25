package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameMain extends Game {
    public LoadingScreen loadingScreen;
    public StartScreen startScreen;
    public DefenseGameScreen defenseGameScreen;
    public SiegeGameScreen siegeGameScreen;
    public DefenseGameLoadoutScreen defenseLoadoutScreen;

    public static AssetManager assetManager;
    private boolean loaded;

    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);

        assetManager = new AssetManager();
        loadAssests();
        loaded = false;
    }

    @Override
    public void render() {
        super.render();
        if(!loaded && assetManager.update())
            init();
    }

    @Override
    public void dispose() {
        startScreen.dispose();
        defenseLoadoutScreen.dispose();
        defenseGameScreen.dispose();
        siegeGameScreen.dispose();
        assetManager.clear();
        loadingScreen.dispose();
    }

    private void init(){
        startScreen = new StartScreen(this);
        defenseGameScreen = new DefenseGameScreen(this);
        siegeGameScreen = new SiegeGameScreen(this);
        defenseLoadoutScreen = new DefenseGameLoadoutScreen(this);

        loaded = true;
        setScreen(startScreen);
    }

    private void loadAssests() {
        //Backgrounds
        assetManager.load("backgrounds/title.png", Texture.class);
        assetManager.load("backgrounds/startscreen.png", Texture.class);
        assetManager.load("backgrounds/gameover.png", Texture.class);
        assetManager.load("backgrounds/victory.png", Texture.class);
        assetManager.load("backgrounds/basedefense.png", Texture.class);
        assetManager.load("backgrounds/siege.png", Texture.class);
        assetManager.load("backgrounds/loadout.png", Texture.class);

        //UI
        assetManager.load("ui/pauseoverlay.png", Texture.class);
        assetManager.load("ui/buttons/settings.png", Texture.class);
        assetManager.load("ui/buttons/resume.png", Texture.class);
        assetManager.load("ui/buttons/quit.png", Texture.class);
        assetManager.load("ui/healthbarback.png", Texture.class);
        assetManager.load("ui/healthbarfill.png", Texture.class);
        assetManager.load("ui/uiskin.json", Skin.class);
        assetManager.load("ui/deployerbox.png", Texture.class);
        assetManager.load("ui/deployercooldown.png", Texture.class);

        //Icons
        assetManager.load("characters/icons/archericon.png", Texture.class);
        assetManager.load("characters/icons/footmanicon.png", Texture.class);
        assetManager.load("characters/icons/wizardicon.png", Texture.class);

        //Units
        assetManager.load("characters/units/archer.atlas", TextureAtlas.class);
        assetManager.load("characters/units/footman200.atlas", TextureAtlas.class);
        assetManager.load("characters/units/wizard.atlas", TextureAtlas.class);
        assetManager.load("characters/units/healthbarbackground.png", Texture.class);
        assetManager.load("characters/units/healthbarfill.png", Texture.class);

        //Towers
        assetManager.load("characters/towers/cannon.png", Texture.class);
        assetManager.load("characters/towers/mage.png", Texture.class);
        assetManager.load("characters/towers/tower.png", Texture.class);

        //Projectiles
        assetManager.load("characters/projectiles/arrow.png", Texture.class);
        assetManager.load("characters/projectiles/arrowS.png", Texture.class);
        assetManager.load("characters/projectiles/fireball.png", Texture.class);
        assetManager.load("characters/projectiles/magic.png", Texture.class);
        assetManager.load("characters/projectiles/orb.png", Texture.class);
    }
}
