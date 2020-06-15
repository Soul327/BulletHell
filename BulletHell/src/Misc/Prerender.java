package Misc;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Prerender extends Thread{
	
	public static int prerenderSmokeSize = 100,prerenderSmoke;
	public static BufferedImage[] smoke;
	
	public static void smoke() {
		smoke = new BufferedImage[prerenderSmokeSize];
		for(prerenderSmoke=0;prerenderSmoke<prerenderSmokeSize;prerenderSmoke++) {
			double radius = 100,size=20;
			int si = (int)Math.ceil( radius*2+size*2 );
			BufferedImage image = new BufferedImage(si, si, BufferedImage.TYPE_INT_ARGB);
			Graphics g = new Graphics((Graphics2D)image.getGraphics());
			g.g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			for(int z=0;z<250;z++) {
				int range = 90;
				double tempX = (Math.random()*range)*Math.sin(Math.random()*360)+si/2;
				double tempY = (Math.random()*range)*Math.cos(Math.random()*360)+si/2;
				g.drawRotatedImage(Assets.ani[3][0], tempX, tempY, size, size, Math.random()*360);
			}
			smoke[prerenderSmoke] = image;
		}
	}

	public void run() {
		smoke();
		//System.out.println("Done");
	}
}
