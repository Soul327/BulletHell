package Entity;

import java.awt.Color;

import GameState.Player;
import Main.StateManager;
import Misc.Graphics;
import Misc.Mat;

public abstract class Entity {
	
	public boolean remove = false;
	public double x,y,width,height;
	double velX,velY,friction,maxVel = 100;
	
	public void tick() {}
	public void tiedTick() {}
	public void tickVelocity() {
		if(velX> maxVel) velX =  maxVel;
		if(velX<-maxVel) velX = -maxVel;
		if(velY> maxVel) velY =  maxVel;
		if(velY<-maxVel) velY = -maxVel;
		x+=velX;
		y+=velY;
		if(friction!=0) {
			if(velX<friction & velX>-friction) velX = 0;
			if(velY<friction & velY>-friction) velY = 0;
			if(velX>0) velX -= friction;
			if(velX<0) velX += friction;
			if(velY>0) velY -= friction;
			if(velY<0) velY += friction;
		}
	}
	public double playerDistance() {
		Player player = StateManager.gameState.world.player;
		return Mat.distance(
				player.x+player.width/2, 
				player.y+player.height/2, 
				x+width/2,
				y+height/2);
	}
	public double entityDistance(Entity e) {
		return Mat.distance(
				e.x+e.width/2, 
				e.y+e.height/2, 
				x+width/2,
				y+height/2);
	}
	public void render(Graphics g) {}
	public void debugRender(Graphics g) {
		g.scalable = true;
		double camX = StateManager.gameState.world.camX;
		double camY = StateManager.gameState.world.camY;
		g.setColor(Color.white);
		g.drawRect(x+camX, y+camY, width, height);
		g.setColor(Color.green);
		double o = 20;
		g.drawLine(x+camX+width/2, y+camY+height/2, x+camX+width/2+velX*o, y+camY+height/2+velY*o);
	}
	public void edge() {
		if(x<0) {
			x=0;
			velX = 0;
		}
		if(x>StateManager.gameState.world.width) {
			x = StateManager.gameState.world.width;
			velX = 0;
		}
		if(y<0) {
			y=0;
			velY = 0;
		}
		if(y>StateManager.gameState.world.height) {
			y = StateManager.gameState.world.height;
			velY = 0;
		}
	}
}
