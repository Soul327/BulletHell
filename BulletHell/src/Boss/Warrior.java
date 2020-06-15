package Boss;

import java.awt.Color;

import Main.StateManager;
import Misc.Graphics;
import Misc.Mat;
import Misc.PlaySound;
import Misc.SoundManager;

public class Warrior extends Boss{
	
	public Warrior() {
		name = "Warrior";
		des = "The purple spirit of the Bonham";
		health = 18000;
		maxHealth = health;
		form = (int)(Math.random()*2 + 1);
		switch(form) {
			case 1://The spirit of the game
				title = "The spirit of the game";
				break;
			case 2://The Worshiper of Dickson
				title = "Worshiper of Dickson";
				break;
		}
	}
	
	public void tick2() {
		if(inAct) {
			switch(act) {
				case 0: automobile(); break;
				//*/
				default:inAct = false;
			}
			actTick++;
		}else {
			temp = new double[10];
			act = (int)(Math.random()*10);
			//act = 6;
			actTick = 0;
			inAct = true;
		}
	}
	public void automobile() {
		switch(actTick) {
		//Intro
		case   0: new SoundManager("res/Automobile.wav").start(); break;
		
		//case  50: star(10, 0); break;
		case 150: x = 100; break;
		case 230: x = 900; break;
		case 300: x = 100; break;
		case 375: x = 900; break;
		case 395: x = 100; break;
		case 420: x = 900; break;
		case 430: x = 100; break;
		
		case 560: star(100,0); break;
		case 565: star(100,0); break;
		case 570: star(100,0); break;
		case 900:inAct = false;
	}
}
	
	public void automobileSync() {
		switch(actTick) {
			//Intro
			case   0: new PlaySound("res/Automobile.wav").start(); break;
			//*
			case   1: star(100, 0); break;
			case  20: star(10,45); break;
			case  30: star(100,0); break;
			case  40: star(100,0); break;
			case  55: star(100,0); break;
			//*/
			case  60: star(10,45); break;
			//case  70: star(100,0); break;
			//case  80: star(100,0); break;
			//case  90: star(10,45); break;
			//Snare
			case  151: star(10,45); break;
			case  165: star(10,45); break;
			case  170: star(10,45); break;
			
			//case  50: star(10, 0); break;
			case 150: x = 100; break;
			case 230: x = 900; break;
			case 300: x = 100; break;
			case 375: x = 900; break;
			case 395: x = 100; break;
			case 420: x = 900; break;
			case 430: x = 100; break;
			
			case 560: star(100,0); break;
			case 565: star(100,0); break;
			case 570: star(100,0); break;
			case 900:inAct = false;
		}
	}
	
	public void render(Graphics g) {
		g.scalable = true;
		g.setColor(new Color(50,30,100));
		g.fillRect(
			(x+StateManager.gameState.world.camX),
			(y+StateManager.gameState.world.camY),
			width,
			height);
		g.drawString(""+actTick, 100, 100);
		g.scalable = false;
	}
}

/* Example boss format
 * 
 * public attack1(){}
 * public attack2(){}
 * public attack3(){}
 */
