package Entity;

import GameState.Player;
import Main.StateManager;
import Misc.Graphics;
import Misc.Mat;

public abstract class Entity {
	
	public boolean remove = false;
	public double x,y,width,height;
	double velX,velY,friction;
	
	public void tick() {}
	public void tiedTick() {}
	public void tickVelocity() {
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
	public void render(Graphics g) {}
	public void debugRender(Graphics g) {}
}
