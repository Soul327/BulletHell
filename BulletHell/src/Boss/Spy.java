package Boss;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import Main.Main;
import Main.StateManager;
import Misc.Graphics;
import Particle.Smoke2;

public class Spy extends Boss{
	
	//Fakes
	Point[] point = new Point[10];
	int[] pointTime = new int[10];
	int index = 0, vis = 255;
	
	ArrayList<Lazer> lazers = new ArrayList<Lazer>();
	static ArrayList<Mirror> mirrors = new ArrayList<Mirror>();
	
	public Spy() {
		name = "Un-named";
		des = "";
		health = 3000;
		maxHealth = health;
		//health = 1300;
		width = 75;
		height = 75;
		x = 800 - width/2;
		y = 900/3 - height;
		//presets((int)(Math.random()*1));
	}
	
	public void bootup(int tick) {}
	
	public void presets() {
		int topLeft     = (int)(Math.random()*2);
		int topRight    = (int)(Math.random()*1);
		int bottomRight = (int)(Math.random()*1);
		int bottomLeft  = (int)(Math.random()*1);
		
		//Laser One ~ Top left
		switch( topLeft ) {
			case 0:
				lazers.add(new Lazer(0, 100, 0));
				mirrors.add(new Mirror(1600/3*2,100,-45));
				break;
			case 1:
				lazers.add(new Lazer(0, 0, 29.5));
				mirrors.add(new Mirror(1300,750,45));
				break;
		}
		//Laser Two ~ Top Right
		switch( topRight ) {
			case 0:
				lazers.add(new Lazer(StateManager.gameState.world.width-100, 0, 90));
				mirrors.add(new Mirror(1600-100,200,45));
				break;
		}
		//Laser Three ~ Bottom Right
		switch( bottomRight ) {
			case 0:
				lazers.add(new Lazer(StateManager.gameState.world.width, StateManager.gameState.world.height-100, 180));
				mirrors.add(new Mirror(1600/3,800,45));
				break;
		}
		//Laser Four ~ Bottom Left
		switch( bottomLeft ) {
			case 0:
				lazers.add(new Lazer(100, StateManager.gameState.world.height, 270));
				mirrors.add(new Mirror(100,900/2,45));
				break;
		}
	}
	
	boolean firstTick = true;
	public void tick2() {
		if(firstTick) { presets(); firstTick = false; }
		for(Lazer laz:lazers)	laz.findEnd(-1);
		
		if(inAct) {
			switch(act) {
				case 0://Spawn decoys
					if(actTick==0) {
						spawnDecoy((int)(Math.random()*StateManager.gameState.world.width),(int)(Math.random()*StateManager.gameState.world.height));
					}
					if(actTick>120) inAct = false;
					break;
				case 1://Spawn smoke2
					if(actTick==0) {
						StateManager.gameState.world.particles.add(new Smoke2((int)(Math.random()*StateManager.gameState.world.width),(int)(Math.random()*StateManager.gameState.world.height)));
						StateManager.gameState.world.particles.add(new Smoke2((int)(Math.random()*StateManager.gameState.world.width),(int)(Math.random()*StateManager.gameState.world.height)));
						StateManager.gameState.world.particles.add(new Smoke2((int)(Math.random()*StateManager.gameState.world.width),(int)(Math.random()*StateManager.gameState.world.height)));
						StateManager.gameState.world.particles.add(new Smoke2((int)(Math.random()*StateManager.gameState.world.width),(int)(Math.random()*StateManager.gameState.world.height)));
						StateManager.gameState.world.particles.add(new Smoke2((int)(Math.random()*StateManager.gameState.world.width),(int)(Math.random()*StateManager.gameState.world.height)));
					}
					if(actTick>60) inAct = false;
					break;
				case 2://Rotate mirrors
					if(actTick==60) {
						for(Mirror mirror:mirrors) {
							mirror.targetAngle = Math.random()*360;
						}
					}
					if(actTick>60*5) inAct = false;
					break;
				case 3://Fade
					if(actTick==0)StateManager.gameState.world.particles.add(new Smoke2(x-width/2,y-height/2));
					if(vis>0) vis-=2;
					if(vis==0) {
						x = Math.random() * StateManager.gameState.world.width;
						y = Math.random() * StateManager.gameState.world.height;
						inAct = false;
					}
					if(actTick>255) inAct = false;
					break;
				case 4://Reapear
					if(vis>0) vis+=2;
					if(vis>=255) inAct = false;
					if(actTick>150) inAct = false;
					break;
				default:inAct = false;
			}
			actTick++;
		}else {
			temp = new double[10];
			act = (int)(Math.random()*10);
			//act = 1;
			actTick = 0;
			inAct = true;
		}
		for(Mirror mirror:mirrors)
			mirror.tick();
	}
	
	public void spawnDecoy(int x, int y) {
		point[index] = new Point();
		point[index].x = x;
		point[index].y = y;
		index++;
		if(index>=point.length) index = 0;
	}
	
	public void render(Graphics g) {
		g.scalable = true;
		if(vis<0) vis = 0;
		if(vis>255) vis = 255;
		
		//Draw Char
		g.setColor(new Color(150,150,150,vis));
		g.fillRect(x+StateManager.gameState.world.camX, y+StateManager.gameState.world.camY, width, height);
		g.setColor(new Color(0,0,0,vis));
		g.drawRect(x+StateManager.gameState.world.camX, y+StateManager.gameState.world.camY, width, height);
		//Draw Fakes
		for(int z=0;z<point.length;z++) {
			if(point[z]==null) continue;
			g.setColor(new Color(150,150,150,200));
			g.fillRect(point[z].getX()+StateManager.gameState.world.camX,point[z].getY()+StateManager.gameState.world.camY, width, height);
		}
		g.scalable = true;
		for(Lazer l:lazers) l.render(g);
		for(Mirror m:mirrors) m.render(g);
	}
}

class Mirror{
	public int x = 0,y = 0;
	public double angle = 0,length = 100,targetAngle;
	double speed = .2;
	
	public Mirror(int x, int y, double angle) {
		this.x = x;
		this.y = y;
		this.angle = angle+90;
		targetAngle = this.angle;
	}
	public void setAngle(double angle) {
		while(angle>360) angle-=360;
		while(angle<0) angle+=360;
		this.angle = angle;
	}
	public void tick() {
		if(targetAngle<angle-1) angle-=speed;
		if(targetAngle>angle+1) angle+=speed;
	}
	public void render(Graphics g) {
		double camX = StateManager.gameState.world.camX, camY = StateManager.gameState.world.camY;
		//*
		g.scalable = true;
		g.setColor(Color.green);
		g.drawLine(
				x+camX-(length/2)*Math.sin(Math.toRadians(angle)),
				y+camY-(length/2)*Math.cos(Math.toRadians(angle)),
				x+camX+(length/2)*Math.sin(Math.toRadians(angle)),
				y+camY+(length/2)*Math.cos(Math.toRadians(angle)) );
		//*/
		g.scalable = false;
	}
}
class Lazer{
	double startingAngle,angle,length;
	Point startPoint = new Point();
	ArrayList<Point> endPoint = new ArrayList<Point>();
	
	public Lazer(int x, int y, double angle) {
		startPoint.x = x;
		startPoint.y = y;
		this.angle = angle;
		startingAngle = angle;
		findEnd(-1);
	}
	public Point findEnd(double end) {
		length = 0;
		double x = startPoint.x, y = startPoint.y;
		double sx = startPoint.x, sy = startPoint.y;
		endPoint = new ArrayList<Point>();
		length = 0;
		angle = startingAngle;
		for(int z = 0;z<Math.pow(10,4);z++) {
			for(Mirror mirror:Spy.mirrors) {
				//starting point of line 1
				Point2D.Double temp1 = new Point2D.Double(sx , sy);
				//ending point of line 1
				Point2D.Double temp2 = new Point2D.Double(x+1 * Math.cos(Math.toRadians(angle)), y+1 * Math.sin(Math.toRadians(angle)));
				//starting point of line 2
				Point2D.Double temp3 = new Point2D.Double(
						mirror.x-(mirror.length/2.0)*Math.sin(Math.toRadians(mirror.angle)), 
						mirror.y-(mirror.length/2.0)*Math.cos(Math.toRadians(mirror.angle)));
				//ending point of line 2
				Point2D.Double temp4 = new Point2D.Double(
						mirror.x+(mirror.length/2.0)*Math.sin(Math.toRadians(mirror.angle)), 
						mirror.y+(mirror.length/2.0)*Math.cos(Math.toRadians(mirror.angle)));

				//determine if the lines intersect
				boolean intersects = Line2D.linesIntersect(temp1.x, temp1.y, temp2.x, temp2.y, temp3.x, temp3.y, temp4.x, temp4.y);

				//determines if the lines share an endpoint
				boolean shareAnyPoint = shareAnyPoint(temp1, temp2, temp3, temp4);
				
				if (intersects & !shareAnyPoint) {
					angle = (180-angle)-2*mirror.angle;
					sx = x;
					sy = y;
					Point point = new Point();
					point.setLocation(x, y);
					endPoint.add(point);
				}
				/*
				if((int)Math.round(x)==mirror.x & (int)Math.round(y)==mirror.y) { //Check if touching mirror
					angle = 90;
					Point point = new Point();
					point.setLocation(x, y);
					endPoint.add(point);
				}
				//*/
			}
			x += 1 * Math.cos(Math.toRadians(angle));
			y += 1 * Math.sin(Math.toRadians(angle));
			length += Math.abs(Math.cos(Math.toRadians(angle))) + Math.abs(Math.sin(Math.toRadians(angle)));
			if(length>=end & end!=-1) {
				Point point = new Point();
				point.setLocation(x, y);
				return point;
			}
			if(StateManager.gameState.world.player.contains(x, y)) {
				StateManager.gameState.world.player.health -= 1;
				break;
			}
			
			if(x<0) break;
			if(x>StateManager.gameState.world.width) break;
			if(y<0) break;
			if(y>StateManager.gameState.world.height) break;
		}
		Point point = new Point();
		point.setLocation(x, y);
		endPoint.add(point);
		return null;
	}
	
	public static boolean shareAnyPoint(Point2D.Double A, Point2D.Double B, Point2D.Double C, Point2D.Double D) {
		if (isPointOnTheLine(A, B, C)) return true;
		else if (isPointOnTheLine(A, B, D)) return true;
		else if (isPointOnTheLine(C, D, A)) return true;
		else if (isPointOnTheLine(C, D, B)) return true;
		else return false;
	}
	
	public static boolean isPointOnTheLine(Point2D.Double A, Point2D.Double B, Point2D.Double P) {  
		double m = (B.y - A.y) / (B.x - A.x);
		
		//handle special case where the line is vertical
		if (Double.isInfinite(m)) {
			if(A.x == P.x) return true;
			else return false;
		}
		
		if ((P.y - A.y) == m * (P.x - A.x)) return true;
		else return false;
	}
	
	public void render(Graphics g) {
		double camX = StateManager.gameState.world.camX, camY = StateManager.gameState.world.camY;
		
		g.setColor(Color.red);
		if(endPoint.size()>0) g.drawLine(
				startPoint.x+camX,
				startPoint.y+camY,
				endPoint.get(0).x+camX,
				endPoint.get(0).y+camY);
		for(int z=1;z<endPoint.size();z++)
			g.drawLine(
					endPoint.get(z-1).x+camX,
					endPoint.get(z-1).y+camY,
					endPoint.get(z).x+camX,
					endPoint.get(z).y+camY);
	}
}