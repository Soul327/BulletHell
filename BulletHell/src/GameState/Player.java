package GameState;

import java.awt.Color;
import java.awt.event.KeyEvent;

import Gadget.Gadget;
import Gadget.TraceTeleporter;
import Gun.Gun;
import Gun.MyFirstGun;
import Gun.SMG;
import Main.Main;
import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;
import Misc.KeyManager;
import Misc.MouseManager;
import World.Town;

public class Player {
	public static boolean invincibility=false;
	int heightFromGround = 20;
	public int money = 0,iFrames = 0, maxiFrames = 20;
	/* shield[0] = Max Cool down
	 * shield[1] = Cool down 
	 * shield[2] = Max Shield Time
	 * shield[3] = Shield Time
	 */
	short shield[] = new short[4];
	public double health = 300, maxHealth = 300,x=0,y=0,width=30,height=30,speed = 4*(60.0/Main.maxFPS);
	public Gadget gadget = new TraceTeleporter();
	public Gun gun = new SMG();
	
	public Player() {
		x = 800 - width/2;
		y = 600 + height/2;
		shield[0] = 120;//Max Cool Down
		shield[2] = 60;//Max Shield Time
	}
	public void tick() {
		if(shield[1]>0) shield[1]--;
		if(shield[3]>0) shield[3]--;
		if(iFrames>0) iFrames --;
	}
	public void tiedTick() {
		if(KeyManager.keys[KeyEvent.VK_W]) y-=speed;
		if(KeyManager.keys[KeyEvent.VK_S]) y+=speed;
		if(KeyManager.keys[KeyEvent.VK_A]) x-=speed;
		if(KeyManager.keys[KeyEvent.VK_D]) x+=speed;
		if(! (StateManager.gameState.world instanceof Town) ) {
			if(KeyManager.keys[KeyEvent.VK_R] & gun.mag!=gun.magSize & gun.reloadTimmer==0) gun.reloadTimmer=Main.maxFPS*gun.reloadSpeed;
			if(KeyManager.keys[KeyEvent.VK_SPACE]) {//Roll
				if(shield[1]<=0) {
					shield[1] = shield[0];
					shield[3] = shield[2];
				}
			}
			if(MouseManager.rightPressed) { gadget.use(); }
			if(MouseManager.leftPressed) {
				gun.fire();
				gun.triggerHeld = true;
			} else {
				gun.triggerHeld = false;
			}
			gadget.tick();
			gun.tick();
			if(health <= 0 & !invincibility) {
				//System.out.println(StateManager.gameState.world.boss.health);
				StateManager.gameState = new GameState();
				StateManager.state = 0;
				StateManager.menuState.init();
			}
		}
		if(x+width>StateManager.gameState.world.width) x=StateManager.gameState.world.width-width;
		if(x<0) x=0;
		if(y+height>StateManager.gameState.world.height) y=StateManager.gameState.world.height-height;
		if(y<0) y=0;
	}
	public void damage(double dam) {
		if(shield[3]>0 | iFrames>0) return;
		health -= dam;
		if(health<0) health = 0;
		if(health>maxHealth) health = maxHealth;
		iFrames = maxiFrames;
	}
	public void render(Graphics g) {
		g.scalable = true;
		double camX = StateManager.gameState.world.camX;
		double camY = StateManager.gameState.world.camY;
		
		int incSize = 25;
		g.drawImage(Assets.assets[25], (x+camX-incSize/2), (y+camY-incSize), (width+incSize), (height+incSize));
		g.setColor(Color.white);
		g.fillCenterCircle(x+camX+width/2, y+camY+height/2, 5);
		
		if( (StateManager.gameState.world instanceof Town) ) return;
		g.setColor(Color.blue);
		if(shield[3]>0) g.drawOval((x+camX), (y+camY), width, height);
		if(shield[3]>0) g.fillOval((x+camX), (y+camY), width, height);
		
		g.setColor(Color.white);
		if(Main.devMode>0)
			g.drawRect((x+camX), (y+camY), width, height);
		gadget.render(g);
		g.scalable = false;
		
		g.setColor(Color.red);
		for(int z=0;z<iFrames*2;z+=2)
			g.drawRect(z, z, Main.width-z*2, Main.height-z*2);
	}
	public boolean contains(double x, double y) {
		if( this.x+this.width>x & 
				this.x<x & 
				this.y+this.height>y & 
				this.y<y) {
			return true;
		}else
			return false;
	}
	public boolean contains(double x, double y, double width, double height) {
		//*
		if( this.x+this.width>x & 
				this.x<x+width & 
				this.y+this.height>y & 
				this.y<y+height) {
			return true;
		}else
			return false;
		//*/
	}
}
