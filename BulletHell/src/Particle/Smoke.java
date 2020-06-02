package Particle;

import java.awt.AlphaComposite;

import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;

public class Smoke extends Particle{
	
	int maxLife = -1,life = maxLife, ani = 7;
	double size,rotation;
	
	public Smoke(double x,double y,double size) {
		this.x = x;
		this.y = y;
		this.size = size+Math.random()*size;
		rotation = Math.random()*360;
		if(x<0 | x>StateManager.gameState.world.width | y<0 | y>StateManager.gameState.world.height) remove = true;
	}
	
	public void render(Graphics g) {
		double camX = StateManager.gameState.world.camX, camY = StateManager.gameState.world.camY;
		if(maxLife!=-1) {
			life--;
			if(life<=0) remove = true;
			if(ani+1<7 & Math.random()<.1 & life<=maxLife/2) ani++;
		}
		
		if(ani-1>0) {
			if(Math.random()<.1 & (life>=maxLife/2 | maxLife==-1)) ani--;
		}
		
		
		g.g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
		g.drawRotatedImage(Assets.ani[3][ani], x+camX, y+camY, 50, 50, rotation);
		
		g.g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
}
