package GameState;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import Gadget.Gadget;
import Gadget.Teleporter;
import Gadget.TraceTeleporter;
import Gun.Gun;
import Gun.SMG;
import Main.Main;
import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;
import Misc.KeyManager;
import Misc.Mat;
import Misc.MouseManager;

public class Player {
	public static boolean invincibility=false;
	int heightFromGround = 20;
	public double health = 100, maxHealth = 100,x=0,y=0,width=30,height=30,speed = 4*(60.0/Main.maxFPS);
	public Gadget gadget = new TraceTeleporter();
	public Gun gun = new SMG();
	
	public Player() {
		x = 800 - width/2;
		y = 600 + height/2;
	}
	public double getLocX() {
		return Main.width/2-(x*Main.scale)-(width*Main.scale)/2;
	}
	public double getLocY() {
		return Main.height/2-(y*Main.scale)-(height*Main.scale)/2;
	}
	public void tick() {
		if(KeyManager.keys[KeyEvent.VK_W]) y-=speed;
		if(KeyManager.keys[KeyEvent.VK_S]) y+=speed;
		if(KeyManager.keys[KeyEvent.VK_A]) x-=speed;
		if(KeyManager.keys[KeyEvent.VK_D]) x+=speed;
		if(KeyManager.keys[KeyEvent.VK_R] & gun.mag!=gun.magSize & gun.reloadTimmer==0) gun.reloadTimmer=Main.maxFPS*gun.reloadSpeed;
		if(KeyManager.keys[KeyEvent.VK_SPACE]) {//Roll
			
		}
		if(MouseManager.rightPressed) { gadget.use(); }
		if(MouseManager.leftPressed) { gun.fire(); }
		gadget.tick();
		gun.tick();
		if(health <= 0 & !invincibility) {
			//System.out.println(StateManager.gameState.world.boss.health);
			StateManager.gameState = new GameState();
			StateManager.state = 0;
			StateManager.menuState.init();
		}
		if(x+width>StateManager.gameState.world.width) x=StateManager.gameState.world.width-width;
		if(x<0) x=0;
		if(y+height>StateManager.gameState.world.height) y=StateManager.gameState.world.height-height;
		if(y<0) y=0;
	}
	public void render(Graphics g) {
		g.scalable = false;
		if(Main.scaling) {
			double camX = StateManager.gameState.world.camX;
			double camY = StateManager.gameState.world.camY;
			
			int incSize = 25;
			g.drawImage(Assets.assets[25], (x+camX-incSize/2)*Main.scale, (y+camY-incSize)*Main.scale, (width+incSize)*Main.scale, (height+incSize)*Main.scale);
			
			g.setColor(Color.white);
			if(Main.devMode>0)
				g.drawRect((x+camX)*Main.scale, (y+camY)*Main.scale, width*Main.scale, height*Main.scale);
			gadget.render(g);
		}else {
			double angle = Math.toDegrees(
					Mat.getAngle(
							StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2,
							StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2,
							MouseManager.mouseX-StateManager.gameState.world.camX,
							MouseManager.mouseY-StateManager.gameState.world.camY));
			int incSize = 25;
			g.drawImage(Assets.assets[25], x+StateManager.gameState.world.camX-incSize/2, y+StateManager.gameState.world.camY-incSize, width+incSize, height+incSize);
			/*
			int incSize = 100;
			g.drawRotatedImage(
					Assets.assets[24], 
					x+StateManager.gameState.world.camX-incSize/2, 
					y+StateManager.gameState.world.camY-incSize/2, 
					width+incSize, 
					height+incSize, 
					angle);
			*/
			if(Main.devMode>0)
				g.drawRect(x+StateManager.gameState.world.camX,y+StateManager.gameState.world.camY, width, height);
			gadget.render(g);
		}
	}
	public boolean contains(double x, double y) {
		if( this.x+this.width>x & 
				this.x<x+width & 
				this.y+this.height>y & 
				this.y<y+height) {
			return true;
		}else
			return false;
	}
}
