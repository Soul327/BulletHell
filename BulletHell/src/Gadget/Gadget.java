package Gadget;

import Misc.Graphics;

public abstract class Gadget {
	
	public int coolDown = 0, defaultCoolDown = 60;
	public String name = "", decription = "";
	
	public void tick() {
		if(coolDown>0)
			coolDown--;
	}
	public void use() {
		if(coolDown==0) {
			coolDown = defaultCoolDown;
			activate();
		}
	}
	public void activate() {}
	public void render(Graphics g) {}
}