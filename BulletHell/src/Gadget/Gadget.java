package Gadget;

import Main.Main;
import Main.StateManager;
import Misc.Graphics;
import Misc.MouseManager;

public abstract class Gadget {
	
	public int coolDown = 0, defaultCoolDown = 60;
	public String name = "", decription = "";
	
	public void tick() {
		if(coolDown>0)
			coolDown--;
	}
	public void use() {
		if(coolDown==0) {
			coolDown = defaultCoolDown;
			activate();
		}
	}
	public void activate() {}
	public void render(Graphics g) {}
	
	//Get mouse location as x and y in game
	public double getLocX() {
		return MouseManager.mouseX/Main.scale-(StateManager.gameState.world.player.width/Main.scale)/2-StateManager.gameState.world.camX;
	}
	public double getLocY() {
		return MouseManager.mouseY/Main.scale-(StateManager.gameState.world.player.height/Main.scale)/2-StateManager.gameState.world.camY;
	}
}