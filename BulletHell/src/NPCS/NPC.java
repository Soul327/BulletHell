package NPCS;

import java.awt.Color;
import java.awt.event.KeyEvent;

import Entity.Entity;
import Main.StateManager;
import Misc.Graphics;
import Misc.KeyManager;

public abstract class NPC extends Entity{
	boolean inRange = false;
	double playerDistance;
	String name = "testy";
	
	int ti = 0;
	public void tick() {}
	public void tiedTick() {
		
		
	}
	public void tickInteract() {
		playerDistance = playerDistance();
		if(playerDistance < 100) {
			if(KeyManager.keyRelease(KeyEvent.VK_E)) {
				//System.out.println(name);
				StateManager.dialogueState.loadChar(name, 0, null,this);
				StateManager.overlayState = 4;
			}
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
	public String event(String str) {
		return "";
	}
}
