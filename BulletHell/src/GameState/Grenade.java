package GameState;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Boss.Boss;
import Boss.NightsRow;
import Main.Main;
import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;
import Misc.MouseManager;
import Misc.PlaySound;
import World.World;

public class Grenade {
	public boolean playerDam = true,bossDam = false, remove = false;
	public int fuse = Main.maxFPS, type = 1;
	public double x=100,y=100,width=15,height=15,velX,velY,damage = 10,ex = 5,heightFromGround = 0;
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
		x+=velX;
		y+=velY;
		fuse--;
		if(fuse<-ex) 
			remove = true;
		if(fuse==0 & type == 2) {
			int points = (int) (Math.random()*5 + 5);
			double angle = Math.random()*90;
			for(int z=0;z<points;z++) {
				Bullet bul = new Bullet(2,(360/points)*z+angle);
				bul.damage = 10;
				bul.x = x+width/2-bul.width/2;
				bul.y = y+height/2-bul.height/2;
				StateManager.gameState.world.bullet.add(bul);
			}
		}
		if(fuse==0 & type == 3) {
			int points = 5;
			double angle = Math.random()*90;
			for(int z=0;z<points;z++) {
				Grenade gg = new Grenade(2,(360/points)*z+angle);
				gg.damage = 10;
				gg.type = 2;
				gg.x = x+width/2-gg.width/2;
				gg.y = y+height/2-gg.height/2;
				Boss boss = StateManager.gameState.world.boss;
				if(boss instanceof NightsRow) {
					NightsRow nite = (NightsRow) boss;
					nite.grenade.add(gg);
				}
			}
		}
		if(fuse==0 & type == 4) {
			int points = 5;
			double angle = Math.random()*90;
			for(int z=0;z<points;z++) {
				Grenade gg = new Grenade(2,(360/points)*z+angle);
				gg.type = 3;
				gg.damage = 10;
				gg.x = x+width/2-gg.width/2;
				gg.y = y+height/2-gg.height/2;
				Boss boss = StateManager.gameState.world.boss;
				if(boss instanceof NightsRow) {
					NightsRow nite = (NightsRow) boss;
					nite.grenade.add(gg);
				}
			}
		}
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
		double scale = Main.scale;
		
		if(Main.scaling) {
			BufferedImage image;
			switch(type) {
				case 2:image = Assets.assets[3];break;
				case 3:image = Assets.assets[4];break;
				default:image = Assets.assets[2];
			}
			if(fuse%10==0)
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
			g.drawImage(image,(x+camX)*scale, (y+camY)*scale, width*scale, height*scale);
			
			//Explosion
			g.scalable = true;
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
			g.scalable = false;
			
		}else {
			//Draw Shadow
			double temp = Math.abs(heightFromGround/2.0);
			if(temp>width) temp = width;
			int t = (int)Math.abs(((temp/height)*255)-255);
			g.setColor(new Color(50,50,50,t)); 
			g.fillOval(x+StateManager.gameState.world.camX+temp/2 + (Math.pow(heightFromGround,1.1)/2.0)*Math.cos(Math.toDegrees(World.timeOfDay)), y+StateManager.gameState.world.camY+heightFromGround+temp/2, width-temp, height-temp);
			
			BufferedImage image;
			switch(type) {
				case 2:image = Assets.assets[3];break;
				case 3:image = Assets.assets[4];break;
				default:image = Assets.assets[2];
			}
			if(fuse%10==0)
				switch(type) {
					case 2:image = Assets.assets[10];break;
					case 3:image = Assets.assets[11];break;
					default:image = Assets.assets[9];
				}
			g.drawImage(image,x+camX, y+camY, width, height);
			
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
}
