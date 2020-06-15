package World;

import java.awt.event.KeyEvent;

import Entity.Coin;
import Entity.Heart;
import Main.StateManager;
import Misc.Graphics;
import Misc.KeyManager;
import Misc.PlaySound;

public class Crowd {
	public static boolean coinMode = false;
	public int limit = 10;
	public double rating = 0,bonus = 0;
	
	public Crowd() {
	}
	public void tiedTick() {}
	public void tick() {
		bonus = 0;
		bonus += StateManager.gameState.world.player.gun.ratingBonus;
		if(rating> limit) rating =  limit;
		if(rating<-limit) rating = -limit;
		if(Math.random()*1000<rating+bonus) StateManager.gameState.world.entities.add(new Coin());
		if(Math.random()*1000<rating+bonus) StateManager.gameState.world.entities.add(new Heart());
		if(coinMode)
			for(int z=0;z<100;z++)
				StateManager.gameState.world.entities.add(new Coin());
				
	}
	public void render(Graphics g) {
		//g.drawString("Rating:"+rating+bonus, 100, 100);
	}
}