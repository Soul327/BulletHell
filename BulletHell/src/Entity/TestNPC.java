package Entity;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Main.StateManager;
import Misc.Graphics;
import Misc.KeyManager;

public class TestNPC extends Entity{
	
	boolean inRange = false;
	double playerDistance;
	
	public TestNPC() {
		x = 500; y = 615;
		//x = 0; y = 0;
		width = 50;
		height = 50;
	}
	int ti = 0;
	public void tick() {}
	public void tiedTick() {
		playerDistance = playerDistance();
		if(playerDistance < 100) {
			if(KeyManager.keyRelease(KeyEvent.VK_E))
				StateManager.overlayState = 4;
			inRange = true;
		} else
			inRange = false;
		
	}
	public void render(Graphics g) {
		g.scalable = true;
		double camX = StateManager.gameState.world.camX;
		double camY = StateManager.gameState.world.camY;
		
		g.setColor(Color.ORANGE);
		g.fillRect(x + camX, y + camY, width, height);
		
		g.setColor(Color.WHITE);
		if(inRange)
			g.drawRect(x + camX, y + camY, width, height);
	}
	public void debugRender(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(x, y, width, height);
	}
}