package GameState;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;

public class Lazer {
	
	public boolean remove = false;
	public double x,y,angle,length=0,maxLength=-1;
	public Lazer last;
	public Color color = Color.red;
	
	public Lazer() {}
	public Lazer(Lazer last) {
		this.last = last;
		x = last.x + last.length*Math.cos(Math.toRadians(last.angle));
		y = last.y + last.length*Math.sin(Math.toRadians(last.angle));
	}
	public void tick() {
		if(last!=null) {
			x = last.x + last.length*Math.cos(Math.toRadians(-last.angle));
			y = last.y + last.length*Math.sin(Math.toRadians(-last.angle));
		} 
		checkLazer();
		angle++;
	}
	public void checkLazer() {
		boolean con = true;
		length = 0;
		while(con) {
			length++;
			double tempX = x + length*Math.cos(Math.toRadians(-angle));
			double tempY = y + length*Math.sin(Math.toRadians(-angle));
			if( tempX<=0 | 
					tempY<=0 | 
					tempX>=1600 | 
					tempY>=900)
				con = false;
			if(length>=maxLength & maxLength!=-1) con = false;
		}
	}
	public void render(Graphics g) {
		double camX = StateManager.gameState.world.camX;
		double camY = StateManager.gameState.world.camY;
		Polygon poly = new Polygon();
		poly.addPoint((int) (x+camX),(int) (y+camY));
		poly.addPoint((int) (x+camX+100),(int) (y+camY));
		poly.addPoint((int) (x+camX+100),(int) (y+camY)+10);
		poly.addPoint((int) (x+camX),(int) (y+camY+10));
		
		
		g.setColor(color);
		g.g.fill(g.rotatePolygon(poly, -angle,new Point( (int)(x+camX),(int)(y+camY+10) )));
		g.setColor(Color.white);
		g.drawLine(x+camX, y+camY,x+length*Math.cos(Math.toRadians(-angle))+camX,y+length*Math.sin(Math.toRadians(-angle))+camY);
		g.fillCenterCircle(x+camX, y+camY, 10);
		//Old Render
		/*
		g.setColor(Color.white);
		g.drawLine(x+camX, y+camY,x+length*Math.cos(Math.toRadians(-angle))+camX,y+length*Math.sin(Math.toRadians(-angle))+camY);
		g.setColor(color.brighter());
		g.drawLine(x+camX+1, y+camY+1,x+length*Math.cos(Math.toRadians(-angle))+camX+1, y+length*Math.sin(Math.toRadians(-angle))+camY+1);
		g.drawLine(x+camX+2, y+camY+2,x+length*Math.cos(Math.toRadians(-angle))+camX+2, y+length*Math.sin(Math.toRadians(-angle))+camY+2);
		g.drawLine(x+camX-1, y+camY-1,x+length*Math.cos(Math.toRadians(-angle))+camX-1, y+length*Math.sin(Math.toRadians(-angle))+camY-1);
		g.drawLine(x+camX-2, y+camY-2,x+length*Math.cos(Math.toRadians(-angle))+camX-2, y+length*Math.sin(Math.toRadians(-angle))+camY-2);
		g.setColor(color);
		g.drawLine(x+camX+3, y+camY+3,x+length*Math.cos(Math.toRadians(-angle))+camX+3, y+length*Math.sin(Math.toRadians(-angle))+camY+3);
		g.drawLine(x+camX+4, y+camY+4,x+length*Math.cos(Math.toRadians(-angle))+camX+4, y+length*Math.sin(Math.toRadians(-angle))+camY+4);
		g.drawLine(x+camX-4, y+camY-4,x+length*Math.cos(Math.toRadians(-angle))+camX-4, y+length*Math.sin(Math.toRadians(-angle))+camY-4);
		g.drawLine(x+camX-3, y+camY-3,x+length*Math.cos(Math.toRadians(-angle))+camX-3, y+length*Math.sin(Math.toRadians(-angle))+camY-3);
		*/
		//g.fillCircle(x+camX, y+camY, 5);
		/*
		g.drawRotatedImage(Assets.assets[23], 
				x+camX, y+camY, 
				x+length*Math.cos(Math.toRadians(-angle))+camX, 
				y+length*Math.sin(Math.toRadians(-angle))+camY, -angle+90);
		//*/
	}
}
