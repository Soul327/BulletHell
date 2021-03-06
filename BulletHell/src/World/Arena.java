package World;

import java.awt.Color;
import java.util.ArrayList;

import Boss.Boss;
import Boss.NightsRow;
import Boss.Spy;
import Boss.Warrior;
import Entity.Coin;
import Entity.Entity;
import Entity.Floof;
import GameState.Bullet;
import GameState.Player;
import Main.Main;
import Misc.Graphics;
import Particle.Particle;

public class Arena extends World{
	
	public Crowd crowd;
	
	public Arena() {
		player = new Player();
		boss = new Warrior();
		timeOfDay = Math.random()*360;
		crowd = new Crowd();
		entities.add(new Coin());
		for(int z=0;z<100;z++)
			entities.add(new Floof());
		camX=70; camY=10;
	}
	public void tick() {
		crowd.tick();
		player.tick();
		for(Entity e:entities) e.tick();
	}
	public void tiedTick(){
		//Main.baseWidth = 1600;
		Main.baseWidth = 1750;
		player.tiedTick();
		boss.tick();
		crowd.tiedTick();
		for(int t=0;t<bullet.size();t++) {
			bullet.get(t).tick();
			if(bullet.get(t).remove) {
				bullet.remove(t);
				t--;
			}
		}
		for(int t=0;t<entities.size();t++) {
			entities.get(t).tiedTick();
			if(entities.get(t).remove) {
				entities.remove(t);
				t--;
			}
		}
		
	}
	public void render(Graphics g){
		if(Main.scaling) {
			//camX = -player.x-player.width/2+Main.baseWidth/2;
			//camY = -player.y-player.height/2+Main.baseHeight/2;
			//if(bootup>0) { camX=70; camY=10; }
			double regen = 1,maxBounce = 10;
			if(camBounceX<regen & camBounceX>-regen) camBounceX = 0;
			if(camBounceY<regen & camBounceY>-regen) camBounceY = 0;
			if(camBounceX>0) camBounceX-=regen;
			if(camBounceX<0) camBounceX+=regen;
			if(camBounceY>0) camBounceY-=regen;
			if(camBounceY<0) camBounceY+=regen;
			if(camBounceX>maxBounce) camBounceX = maxBounce;
			if(camBounceY>maxBounce) camBounceY = maxBounce;
			if(camBounceX<-maxBounce) camBounceX = -maxBounce;
			if(camBounceY<-maxBounce) camBounceY = -maxBounce;
			camX = 70 + camBounceX; camY = 10 + camBounceY;
			
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
			if(Main.devMode>0)
				for(Entity e:entities) e.debugRender(g);
			crowd.render(g);
		}
	}
}
