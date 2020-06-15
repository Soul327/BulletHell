package GameState;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Main.Main;
import Misc.Graphics;
import Misc.MouseManager;

public class Dialog {
	
	int depth = 0;
	String last = "";
	File file;
	
	public Dialog() {
		file = new File("res/NPC_chat/testy.txt");
	}
	public String getMessage(int z) {
		ArrayList<String> re = get(z);
		if(re.size()>0)
			return get(z).get(0);
		return null;
	}
	public ArrayList<String> get(int z){
		try {
			ArrayList<String> re = new ArrayList<String>();
			Scanner scan = new Scanner(file);
			
			while(scan.hasNextLine()) {
				String s = scan.nextLine();
				boolean add = true;
				for(int x=0;x<z;x++) 
					if(s.charAt(x)!=' ')
						add = false;
				if(s.charAt(z)==' ')
					add = false;
				if(add) re.add(s.substring(z));
			}
			
			scan.close();
			return re;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void tick() {}
	public void tiedTick() {}
	
	int delay = 0;
	public void render(Graphics g) {
		g.scalable = false;
		if(delay>0) delay--;
		int headshotWidth = 300, headshotHeight = 300, border = 10;
		int messageWidth = 1000, messageHeight = 200;
		g.setColor(new Color( 28, 30, 31)); g.fillRect(border, Main.height-headshotHeight*Main.scale-border, headshotWidth*Main.scale, headshotHeight*Main.scale);
		g.setColor(new Color(200,200,200)); g.drawRect(border, Main.height-headshotHeight*Main.scale-border, headshotWidth*Main.scale, headshotHeight*Main.scale);
		
		g.setColor(new Color( 28, 30, 31)); g.fillRect(Main.width-border-messageWidth*Main.scale, Main.height-messageHeight*Main.scale-border, messageWidth*Main.scale, messageHeight*Main.scale);
		g.setColor(new Color(200,200,200)); g.drawRect(Main.width-border-messageWidth*Main.scale, Main.height-messageHeight*Main.scale-border, messageWidth*Main.scale, messageHeight*Main.scale);
		
		g.setColor(Color.white);
		g.setFont(new Font("Serif",Font.PLAIN,(int)(20*Main.scale)));
		g.drawString("Jaf, the test",border+5, Main.height-headshotHeight*Main.scale-border); //Name
		String s = getMessage(depth);
		g.drawString(s,Main.width-border-messageWidth*Main.scale+5, Main.height-messageHeight*Main.scale-border); //Message
		
		//prints out all optins
		ArrayList<String> re = get(depth);
		for(int z = 1;z<re.size();z++) {
			s = re.get(z);
			double x = Main.width-border-messageWidth*Main.scale+5;
			double y = Main.height-(messageHeight)*Main.scale-border+(25*Main.scale*(z+.4));
			double width = messageWidth*Main.scale-border*2;
			double height = 20*Main.scale;
			double mx = MouseManager.mouseX;
			double my = MouseManager.mouseY;
			if(mx>x & mx<x+width & my>y & my<y+height) {
				g.setColor(Color.red);
				if(MouseManager.leftPressed) {
					if(delay==0) {
						depth+=1;
						delay=Main.maxFPS/2;
						last = re.get(z);
					}
				}
			} else
				g.setColor(Color.white);
			g.fillCenterCircle(x, y, 2);
			g.fillCenterCircle(mx, my, 2);
			g.drawRect( x, y, width, height);
			
			g.drawString(s,Main.width-border-messageWidth*Main.scale+10, Main.height-(messageHeight)*Main.scale-border+(25*Main.scale*z)); //Message
		}
	}
}
