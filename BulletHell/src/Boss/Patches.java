package Boss;

import java.awt.event.KeyEvent;

import GameState.World;
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
	}
	public void tick2() {
		health = maxHealth;
		if(KeyManager.keyRelease(KeyEvent.VK_0)) { ani++; System.out.println(ani); }
	}
	public void render(Graphics g) {
		if(Math.random()<.05) ani++;
		if(ani>Assets.anim.get(0).size()-1) ani=0;
		g.drawImage(Assets.anim.get(0).get(ani), x+StateManager.gameState.world.camX + (Math.pow(heightFromGround,1.1)/2.0)*Math.cos(Math.toDegrees(World.timeOfDay)), y+StateManager.gameState.world.camY+heightFromGround, width, height);
	}
}
