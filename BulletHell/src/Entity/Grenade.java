package Entity;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Main.Main;
import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;

public class Grenade extends Entity{
	public boolean playerDam = true,bossDam = false;
	public int fuse = Main.maxFPS, type = 1;
	public double damage = 10,ex = 5,heightFromGround = 0;
	public Grenade() {}
	public Grenade(double power, double angle) {
		this.velX = power * Math.cos(Math.toRadians(angle));
		this.velY = power * Math.sin(Math.toRadians(angle));
	}
	
	public void setRadAngle(double power,double angle) {
		velX = (power/Main.maxFPS) * Math.cos(angle);
		velY = (power/Main.maxFPS) * Math.sin(angle);
	}
	public void setDegAngle(double power,double angle) {
		velX = (power/Main.maxFPS) * Math.cos(Math.toDegrees(angle));
		velY = (power/Main.maxFPS) * Math.sin(Math.toDegrees(angle));
	}
	
	public void tick() {
		tickVelocity();
		fuse--;
		if(fuse<-ex) 
			remove = true;
		if(fuse==0) {
			//new PlaySound("res/Sounds/explosions/explosion0"+(int)(Math.random()*8+1)+".wav").start();
			//new PlaySound("res/Sounds/boom_pack/boom1.wav").start();
		}
		if(fuse<=0) {
			//Check Explosion Damage
			if(playerDam) {
				double dis = Math.sqrt(
						Math.pow( (StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2) - (x + width/2), 2) + 
						Math.pow( (StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2) - (y + height/2), 2));
				if(dis<=(fuse/-ex)*100-StateManager.gameState.world.player.width-5) {
					StateManager.gameState.world.player.health -=10;
					playerDam = false;
				}
			}
			if(bossDam) {
				double dis = Math.sqrt(
						Math.pow( (StateManager.gameState.world.boss.x+StateManager.gameState.world.boss.width/2) - (x + width/2), 2) + 
						Math.pow( (StateManager.gameState.world.boss.y+StateManager.gameState.world.boss.height/2) - (y + height/2), 2));
				if(dis<=(fuse/-ex)*100-StateManager.gameState.world.boss.width-5) {
					StateManager.gameState.world.boss.health -=10;
					bossDam = false;
				}
				bossDam = false;
			}
		}
	}
	
	public void render(Graphics g) {
		double camX = StateManager.gameState.world.camX;
		double camY = StateManager.gameState.world.camY;
		g.scalable = true;
		
		BufferedImage image;
		switch(type) {
			case 2:image = Assets.assets[3];break;
			case 3:image = Assets.assets[4];break;
			default:image = Assets.assets[2];
		}
		if(fuse%10==0 & fuse%10==1)
			switch(type) {
				case 2:image = Assets.assets[10];break;
				case 3:image = Assets.assets[11];break;
				default:image = Assets.assets[9];
			}
		
		//Draw Shadow
		/*
		double temp = Math.abs(heightFromGround/2.0);
		if(temp>width) temp = width;
		int t = (int)Math.abs(((temp/height)*255)-255);
		g.scalable = true;
		g.setColor(new Color(50,50,50,t)); 
		g.fillOval(
				x+StateManager.gameState.world.camX+temp/2 + (Math.pow(heightFromGround,1.1)/2.0)*Math.cos(Math.toDegrees(World.timeOfDay)), 
				y+StateManager.gameState.world.camY+heightFromGround+temp/2, 
				width-temp, height-temp);
		g.scalable = false;
		*/
		
		//Draw Grenade
		g.drawImage(image,x+camX, y+camY, width, height);
		
		//Explosion
		if(fuse<=0) {
			g.setColor(Color.red);
			g.fillCircle((x+camX+width/2)-(fuse/-ex)*50, (y+camY+height/2)-(fuse/-ex)*50, (fuse/-ex)*100);
			switch(type) {
				case 2:image = Assets.assets[22];break;
				case 3:image = Assets.assets[22];break;
				default:image = Assets.assets[20];
			}
			g.drawImage(
					image,
					(x+camX+width/2)-(fuse/-ex)*50,
					(y+camY+height/2)-(fuse/-ex)*50,
					(fuse/-ex)*100,(fuse/-ex)*100);
			velX = 0; velY = 0;
		}
	}
}
