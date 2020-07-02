package GameState;

import java.awt.Color;

import Main.Main;
import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;
import World.World;

public class Bullet {
	public boolean playerDam = true,bossDam = false,remove = false;
	public int despawn = Main.maxFPS*20;
	public double x,y,width,height,velX,velY,damage = 1,heightFromGround=5,angle;
	
	public Bullet(double power, double angle) {
		x = 650;
		y = 400;
		width = 10;
		height = 10;
		angle = -angle;
		this.velX = (power*(60.0/Main.maxFPS)) * Math.cos(Math.toRadians(angle));
		this.velY = (power*(60.0/Main.maxFPS)) * Math.sin(Math.toRadians(angle));
		//new PlaySound("res/Sounds/boom_pack/boom4.wav").start();
		this.angle = angle;
	}
	public void tick() {
		despawn--;
		if(despawn<=0) remove=true;
		x+=velX;
		y+=velY;
		//Check player colide
		if(playerDam & StateManager.gameState.world.boss.health!=0) {
			if(StateManager.gameState.world.player.contains(x,y,width,height)){
				remove = true;
				StateManager.gameState.world.player.damage(damage);;
			}
		}
		if(bossDam) {
			if( StateManager.gameState.world.boss.x+StateManager.gameState.world.boss.width>x 
					& StateManager.gameState.world.boss.x<x+width 
					& StateManager.gameState.world.boss.y+StateManager.gameState.world.boss.height>y 
					& StateManager.gameState.world.boss.y<y+height) {
				remove = true;
				if(StateManager.gameState.world.boss.health>0)
					StateManager.gameState.world.boss.health-=damage;
			}
		}
		if(x<0) remove=true;
		if(x+width>StateManager.gameState.world.width) remove=true;
		if(y<0) remove=true;
		if(y+height>StateManager.gameState.world.height) remove=true;
	}
	public void render(Graphics g) {
		double camX = StateManager.gameState.world.camX;
		double camY = StateManager.gameState.world.camY;
		double scale = Main.scale;
		
		//Draw shadow
		double temp = Math.abs(heightFromGround/2.0);
		if(temp>width) temp = width;
		int t = (int)Math.abs(((temp/height)*255)-255);
		g.setColor(new Color(50,50,50,t)); 
		g.fillOval(
				((x+camX+temp/2) + (Math.pow(heightFromGround,1.1)/2.0)*Math.cos(Math.toDegrees(World.timeOfDay)))*scale, 
				(y+camY+heightFromGround+temp/2)*scale, 
				(width-temp)*scale, (height-temp)*scale);
		
		//Draw bullet
		if(bossDam) g.drawImage(Assets.assets[0], (x+StateManager.gameState.world.camX)*Main.scale, (y+StateManager.gameState.world.camY)*Main.scale, width*Main.scale, height*Main.scale);
		if(playerDam) g.drawImage(Assets.assets[1], (x+StateManager.gameState.world.camX)*Main.scale, (y+StateManager.gameState.world.camY)*Main.scale, width*Main.scale, height*Main.scale);
	}
}