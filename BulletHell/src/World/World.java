package World;

import java.awt.Color;
import java.util.ArrayList;

import Boss.Boss;
import Boss.Patches;
import Boss.Spy;
import Entity.Entity;
import GameState.Bullet;
import GameState.Player;
import Misc.Graphics;
import Particle.Particle;
import Main.Main;

public abstract class World {
	public static double timeOfDay = 180;
	public double camX = 0,camY = 0;
	public int width=1600,height=900, bootup = Main.maxFPS*3;
	public Player player;
	public Boss boss;
	public ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	
	public ArrayList<Particle> particles = new ArrayList<Particle>();
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public World() {
		player = new Player();
		boss = new Patches();
		timeOfDay = Math.random()*360;
	}
	public void tick(){}
	public void tiedTick(){
		if(bootup<=0) {
			//Main.baseWidth = 1600;
			Main.baseWidth = 1750;
			player.tiedTick();
			boss.tick();
			for(int t=0;t<bullet.size();t++) {
				bullet.get(t).tick();
				if(bullet.get(t).remove) {
					bullet.remove(t);
					t--;
				}
			}
			for(Entity e:entities)
				e.tiedTick();
		}else {
			Main.baseWidth = 1750;
			bootup--;
			boss.bootup(bootup);
		}
	}
	public void render(Graphics g){
		if(Main.scaling) {
			camX = -player.x-player.width/2+Main.baseWidth/2;
			camY = -player.y-player.height/2+Main.baseHeight/2;
			//if(bootup>0) { camX=70; camY=10; }
			camX=70; camY=10;
			g.setColor(Color.gray.darker());
			g.fillRect(camX*Main.scale, camY*Main.scale, width*Main.scale, height*Main.scale);
			
			boss.render(g);
			player.render(g);
			for(Bullet b:bullet) b.render(g);
			
			for(int t=0;t<particles.size();t++) {
				particles.get(t).render(g);
				if(particles.get(t).remove) {
					particles.remove(t);
					t--;
				}
			}
			for(Entity e:entities) e.render(g);
		}else {
			g.setColor(Color.gray.darker());
			g.fillRect(camX, camY, width, height);
			camX = Main.width/2-player.x-player.width/2; camY = Main.height/2-player.y-player.height/2;
			
			g.setColor(Color.white); g.drawLine(camX, 0, camX, Main.height);
			g.setColor(Color.white); g.drawLine(0, camY, Main.width, camY);
			
			boss.render(g);
			player.render(g);
			for(Bullet b:bullet) b.render(g);
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
