package Entity;

import java.awt.Color;

import javax.sound.sampled.FloatControl;

import GameState.Player;
import Main.Main;
import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;
import Misc.Mat;
import Misc.SoundManager;

public class Coin extends Entity{
	
	public Coin() {
		width = height = 25;
		x = Math.random()*(1600-width);
		y = Math.random()*(900-height);
	}
	
	boolean flip = true;
	byte aniFrame = 0;
	byte tickTime = 0;
	double dis = 0;
	
	public void tiedTick() {
		if( playerDistance() <= 40 ) {
			remove = true;
			//new PlaySound("res/coin_sounds/coin"+ (int)(Math.random()*10+1) +".wav").start();
			SoundManager.playSound("res/coin_sounds/coin"+ (int)(Math.random()*10+1) +".wav",1f);
			StateManager.gameState.world.player.money++;
			//StateManager.gameState.world.entities.add(new Coin());
		}
	}
	
	public void render(Graphics g) {
		tickTime++;
		if(tickTime>Main.fps/15.0) {
			if(flip) aniFrame++;
			else aniFrame--;
			if(aniFrame<=0 | aniFrame>=3) flip = !flip;
			tickTime=0;
		}
		
		double camX = StateManager.gameState.world.camX;
		double camY = StateManager.gameState.world.camY;
		g.scalable = true;
		g.drawImage(Assets.ani[1][aniFrame], x+1+camX, y+5+camY, width, height);
		g.drawImage(Assets.ani[0][aniFrame], x+camX, y+camY, width, height);
		//g.drawString("DIS:"+dis, 100, 200);
		//if(Main.devMode!=0) g.drawRect(x, y, width, height);
		g.scalable = false;
	}
}
