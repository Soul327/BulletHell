package Boss;

import java.awt.Color;

import GameState.Bullet;
import GameState.Player;
import GameState.World;
import Main.StateManager;
import Misc.Graphics;
import Misc.MouseManager;

public abstract class Boss {
	
	public String name,des;
	boolean dead = false;
	int heightFromGround = 10;
	public double x=0,y=0,width=100,height=100;
	public double health = 2469,maxHealth = 2469;
	
	public Boss() {
		x = 650;
		y = 400;
		width = 100;
		height = 100;
	}
	int tickTime = 0;
	public void tick() {
		if(dead) return;
		if(health<=0) {
			health = 0;
			dead = true;
		}
		if(tickTime==0) {
			tick2();
			if(health<=maxHealth/2) tick2();
		}
		tickTime++;
		if(tickTime>Main.Main.maxFPS/60.0) {//Runs at 60 FPS
			tickTime=0;
		}
		tiedTick();
	}
	boolean inAct = false;
	int act = 0, actTick = 0;
	double temp[] = new double[10];
	public void bootup(int tick) {}
	public void tick2() {};
	public void tiedTick() {};
	public void render(Graphics g) {
		g.setColor(Color.red); g.fillRect(x+StateManager.gameState.world.camX, y+StateManager.gameState.world.camY, width, height);
		g.setColor(Color.black); g.drawRect(x+StateManager.gameState.world.camX, y+StateManager.gameState.world.camY, width, height);
	}
	public void star(int points, double angle) {
		for(int z=0;z<points;z++) {
			Bullet bul = new Bullet(4,(360/points)*z+angle);
			bul.damage = 10;
			bul.x = x+width/2-bul.width/2;
			bul.y = y+height/2-bul.height/2;
			StateManager.gameState.world.bullet.add(bul);
		}
	}
	public void shootPlayer() {
		double px = StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2, py = StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2;
		double mx = MouseManager.mouseX-px-StateManager.gameState.world.camX,my = -MouseManager.mouseY+py+StateManager.gameState.world.camY;
		double angle = Math.toDegrees(Math.atan2( my,mx));
		Bullet bul = new Bullet(2,angle);
		bul.damage = 10;
		StateManager.gameState.world.bullet.add(bul);
	}
}