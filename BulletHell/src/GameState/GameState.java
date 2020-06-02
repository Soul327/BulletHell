package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.StateManager;
import Misc.Graphics;
import Misc.KeyManager;

public class GameState {
	public World world;
	GUI gui;
	
	public GameState() {
		world = new World();
		gui = new GUI();
	}
	
	public void tick() {
		world.tick();
		gui.tick();
		if(KeyManager.keyRelease(KeyEvent.VK_ESCAPE)) {
			StateManager.state = 0;
			StateManager.menuState.init();
		}
	}
	public void render(Graphics g) {
		world.render(g);
		gui.render(g);
	}
}