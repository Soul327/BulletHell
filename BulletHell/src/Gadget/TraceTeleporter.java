package Gadget;

import java.awt.Color;

import GameState.World;
import Main.Main;
import Main.StateManager;
import Misc.Graphics;
import Misc.Mat;
import Misc.MouseManager;

public class TraceTeleporter extends Gadget{
	private int radius = 10;
	private double distance = 150;
	
	public TraceTeleporter() {
		name = "TraceTeleporter";
		decription = "On the press of a button this item will ";
		defaultCoolDown = Main.maxFPS;
	}
	public void activate() {
		double angle = GameState.GameState.getPlayerMouseAngle();
		StateManager.gameState.world.player.x += distance * Math.cos(angle);
		StateManager.gameState.world.player.y += distance * Math.sin(angle);
	}
	public void render(Graphics g) {
		double angle = GameState.GameState.getPlayerMouseAngle();
		g.setColor(Color.white);
		g.scalable = true;
		g.drawCircle(
				(StateManager.gameState.world.player.x+distance*Math.cos(angle)+StateManager.gameState.world.camX), 
				(StateManager.gameState.world.player.y+distance*Math.sin(angle)+StateManager.gameState.world.camY),
				radius);
		g.scalable = false;
	}
}
