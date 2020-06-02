package Boss;

import java.awt.Color;
import java.awt.Point;

import GameState.Grenade;
import GameState.Lazer;
import GameState.World;
import Main.StateManager;
import Misc.Graphics;
import Misc.Mat;
import Misc.MouseManager;
import Particle.Particle;
import Particle.Smoke;
import Particle.Smoke2;

public class Spy extends Boss{
	
	double grenadeRange = 200;
	
	Point[] point = new Point[10];
	int[] pointTime = new int[10];
	int index = 0;
	
	public Spy() {
		name = "Un-named";
		des = "";
		health = 3000;
		maxHealth = health;
		//health = 1300;
		width = 75;
		height = 75;
	}
	
	boolean flip = true;
	int delay = 120;
	
	public void tick2() {
		heightFromGround++;if(heightFromGround>100) heightFromGround = 0;
		if(flip) {
			/*
			Lazer lazer = new Lazer();
			lazer.x = 100;
			lazer.y = 100;
			lazer.angle = 90;
			//lazer.maxLength = 200;
			lazer.color = Color.green;
			StateManager.gameState.world.lazer.add(lazer);
			flip = false;
			/*
			Lazer lazer2 = new Lazer(lazer);
			lazer2.angle = 45;
			StateManager.gameState.world.lazer.add(lazer2);
			//*/
		}
		if(inAct) {
			switch(act) {
				case 0://Spawn decoys
					if(actTick==0) {
						point[index] = new Point();
						point[index].x = (int)(Math.random()*StateManager.gameState.world.width);
						point[index].y = (int)(Math.random()*StateManager.gameState.world.height);
						index++;
						if(index>=point.length) index = 0;
					}
					if(actTick>120) inAct = false;
					break;
				/*
				case 1://Spawn smoke
					if(actTick==0) {
						double x = (int)(Math.random()*StateManager.gameState.world.width);
						double y = (int)(Math.random()*StateManager.gameState.world.height);
						//double x = StateManager.gameState.world.player.x + StateManager.gameState.world.player.width/2;
						//double y = StateManager.gameState.world.player.y + StateManager.gameState.world.player.height/2;
						for(int z=0;z<50;z++) {
							int range = 100;
							double tempX = x+(Math.random()*range)*Math.sin(Math.random()*360);
							double tempY = y+(Math.random()*range)*Math.cos(Math.random()*360);
							StateManager.gameState.world.particles.add(new Smoke( tempX, tempY ,40));
						}
					}
					if(actTick>60) inAct = false;
					break;
				//*/
				case 1://Spawn smoke2
					if(actTick==0) {
						double x = (int)(Math.random()*StateManager.gameState.world.width);
						double y = (int)(Math.random()*StateManager.gameState.world.height);
						StateManager.gameState.world.particles.add(new Smoke2(x,y));
					}
					if(actTick>60) inAct = false;
					break;
				default:inAct = false;
			}
			actTick++;
		}else {
			/*
			act = (int)(Math.random()*10);
			act = 1;
			actTick = 0;
			inAct = true;
			//*/
		}
	}
	
	public void render(Graphics g) {
		//Draw Shadow
		double temp = Math.abs(heightFromGround/2.0);
		if(temp>width) temp = width;
		int t = (int)Math.abs(((temp/height)*255)-255);
		g.setColor(new Color(50,50,50,t)); 
		g.fillRect(x+StateManager.gameState.world.camX+temp/2 + (Math.pow(heightFromGround,1.1)/2.0)*Math.cos(Math.toDegrees(World.timeOfDay)), y+StateManager.gameState.world.camY+heightFromGround+temp/2, width-temp, height-temp);
		//Draw Char
		g.setColor(Color.red);
		g.fillRect(x+StateManager.gameState.world.camX, y+StateManager.gameState.world.camY, width, height);
		g.setColor(Color.black);
		g.drawRect(x+StateManager.gameState.world.camX, y+StateManager.gameState.world.camY, width, height);
		//Draw Fakes
		for(int z=0;z<point.length;z++) {
			if(point[z]==null) continue;
			g.setColor(new Color(255,0,0,200));
			g.fillRect(point[z].getX()+StateManager.gameState.world.camX,point[z].getY()+StateManager.gameState.world.camY, width, height);
		}
	}
}