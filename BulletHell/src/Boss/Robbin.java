package Boss;

import java.awt.Color;

import GameState.Bullet;
import Main.Main;
import Main.StateManager;
import Misc.Graphics;
import Misc.Mat;
import Misc.MouseManager;
import Misc.SoundManager;
import World.World;

public class Robbin extends Boss{
	public Robbin() {
		name = "Robbin";
		title = "the Asshole";
		des = "He's just a dick";
		health = 1800;
		maxHealth = health;
	}
	public void init() {
		SoundManager.playLoopingSound("res/Game.wav", 1f);
	}
	
	public void tick2() {
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
				case 5://Shoot at player
					double playerAngle = -Math.toDegrees(Mat.getAngle(
							x+width/2,
							y+height/2,
							StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2,
							StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2));
					switch(actTick) {
						case 0: shoot(playerAngle); break;
						case 5: shoot(5+playerAngle); shoot(-5+playerAngle); break;
						case 10: shoot(10+playerAngle); shoot(-10+playerAngle); break;
						case 15: shoot(5+playerAngle); shoot(-5+playerAngle); break;
						case 20: shoot(playerAngle); break;
					}
					if(actTick>20) inAct = false;
					break;
				//*/
				default:inAct = false;
			}
			actTick++;
		}else {
			temp = new double[10];
			act = (int)(Math.random()*10);
			//act = 6;
			actTick = 0;
			inAct = true;
		}
	}
	public void shoot(double angle) {
		Bullet bul = new Bullet(4,angle);
		bul.damage = 10;
		bul.x = x+width/2-bul.width/2;
		bul.y = y+height/2-bul.height/2;
		StateManager.gameState.world.bullet.add(bul);
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