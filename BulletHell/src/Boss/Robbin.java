package Boss;

import java.awt.Color;

import GameState.Bullet;
import GameState.World;
import Main.Main;
import Main.StateManager;
import Misc.Graphics;
import Misc.MouseManager;
import Misc.MusicPlayer;

public class Robbin extends Boss{
	public Robbin() {
		name = "Robbin, the Asshole";
		des = "He's just a dick";
		health = 1800;
		maxHealth = health;
	}
	boolean first = true;
	public void tick2() {
		if(first) {
			first = false;
			MusicPlayer.playerMusic("res/Game.wav");
		}
		if(inAct) {
			switch(act) {
				case 0://Spin and shoot
					star(5, actTick);
					if(actTick>200) {
						if(Math.random()>.5) inAct = false;
						else inAct = true;
					}
					break;
				case 1://Spin and shoot
					star(5, -actTick);
					if(actTick>200) {
						if(Math.random()>.5) inAct = false;
						else inAct = true;
					}
					break;
				case 2://Wait
					if(actTick>60) inAct = false; break;
				case 3:
					if(actTick==0 & health<=maxHealth/3) star(90, 0);
					if(actTick==30) {
						x = (int)(Math.random()*(1600-width));
						y = (int)(Math.random()*(900-height));
					}
					if(actTick>60) inAct = false;
					break;
				case 4://Circle
					if(actTick==0) star(20, 0);
					if(actTick>60) inAct = false;
					break;
				//*
				case 5://Mine field
					if(actTick%10==0) temp[0]++;
					switch((int)temp[0]) {
						case 0:
							star(10, 0);
							break;
						case 1:
							star(10, 45);
							break;
					}
					if(temp[0]>1) temp[0] = 0;
					if(actTick>60*1) inAct = false;
					break;
				//*/
				default:inAct = false;
			}
			actTick++;
		}else {
			temp = new double[10];
			act = (int)(Math.random()*10);
			act = 5;
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