package NPCS;

import java.awt.Color;
import java.awt.event.KeyEvent;

import Main.StateManager;
import Misc.Graphics;
import Misc.KeyManager;

public class Melissa extends NPC{
	//Melissa is bee in greek
	public Melissa() {
		width = 50;
		height = 50;
		name = "Melissa";
	}
	
	public void tiedTick() {
		tickInteract();
	}
	
	public void render(Graphics g) {
		g.scalable = true;
		double camX = StateManager.gameState.world.camX;
		double camY = StateManager.gameState.world.camY;
		
		g.setColor(Color.magenta);
		g.fillRect(x + camX, y + camY, width, height);
		
		g.setColor(Color.WHITE);
		if(inRange)
			g.drawRect(x + camX, y + camY, width, height);
	}
	public String event(String str) {
		return "";
	}
}
