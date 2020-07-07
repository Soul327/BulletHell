package Entity;

import java.awt.Color;
import java.awt.event.KeyEvent;

import Main.StateManager;
import Misc.Graphics;
import Misc.KeyManager;
import Misc.Mat;

public class Floof extends Entity{
	
	boolean lockedOn = false;
	int loX = 0;
	int loY = 0;
	double acc = .1;
	
	public Floof() {
		width = height = 25;
		x = Math.random()*(1600-width);
		y = Math.random()*(900-height);
		maxVel = 25;
		friction = .05;
	}
	
	public void tick() {
		double playerX = StateManager.gameState.world.player.x;
		double playerY = StateManager.gameState.world.player.y;
		
		if(lockedOn) {
			double angleToPlayer = Mat.getAngle(x,y,loX,loY);
			velX += Math.cos(angleToPlayer)*(acc+friction);
			velY += Math.sin(angleToPlayer)*(acc+friction);
			//System.out.println(angleToPlayer +" "+velX+" "+velY+" "+loX+" "+loY);
			if(KeyManager.keyRelease(KeyEvent.VK_K)) lockedOn = false;
			int sway = 5;
			if(x<loX+sway & x>loX-sway & y<loY+sway & y>loY-sway)
				lockedOn = false;
		}else {
			if(velX==0 & velY==0) {
				loX = (int)playerX;
				loY = (int)playerY;
				lockedOn = true;
			}
		}
		tickVelocity();
		edge();
		if(playerDistance()<30) StateManager.gameState.world.player.damage(1);
		for(Entity e:StateManager.gameState.world.entities)
			if(e instanceof Bullet)
				if(entityDistance(e)<30) {
					e.remove = true;
					remove = true;
				}
	}
	
	public void render(Graphics g) {
		g.scalable = true;
		double camX = StateManager.gameState.world.camX;
		double camY = StateManager.gameState.world.camY;
		
		g.setColor(Color.red);
		g.fillRect(x+camX, y+camY, width, height);
		if(lockedOn & Main.Main.devMode>=0) g.drawRect(loX+camX, loY+camY, width, height);
	}
}
