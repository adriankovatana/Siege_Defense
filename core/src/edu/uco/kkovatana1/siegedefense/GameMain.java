package edu.uco.kkovatana1.siegedefense;

import com.badlogic.gdx.Game;

public class GameMain extends Game {

	public StartScreen startScreen;
	public DefenseGameScreen defenseGameScreen;
	public SiegeGameScreen siegeGameScreen;
	
	@Override
	public void create () {
		startScreen = new StartScreen(this);
		defenseGameScreen = new DefenseGameScreen(this);
		siegeGameScreen = new SiegeGameScreen(this);

		setScreen(startScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		startScreen.dispose();
		defenseGameScreen.dispose();
		siegeGameScreen.dispose();
	}
}
