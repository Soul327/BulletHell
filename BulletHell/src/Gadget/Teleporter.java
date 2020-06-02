package Gadget;

import GameState.World;
import Main.StateManager;
import Misc.MouseManager;

public class Teleporter extends Gadget{
	public Teleporter() {
		name = "Teleporter";
		decription = "On the press of a button this item will relocate you to the location selected";
		defaultCoolDown = Main.Main.maxFPS*2;
	}
	public void activate() {
		StateManager.gameState.world.player.x = MouseManager.mouseX-StateManager.gameState.world.player.width/2-StateManager.gameState.world.camX;
		StateManager.gameState.world.player.y = MouseManager.mouseY-StateManager.gameState.world.player.height/2-StateManager.gameState.world.camY;
	}
}
