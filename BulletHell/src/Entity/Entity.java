package Entity;

import Misc.Graphics;

public abstract class Entity {
	
	public boolean remove = false;
	double x,y,width,height;
	
	public void tick() {}
	public void tiedTick() {}
	public void render(Graphics g) {}
	public void debugRender(Graphics g) {}
}
