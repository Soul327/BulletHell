package Boss;

import java.awt.Color;

import GameState.Bullet;
import GameState.World;
import Main.Main;
import Main.StateManager;
import Misc.Graphics;
import Misc.MouseManager;

public class Robbin extends Boss{
	public Robbin() {
		name = "Robbin, the Asshole";
		des = "He's just a dick";
		health = 1049+698;
		maxHealth = health;
	}
	boolean inAct = false;
	int act = 0, actTick = 0;
	double temp[] = new double[10];
	public void tick2() {
		if(inAct) {
			switch(act) {
				case 0:
					switch(actTick) {
						case 0 :star(5, 00);break;
						case 10:star(5, 10);break;
						case 20:star(5, 20);break;
						case 30:star(5, 30);break;
						case 40:star(5, 40);break;
						case 50:star(5, 50);break;
						case 60:star(5, 60);break;
						case 70:star(5, 70);break;
						case 80:star(5, 80);break;
					}
					if(actTick>100) {
						if(Math.random()>.5) inAct = false;
						else inAct = true;
					}
					break;
				case 1:
					switch(actTick) {
						case 0 :star(5, 80);break;
						case 10:star(5, 70);break;
						case 20:star(5, 60);break;
						case 30:star(5, 50);break;
						case 40:star(5, 40);break;
						case 50:star(5, 30);break;
						case 60:star(5, 20);break;
						case 70:star(5, 10);break;
						case 80:star(5, 00);break;
					}
					if(actTick>100) {
						if(Math.random()>.5) inAct = false;
						else inAct = true;
					}
					break;
				case 2:if(actTick>60) inAct = false; break;
				case 3:
					if(actTick==0 & health<=maxHealth/3) star(90, 0);
					if(actTick==30) {
						x = (int)(Math.random()*(1600-width));
						y = (int)(Math.random()*(900-height));
					}
					if(actTick>60) inAct = false;
					break;
				case 4:
					if(actTick==0) star(180, 0);
					if(actTick>60) inAct = false;
					break;
				default:inAct = false;
			}
			actTick++;
		}else {
			act = (int)(Math.random()*10);
			actTick = 0;
			inAct = true;
		}
	}
	public void render(Graphics g) {
		if(Main.scaling) {
			g.setColor(Color.red); 
			g.fillRect(
					(x+StateManager.gameState.world.camX)*Main.scale,
					(y+StateManager.gameState.world.camY)*Main.scale,
					(width)*Main.scale,
					(height)*Main.scale);
			
		}else {
		//Draw Shadow
		double temp = Math.abs(heightFromGround/2.0);
		if(temp>width) temp = width;
		int t = (int)Math.abs(((temp/height)*255)-255);
		g.setColor(new Color(50,50,50,t)); 
		g.fillRect(x+StateManager.gameState.world.camX+temp/2 + (Math.pow(heightFromGround,1.1)/2.0)*Math.cos(Math.toDegrees(World.timeOfDay)), y+StateManager.gameState.world.camY+heightFromGround+temp/2, width-temp, height-temp);
		g.setColor(Color.red); g.fillRect(x+StateManager.gameState.world.camX, y+StateManager.gameState.world.camY, width, height);
		g.setColor(Color.black); g.drawRect(x+StateManager.gameState.world.camX, y+StateManager.gameState.world.camY, width, height);
		}
	}
}