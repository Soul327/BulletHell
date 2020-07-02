package Main;

import GameState.Dialogue;
import Misc.Graphics;
import Misc.SoundManager;

public class TestState {
	Dialogue dialogue = new Dialogue();
	
	public TestState() {}
	public void tick() {}
	public void tiedTick() {}
	public void render(Graphics g) {
		dialogue.render(g);
	}
}
