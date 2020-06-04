package Gadget;

import GameState.World;
import Main.Main;
import Main.StateManager;
import Misc.Graphics;
import Misc.MouseManager;

public class Teleporter extends Gadget{
	public Teleporter() {
		name = "Teleporter";
		decription = "On the press of a button this item will relocate you to the location selected";
		defaultCoolDown = Main.maxFPS*2;
	}
	public void activate() {
		StateManager.gameState.world.player.x = getLocX();
		StateManager.gameState.world.player.y = getLocY();
	}
	public void render(Graphics g) {
		//*
		//g.scalable = true;
		double x = getLocX();
		double y = getLocY();
		double width = 30, height=30;
		g.drawRect(
				(x+StateManager.gameState.world.camX)*Main.scale, 
				(y+StateManager.gameState.world.camY)*Main.scale, 
				(width)*Main.scale, 
				(height)*Main.scale);
		g.scalable = false;
		//*/
	}
	
}