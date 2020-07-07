package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import GameState.DialogueState;
import GameState.GameState;
import Menu.Loading;
import Menu.MenuState;
import Menu.Settings;
import Misc.Graphics;

public class StateManager {
	
	public static int state = 2,overlayState = -1;
	public static MenuState menuState;
	public static GameState gameState;
	public static Loading loading;
	public static Settings settings;
	public static DialogueState dialogueState;
	
	public StateManager() {
		loading = new Loading();
	}
	
	public void init() {
		menuState = new MenuState();
		gameState = new GameState();
		settings = new Settings();
		dialogueState = new DialogueState();
	}
	
	int tickTime = 0;
	
	public void tick() {
		tiedTick();
		if(tickTime==0) tick2();
		tickTime++;
		if(tickTime>Main.maxFPS/60.0) tickTime=0; //Runs at 60 FPS
	}
	public void tick2(){//Locked at 60
		switch(state) {
			case 1:gameState.tick();break;
		}
	}
	public void tiedTick() {//Tied to framerate
		if(overlayState==-1)
			switch(state) {
				case 0:menuState.tick();break;
				case 1:gameState.tiedTick(); break;
				case 2:
					if(!Main.load.isAlive()) {
						state = 0;//Default state after game loads
						state = 1;//Default state after game loads
						init();
					}
					loading.tiedTick();
					break;
			}
		switch(overlayState) {
			case 3:settings.tiedTick();break;
			case 4:dialogueState.tiedTick();break;
		}
	}
	public void render(Graphics2D g2d) {
		Graphics g = new Graphics(g2d);
		switch(state) {
			case 0:menuState.render(g);break;
			case 1:gameState.render(g);break;
			case 2:loading.render(g);break;
			case 3:settings.render(g);break;
		}
		//*
		switch(overlayState) {
			case 3:settings.render(g);break;
			case 4:dialogueState.render(g);break;
		}
		//*/
	}
}