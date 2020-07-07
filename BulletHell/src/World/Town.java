package World;

import java.awt.Color;

import Entity.Entity;
import Entity.TestNPC;
import GameState.Bullet;
import GameState.Player;
import Main.Main;
import Misc.Graphics;

public class Town extends World{
	
	public Town() {
		player = new Player();
		Entity e;
		//Gadget shop
		e = new TestNPC();
		e.x = 800; e.y = 600;
		entities.add(e);
		//Weapon shop
		e = new TestNPC();
		e.x = 1400; e.y = 100;
		entities.add(e);
		
		timeOfDay = Math.random()*360;
		camX=70; camY=10;
	}
	public void tick() {
		player.tick();
		for(Entity e:entities) e.tick();
	}
	public void tiedTick(){
		//Main.baseWidth = 1600;
		Main.baseWidth = 1750;
		player.tiedTick();
		for(int t=0;t<entities.size();t++) {
			entities.get(t).tiedTick();
			if(entities.get(t).remove) {
				entities.remove(t);
				t--;
			}
		}
		
	}
	public void render(Graphics g){
		//camX = -player.x-player.width/2+Main.baseWidth/2;
		//camY = -player.y-player.height/2+Main.baseHeight/2;
		//if(bootup>0) { camX=70; camY=10; }
		
		g.setColor(Color.gray.darker());
		g.fillRect(camX*Main.scale, camY*Main.scale, width*Main.scale, height*Main.scale);
		
		for(Bullet b:bullet) b.render(g);
		
		for(int t=0;t<particles.size();t++) {
			particles.get(t).render(g);
			if(particles.get(t).remove) {
				particles.remove(t);
				t--;
			}
		}
		for(Entity e:entities) e.render(g);
		player.render(g);
	}
}
