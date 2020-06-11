package Boss;

import java.awt.Color;
import java.util.ArrayList;

import GameState.Grenade;
import Main.Main;
import Main.StateManager;
import Misc.Graphics;
import Misc.Mat;
import Misc.MouseManager;
import World.World;

public class NightsRow extends Boss{
	double grenadeRange = 400;
	public ArrayList<Grenade> grenade = new ArrayList<Grenade>();
	
	public NightsRow() {
		name = "Nite Row";
		des = "An explosive encounter";
		health = 4000;
		maxHealth = health;
		//health = 1300;
		width = 75;
		height = 75;
	}
	public void tiedTick() {
		//Tick Grenades
		for(int a=0;a<grenade.size();a++) {
			grenade.get(a).tick();
			if(grenade.get(a).remove) {
				grenade.remove(a);
				a--;
			}
		}
	}
	
	int delay = 120;
	public void tick2() {
		if(Mat.distance(x, y, StateManager.gameState.world.player.x, StateManager.gameState.world.player.y)>grenadeRange/2) {
			if(x<StateManager.gameState.world.player.x) x++;
			if(x>StateManager.gameState.world.player.x) x--;
			if(y<StateManager.gameState.world.player.y) y++;
			if(y>StateManager.gameState.world.player.y) y--;
		}
		
		if(inAct) {
			switch(act) {
				case 0://Stomp and drop grenades
					switch(actTick) {
						case 0:y-=10;break;
						case 1:y+=1;break;
						case 10:
							for(int z=0;z<Math.random()*1000;z++) {
								Grenade gg = new Grenade();
								gg.fuse += (gg.fuse*2)*Math.random();
								//if(Math.random()<.5) gg.type = 2;
								gg.x = Math.random() * (1600-gg.width);
								gg.y = Math.random() * (900-gg.height);
								grenade.add(gg);
							}
					}
					if(actTick>1 & actTick<10) y+=1;
					if(actTick>20) {
						inAct = false;
					}
					break;
				case 1://Shoot accurately 
					if(actTick%10==0) {
						Grenade gg = toss();
						if(Math.random()>(health/maxHealth)+.25) gg.type = 2;
						grenade.add(gg);
					}
					if(actTick>60 + delay) inAct = false;
					break;
				case 2://Shoot at the player with shotgun
					if(actTick%2==0) {
						Grenade gg = new Grenade();
						if(health<=maxHealth/20) gg.type = 2;
						//*
						double angle = Mat.getAngle(
								this.x+gg.width/2+width/2,
								this.y+gg.height/2+height/2,
								StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2,
								StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2),
							distance = Mat.distance(
								this.x+gg.width/2+width/2, 
								this.y-gg.height/2+height/2, 
								StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2, 
								StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2),
							accuracy = 25;
						if(distance>grenadeRange) distance = grenadeRange;
						angle += Math.toRadians( (Math.random()*(100-accuracy)-(100-accuracy)/2) / (accuracy/10) );
						distance += (Math.random()*100-50);
						gg.x = this.x+width/2;
						gg.y = this.y+height/2;
						gg.setRadAngle(distance, angle);
						//*/
						grenade.add(gg);
					}
					if(actTick>60 + delay) inAct = false;
					break;
				case 3://Teleport
					if(actTick==0 & health<=maxHealth/3) {
						Grenade gg = toss();
						gg.type = 3;
						grenade.add(gg);
					}
					if(actTick==30) {
						x = (int)(Math.random()*(1600-width));
						y = (int)(Math.random()*(900-height));
					}
					if(actTick>60 + delay) inAct = false;
					break;
				default:inAct = false;
			}
			actTick++;
		}else {
			act = (int)(Math.random()*10);
			//act = 3;
			actTick = 0;
			inAct = true;
		}
	}
	public Grenade toss() {
		Grenade gg = new Grenade();
		double angle = Mat.getAngle(
				this.x+gg.width/2+width/2,
				this.y+gg.height/2+height/2,
				StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2,
				StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2);
		double distance = Mat.distance(
				this.x+gg.width/2+width/2, 
				this.y+gg.height/2+height/2, 
				StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2, 
				StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2);
		if(distance>grenadeRange) distance = grenadeRange; 
		gg.x = this.x+width/2;
		gg.y = this.y+height/2;
		gg.setRadAngle(distance, angle);
		
		return gg;
	}
	
	public void render(Graphics g) {
		double scale = Main.scale;
		if(Main.scaling) {
			//Draw Shadow
			double temp = Math.abs(heightFromGround/2.0);
			if(temp>width) temp = width;
			int t = (int)Math.abs(((temp/height)*255)-255);
			g.setColor(new Color(50,50,50,t)); 
			g.fillRect(
					(x+StateManager.gameState.world.camX+temp/2 + (Math.pow(heightFromGround,1.1)/2.0)*Math.cos(Math.toDegrees(World.timeOfDay)))*scale, 
					(y+StateManager.gameState.world.camY+heightFromGround+temp/2)*scale, width-temp, height-temp);
				
			g.setColor(Color.red);
			g.fillRect(
					(x+StateManager.gameState.world.camX)*scale, 
					(y+StateManager.gameState.world.camY)*scale, width*scale, height*scale);
			g.setColor(Color.black);
			g.drawRect(
					(x+StateManager.gameState.world.camX)*scale, 
					(y+StateManager.gameState.world.camY)*scale, width*scale, height*scale);
			for(Grenade gg:grenade) gg.render(g);
		}else {
			//Draw Shadow
			double temp = Math.abs(heightFromGround/2.0);
			if(temp>width) temp = width;
			int t = (int)Math.abs(((temp/height)*255)-255);
			g.setColor(new Color(50,50,50,t)); 
			g.fillRect(x+StateManager.gameState.world.camX+temp/2 + (Math.pow(heightFromGround,1.1)/2.0)*Math.cos(Math.toDegrees(World.timeOfDay)), y+StateManager.gameState.world.camY+heightFromGround+temp/2, width-temp, height-temp);
				
			g.setColor(Color.red);
			g.fillRect(x+StateManager.gameState.world.camX, y+StateManager.gameState.world.camY, width, height);
			g.setColor(Color.black);
			g.drawRect(x+StateManager.gameState.world.camX, y+StateManager.gameState.world.camY, width, height);
		}
	}
	
}