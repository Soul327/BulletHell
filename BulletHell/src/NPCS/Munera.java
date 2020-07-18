package NPCS;

import java.awt.Color;

import Main.StateManager;
import Misc.Graphics;

public class Munera extends NPC{
	
	/* In ancient Rome, munera (Latin plural) were the provision of public works and entertainments provided for the benefit of the Roman people ('populus Romanus') by individuals of high status and wealth.
	 * This will be the way to select the boss fight and check for rewards
	 */
	public Munera() {
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
