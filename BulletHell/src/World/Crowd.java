package World;

import java.awt.event.KeyEvent;

import Entity.Coin;
import Entity.Heart;
import Main.StateManager;
import Misc.Graphics;
import Misc.KeyManager;
import Misc.SoundManager;

public class Crowd {
	public static boolean coinMode = false;
	public int limit = 10;
	public double rating = 0,bonus = 0;
	
	public Crowd() {
		if(true) return;
		//https://crowds.ambient-mixer.com/arena-crowd-for-gladiators
		//SoundManager.playLoopingSound("res/Crowd/a/Samba.wav",0f);//1
		SoundManager.playLoopingSound("res/Crowd/a/large crowd cheer.wav",.1f);//2
		//SoundManager.playLoopingSound("res/Crowd/a/Large crowd outdoor screaming.wav",.6f);//3
		SoundManager.playLoopingSound("res/Crowd/a/Attack!.wav",0f);//4
		SoundManager.playLoopingSound("res/Crowd/a/warriors_fighting_swords_shield.wav",0f);//4
		//SoundManager.playLoopingSound("res/Crowd/a/Crowd Cheering.wav",.6f);
		//SoundManager.playLoopingSound("res/Crowd/a/Cracking Fire Whipped Skliter.wav",6f);
		//SoundManager.playLoopingSound("res/Crowd/a/Huge Crowd.wav",6f);
		
		SoundManager.playSound("res/Automobile.wav",1f);
	}
	public void playerHealthChange(double change) {
		if(rating+bonus>0) {
			long delay = (long)(Math.random()*100);
			if(change<0) {
				switch((int)(Math.random()*3)) {
					case 0: SoundManager.playDelayedSound("res/Crowd/crowdboo.wav",delay); break;
					case 1: SoundManager.playDelayedSound("res/Crowd/groans.wav",delay); break;
					case 2: SoundManager.playDelayedSound("res/Crowd/boooo.wav",delay); break;
				}
			}
			if(change>0) {}
		}
		if(rating+bonus>0) {}
	}
	public void tiedTick() {}
	public void tick() {
		bonus = 0;
		bonus += StateManager.gameState.world.player.gun.ratingBonus;
		if(rating> limit) rating =  limit;
		if(rating<-limit) rating = -limit;
		if(Math.random()*1000<rating+bonus) StateManager.gameState.world.entities.add(new Coin());
		if(Math.random()*1000<rating+bonus) StateManager.gameState.world.entities.add(new Heart());
		if(KeyManager.keyRelease(KeyEvent.VK_O)) playerHealthChange(-1);
		if(KeyManager.keyRelease(KeyEvent.VK_P)) playerHealthChange(1);
		if(KeyManager.keyRelease(KeyEvent.VK_L)) {
			for(int x=0;x<10;x++)
				SoundManager.playDelayedSound("res/Crowd/applause1.wav",x*100);
		}
		if(coinMode)
			for(int z=0;z<100;z++)
				StateManager.gameState.world.entities.add(new Coin());
	}
	public void render(Graphics g) {
		//g.drawString("Rating:"+rating+bonus, 100, 100);
	}
}