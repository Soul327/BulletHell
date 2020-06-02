package Gadget;

import java.awt.Point;
import java.util.ArrayList;

import GameState.Bullet;
import Main.StateManager;
import Misc.Graphics;

public class Snap extends Gadget{
	
	Point point = new Point(0,0);
	double health = 0;
	
	public Snap() {
		name = "Snap";
		decription = "When you get hit you will teleport back to the location set";
		defaultCoolDown = Main.Main.maxFPS*20;
	}
	public void activate() {
		StateManager.gameState.world.bullet = new ArrayList<Bullet>();
	}
	public void render(Graphics g) {}
}
