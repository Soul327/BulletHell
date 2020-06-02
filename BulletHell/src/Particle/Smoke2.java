package Particle;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;
import Misc.Prerender;

public class Smoke2 extends Particle{
	
	int maxLife = -1,life = maxLife, ani = 7;
	BufferedImage image = null;
	
	public Smoke2(double x,double y) {
		this.x = x;
		this.y = y;
		if(x<0 | x>StateManager.gameState.world.width | y<0 | y>StateManager.gameState.world.height) remove = true;
		image = Prerender.smoke[(int)(Math.random()*Prerender.smoke.length)];
		//compress();
		int width = image.getWidth(), height = image.getHeight();
		while(true) {
			if(x+width >StateManager.gameState.world.width ) { width--;  continue; }
			if(y+height>StateManager.gameState.world.height) { height--; continue; }
			break;
		}
		if(width!=image.getWidth() | height!=image.getHeight()) image = image.getSubimage(0, 0, width, height);
	}
	
	public void render(Graphics g) {
		g.drawImage(image, x+StateManager.gameState.world.camX, y+StateManager.gameState.world.camY, image.getWidth(), image.getHeight());
	}
}
