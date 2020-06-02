package Gadget;

import java.awt.Color;

import GameState.World;
import Main.StateManager;
import Misc.Graphics;
import Misc.Mat;
import Misc.MouseManager;

public class TraceTeleporter extends Gadget{
	
	public TraceTeleporter() {
		name = "TraceTeleporter";
		decription = "On the press of a button this item will ";
		defaultCoolDown = Main.Main.maxFPS;
	}
	public void activate() {
		int radius = 10;
		double camX = StateManager.gameState.world.camX,camY = StateManager.gameState.world.camY;
		double angle = Mat.getAngle(
				StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2,
				StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2,
				MouseManager.mouseX+radius/2-camX,
				MouseManager.mouseY+radius/2-camY);
		double distance = 150;
		
		StateManager.gameState.world.player.x += distance * Math.cos(angle);
		StateManager.gameState.world.player.y += distance * Math.sin(angle);
	}
	public void render(Graphics g) {
		int radius = 10;
		double camX = StateManager.gameState.world.camX,camY = StateManager.gameState.world.camY;
		double angle = Mat.getAngle(
				StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2,
				StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2,
				MouseManager.mouseX+radius/2-camX,
				MouseManager.mouseY+radius/2-camY);
		double distance = 150;
		g.setColor(Color.white);
		g.drawCenterCircle(
				StateManager.gameState.world.player.x+distance*Math.cos(angle)+camX, 
				StateManager.gameState.world.player.y+distance*Math.sin(angle)+camY,
				radius);
	}
}
