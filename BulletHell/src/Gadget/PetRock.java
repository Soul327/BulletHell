package Gadget;

import java.awt.Color;
import java.util.ArrayList;

import Main.StateManager;
import Misc.Graphics;

public class PetRock extends Gadget{
	
	int sel = 0,timmer = 0;
	ArrayList<String> speech = new ArrayList<String>();
	
	public PetRock() {
		name = "Pet Rock";
		decription = "A cold round rock that berates you on interaction";
		
		speech.add("Fuck you");
		speech.add("Don't miss");
		speech.add("Just win already");
		speech.add("My mother could take more damage");
		speech.add("Death is a great way to learn!");
	}
	public void activate() {
		sel = (int)(Math.random()*speech.size());
		timmer = 60;
	}
	public void render(Graphics g) {
		if(Math.random()*500<1) {
			sel = (int)(Math.random()*speech.size());
			timmer = 60;
		}
		if(timmer>0) timmer--;
		if(timmer<=0) return;
		g.setColor(Color.white);
		g.drawString(speech.get(sel), 
				StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2+StateManager.gameState.world.camX - g.getFontMetrics().stringWidth(speech.get(sel))/2,
				StateManager.gameState.world.player.y+StateManager.gameState.world.camY-20);
	}
}
