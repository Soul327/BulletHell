package Main;

import java.awt.event.KeyEvent;

import GameState.Dialogue;
import Misc.Graphics;
import Misc.KeyManager;
import Misc.SoundManager;

public class TestState {
	
	Life life;
	
	public TestState() {
		life = new Life();
	}
	public void tick() {
		life.tick();
		if(KeyManager.keyRelease(KeyEvent.VK_SPACE)) 
			life.generationTick();
	}
	public void tiedTick() {
		
	}
	public void render(Graphics g) {
		life.render(g);
	}
}
