package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

import Misc.Button;

public class MenuState {
	
	String title = "";
	String titleStored = "Welcome to the GAME";
	
	int time = 0;
	int time2 = 0;
	ArrayList<Button> buttons = new ArrayList<Button>();
	public MenuState() {
		int width = 700, height = 50, y=350;
		buttons.add(new Button(new Polygon(new int[]{Main.width/2+width/2,Main.width/2+width/2,Main.width/2-width/2,Main.width/2-width/2}, new int[]{y,y+height,y+height,y}, 4),"Play"));
		width = 700; height = 50; y=420;
		buttons.add(new Button(new Polygon(new int[]{Main.width/2+width/2,Main.width/2+width/2,Main.width/2-width/2,Main.width/2-width/2}, new int[]{y,y+height,y+height,y}, 4),"Test"));
		width = 700; height = 50; y=490;
		buttons.add(new Button(new Polygon(new int[]{Main.width/2+width/2,Main.width/2+width/2,Main.width/2-width/2,Main.width/2-width/2}, new int[]{y,y+height,y+height,y}, 4),"Test"));
	}
	public void tick() {
		if(time%5==0) time2++;
		if(time2<=titleStored.length()) title = titleStored.substring(0, time2);
		if(time>Integer.MAX_VALUE/2) time=0;
		time++;
		if(buttons.get(0).leftClick()) StateManager.state = 1;
	}
	public void render(Graphics2D g) {
		g.setColor(Color.white);
		Font font = new Font("Serif",Font.PLAIN,80);
		g.setFont(font);
		g.drawString(title, Main.width/2-g.getFontMetrics().stringWidth(title)/2, 170);
		g.setFont(new Font("Serif",Font.PLAIN,40));
		for(Button b:buttons) b.render(g);
	}
}