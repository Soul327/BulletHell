package Gadget;

import java.awt.Color;
import java.util.ArrayList;

import Main.StateManager;
import Misc.Graphics;

public class PetRock extends Gadget{
	
	int sel = 0,timer = 0;
	ArrayList<String> speech = new ArrayList<String>();
	
	public PetRock() {
		name = "Pet Rock";
		decription = "A \"helpful\" little friend";
		
		speech.add("Fuck you");
		speech.add("Don't miss");
		speech.add("Just win already");
		speech.add("My mother could take more damage");
		speech.add("Death is a great way to learn!");
		speech.add("Bro, do you even game?");
		speech.add("Fired, and ye missed");
		speech.add("POGGERS!");
		speech.add("You fell victim to one of the classic blunders!");
		speech.add("Your mother was a hamster");
		speech.add("Your father smelt of elderberries");
		speech.add("Your moves are weak");
		speech.add("You are proof that our god has a sense of humor");
		speech.add("You have delusion of adequacy");
		speech.add("I like the way you try");
		speech.add("I like your approach now let's see your departure");
		speech.add("Set me down, I can take him!");
		speech.add("Dont get bitter just get better");
		speech.add("I thought you were supposed to be a hero?");
	}
	public void activate() {
		sel = (int)(Math.random()*speech.size());
		timer = 60;
	}
	public void render(Graphics g) {
		if(Math.random()*500<1) {
			sel = (int)(Math.random()*speech.size());
			timer = 60;
		}
		if(timer>0) timer--;
		if(timer<=0) return;
		g.setColor(Color.white);
		g.scalable = true;
		g.drawString(speech.get(sel), 
				StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2+StateManager.gameState.world.camX - g.getFontMetrics().stringWidth(speech.get(sel))/2,
				StateManager.gameState.world.player.y+StateManager.gameState.world.camY-20);
		g.scalable = false;
	}
}
