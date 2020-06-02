package GameState;

import java.awt.Color;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Boss.Boss;
import Boss.NightsRow;
import Boss.Robbin;
import Boss.Spy;
import Misc.Graphics;
import Misc.KeyManager;
import Misc.Mat;
import Particle.Particle;
import Particle.Smoke;
import Main.Main;
import Main.StateManager;

public class World {
	public static double timeOfDay = 180;
	public double camX = 0,camY = 0;
	public int width=1600,height=900;
	public Player player;
	public Boss boss;
	public ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	public ArrayList<Lazer> lazer = new ArrayList<Lazer>();
	public ArrayList<Grenade> grenade = new ArrayList<Grenade>();
	public ArrayList<Particle> particles = new ArrayList<Particle>();
	
	public World() {
		player = new Player();
		boss = new NightsRow();
		timeOfDay = Math.random()*360;
	}
	public void tick(){
		player.tick();
		boss.tick();
		for(int t=0;t<bullet.size();t++) {
			bullet.get(t).tick();
			if(bullet.get(t).remove) {
				bullet.remove(t);
				t--;
			}
		}
		for(int t=0;t<grenade.size();t++) {
			grenade.get(t).tick();
			if(grenade.get(t).remove) {
				grenade.remove(t);
				t--;
			}
		}
		for(int t=0;t<lazer.size();t++) {
			lazer.get(t).tick();
			if(lazer.get(t).remove) {
				lazer.remove(t);
				t--;
			}
		}
	}
	public void render(Graphics g){
		if(Main.scaling) {
			camX = 0; camY = 0;
			
			width=1600; height=900;
			
			g.setColor(Color.gray.darker());
			g.fillRect(camX*Main.scale, camY*Main.scale, width*Main.scale, height*Main.scale);
			
			player.render(g);
		}else {
			g.setColor(Color.gray.darker());
			g.fillRect(camX, camY, width, height);
			camX = player.getLocX(); camY = player.getLocY();
			camX = Main.width/2-player.x-player.width/2; camY = Main.height/2-player.y-player.height/2;
			
			
			g.setColor(Color.white); g.drawLine(camX, 0, camX, Main.height);
			g.setColor(Color.white); g.drawLine(0, camY, Main.width, camY);
			
			boss.render(g);
			player.render(g);
			for(Bullet b:bullet) b.render(g);
			for(Grenade gg:grenade) gg.render(g);
			for(Lazer l:lazer) l.render(g);
			//Draw line pointing to boss
			//g.setColor(Color.white); g.drawLine(boss.x+camX+boss.width/2, boss.y+camY+boss.height/2, player.x+camX+player.width/2, player.y+camY+player.height/2);
			for(int t=0;t<particles.size();t++) {
				particles.get(t).render(g);
				if(particles.get(t).remove) {
					particles.remove(t);
					t--;
				}
			}
		}
	}
}
