package Boss;

import java.awt.Color;
import java.awt.event.KeyEvent;

import GameState.World;
import Main.Main;
import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;
import Misc.KeyManager;

public class Patches extends Boss{
	
	int ani = 0;
	
	public Patches() {
		name = "Patches";
		des = "Test ye wares";
		health = Integer.MAX_VALUE;
		maxHealth = health;
		width = 50;
		height = 50;
	}
	public void tick2() {
		health = maxHealth;
	}
	public void render(Graphics g) {
		double camX = StateManager.gameState.world.camX;
		double camY = StateManager.gameState.world.camY;
		double scale = Main.scale;
		
		if(Main.scaling) {
			int off = 100;
			
			if(Math.random()<.05) ani++;
			if(ani>Assets.anim.get(0).size()-1) ani=0;
			g.drawImage(Assets.anim.get(0).get(ani), 
					(x+camX-off/2)*scale,
					(y+camY-off/2)*scale,
					(width+off)*scale, 
					(height+off)*scale);
			g.setColor(Color.black);
			if(Main.devMode>0)
				g.drawRect(
						(x+camX)*scale,
						(y+camY)*scale,
						width*scale, 
						height*scale);
		}else {
			int off = 100;
			
			if(Math.random()<.05) ani++;
			if(ani>Assets.anim.get(0).size()-1) ani=0;
			g.drawImage(Assets.anim.get(0).get(ani), x+StateManager.gameState.world.camX -off/2, y+StateManager.gameState.world.camY+heightFromGround-off/2, width+off, height+off);
			//g.setColor(Color.black);
			if(Main.devMode>0)
				g.drawRect(x+StateManager.gameState.world.camX , y+StateManager.gameState.world.camY+heightFromGround, width, height);
		}
	}
}
