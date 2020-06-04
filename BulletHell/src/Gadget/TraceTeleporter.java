package Gadget;

import java.awt.Color;

import GameState.World;
import Main.Main;
import Main.StateManager;
import Misc.Graphics;
import Misc.Mat;
import Misc.MouseManager;

public class TraceTeleporter extends Gadget{
	double distance = 150;
	int radius = 10;
	
	public TraceTeleporter() {
		name = "TraceTeleporter";
		decription = "On the press of a button this item will ";
		defaultCoolDown = Main.maxFPS;
	}
	public double getAngle() {
		double camX = StateManager.gameState.world.camX,camY = StateManager.gameState.world.camY;
		return Mat.getAngle(
				(StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2)*Main.scale,
				(StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2)*Main.scale,
				MouseManager.mouseX+radius/2-camX,
				MouseManager.mouseY+radius/2-camY);
	}
	public void activate() {
		double angle = getAngle();
		
		StateManager.gameState.world.player.x += distance * Math.cos(angle);
		StateManager.gameState.world.player.y += distance * Math.sin(angle);
	}
	public void render(Graphics g) {
		int radius = 10;
		double scale = Main.scale;
		double camX = StateManager.gameState.world.camX,camY = StateManager.gameState.world.camY;
		double angle = getAngle();
		double distance = 150;
		g.setColor(Color.white);
		g.drawCenterCircle(
				(StateManager.gameState.world.player.x+distance*Math.cos(angle)+camX)*scale, 
				(StateManager.gameState.world.player.y+distance*Math.sin(angle)+camY)*scale,
				radius*scale);
	}
}
