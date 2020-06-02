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
		if(KeyManager.keyRelease(KeyEvent.VK_0)) { ani++; System.out.println(ani); }
	}
	public void render(Graphics g) {
		int off = 100;
		
		if(Math.random()<.05) ani++;
		if(ani>Assets.anim.get(0).size()-1) ani=0;
		g.drawImage(Assets.anim.get(0).get(ani), x+StateManager.gameState.world.camX -off/2, y+StateManager.gameState.world.camY+heightFromGround-off/2, width+off, height+off);
		//g.setColor(Color.black);
		if(Main.devMode>0)
			g.drawRect(x+StateManager.gameState.world.camX , y+StateManager.gameState.world.camY+heightFromGround, width, height);
	}
}
