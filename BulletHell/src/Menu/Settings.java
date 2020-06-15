package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import Main.Main;
import Main.StateManager;
import Misc.Graphics;
import Misc.KeyManager;
import Misc.MouseManager;
import Misc.SoundManager;

public class Settings {
	
	Slider volumeSlider;
	public Settings() {
		volumeSlider = new Slider();
		volumeSlider.range = 1;
	}
	public void tiedTick() {
		volumeSlider.tiedTick();
		SoundManager.volume = (float)volumeSlider.getValue();
		if(KeyManager.keyRelease(KeyEvent.VK_ESCAPE))
			StateManager.overlayState = -1;
	}
	public void render(Graphics g) {
		int line = 0;
		g.setColor(new Color(0,0,0,150));
		g.fillRect(0, 0, Main.width, Main.height);
		
		g.setColor(new Color(255,255,255,255));
		g.setFont(new Font("Serif",Font.PLAIN,40));
		String s = "Settings";
		g.drawString(s, Main.width/2-g.getStringLength(s)/2, line);
		line+=70;
		
		g.setColor(new Color(255,255,255,255));
		g.setFont(new Font("Serif",Font.PLAIN,20));
		s = "Volume";
		g.drawString(s, Main.width/2-g.getStringLength(s)/2, line);
		line+=35;
		
		volumeSlider.x = Main.width/2 - volumeSlider.width/2;
		volumeSlider.y = line;
		volumeSlider.render(g);
	}
	
	
	/* Example
	 * Volume
	 */
}

class Slider {
	public int x = 100, y = 100, width = 1000, height = 20;
	public int range = 100, value = 30;
	
	public double getValue() {
		return (double)value/width*range;
	}
	
	public void tiedTick() {
		if(MouseManager.leftPressed) {
			int mx = MouseManager.mouseX;
			int my = MouseManager.mouseY;
			if(mx>x & mx<x+width & my>y & my<y+height) {
				value = (int)( mx-x );
				if(Math.random()<.05)
					new SoundManager("res/coin_sounds/coin"+ (int)(Math.random()*10+1) +".wav").start();
				//System.out.println(value);
			}
		}
		
	}
	public void render(Graphics g) {
		g.scalable = true;
		g.setColor(new Color(50,50,50,150)); 
			g.fillRect(x+1, y+3, width, height); //Shadow
		
		g.setColor(new Color(136, 20, 20)); 
			g.fillRect(x, y, width, height);
		g.setColor(new Color(0, 255, 20)); 
			g.fillRect(x, y, width*((double)value/width), height);
		g.setColor(Color.white); 
			g.fillRect(x+width*((double)value/width)-7/2.0, y-2, 7, height+4);
	}
}
