package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import GameState.GameState;
import Menu.Loading;
import Menu.MenuState;
import Misc.Graphics;

public class StateManager {
	
	public static int state = 2;
	public static MenuState menuState;
	public static GameState gameState;
	public static Loading loading;
	
	public StateManager() {
		loading = new Loading();
	}
	
	public void init() {
		menuState = new MenuState();
		gameState = new GameState();
	}
	
	public void tick() {
		switch(state) {
			case 0:menuState.tick();break;
			case 1:gameState.tick();break;
			case 2:
				if(!Main.load.isAlive()) {
					state = 0;
					init();
				}
				loading.tick();
				break;
		}
	}
	public void render(Graphics2D g2d) {
		Graphics g = new Graphics(g2d);
		switch(state) {
			case 0:menuState.render(g);break;
			case 1:gameState.render(g);break;
			case 2:loading.render(g);break;
		}
	}
}