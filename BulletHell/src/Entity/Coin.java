package Entity;

import java.awt.Color;

import GameState.Player;
import Main.Main;
import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;
import Misc.Mat;
import Misc.PlaySound;

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
		Player player = StateManager.gameState.world.player;
		dis = Mat.distance(player.x+player.width/2, player.y+player.height/2, x+width/2, y+height/2);
		//System.out.println(dis);
		if( dis <= 75 ) {
			remove = true;
			new PlaySound("res/coin_sounds/coin"+ (int)(Math.random()*10+1) +".wav").start();
			StateManager.gameState.world.entities.add(new Coin());
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
		
		g.scalable = true;
		g.drawImage(Assets.ani[1][aniFrame], x+1, y+5, width, height);
		g.drawImage(Assets.ani[0][aniFrame], x, y, width, height);
		g.drawString("DIS:"+dis, 100, 200);
		if(Main.devMode!=0) g.drawRect(x, y, width, height);
		g.scalable = false;
		g.drawCenterCircle(x+width/2, y+height/2, 75);
		g.fillCenterCircle(x+width/2, y+height/2, 5);
		g.fillCenterCircle(
				StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2, 
				StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2, 5);
	}
}
