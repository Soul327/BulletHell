package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.Main;
import Main.StateManager;
import Misc.Graphics;
import Misc.KeyManager;
import Misc.Mat;
import Misc.MouseManager;

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
	public static double getPlayerMouseAngle() {
		return Mat.getAngle(
				(StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2+StateManager.gameState.world.camX)*Main.scale, 
				(StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2+StateManager.gameState.world.camY)*Main.scale,
				MouseManager.mouseX,
				MouseManager.mouseY);
	}
}