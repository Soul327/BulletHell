package Gadget;

import java.awt.Color;
import java.awt.Point;

import Main.StateManager;
import Misc.Graphics;

public class GlitchBack extends Gadget{
	
	Point point = new Point(0,0);
	double health = 0;
	
	public GlitchBack() {
		name = "Glitch Back";
		decription = "When you get hit you will teleport back to the location set";
		defaultCoolDown = 30;
	}
	public void activate() {
		point = new Point(
				(int)(StateManager.gameState.world.player.getLocX()),
				(int)(StateManager.gameState.world.player.getLocY())
				);
		health = StateManager.gameState.world.player.health;
	}
	public void render(Graphics g) {
		if(point!=null & StateManager.gameState.world.player.health<health) {
			StateManager.gameState.world.player.x = point.getX();
			StateManager.gameState.world.player.y = point.getY();
			point = null;
		}
		if(point!=null) g.fillOval(point.x-5+StateManager.gameState.world.camX, point.y-5+StateManager.gameState.world.camY, 10, 10);
		
	}
}
