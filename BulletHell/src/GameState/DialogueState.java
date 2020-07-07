package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Main.Main;
import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;
import Misc.KeyManager;
import Misc.MouseManager;

public class DialogueState {
	
	File file;
	ArrayList<String> dio = new ArrayList<String>();
	int level = 0,mouseDisable = 0;
	String name = "";
	
	public DialogueState() {
		loadChar("testy",0,null);
		//loadChar("testy",1,"I don't see this as very fancy");
	}
	
	public void loadChar(String name,int level, String last) {
		file = new File("res/NPC_chat/"+name+".txt");
		dio = getDio(level,last);
		for(String s:dio) System.out.println(s);
		this.name = name;
		this.level = level;
	}
	public ArrayList<String> getDio(int level, String last) {
		boolean add = false;
		Scanner scanner;
		ArrayList<String> re = new ArrayList<String>();
		
		if(last!=null) {
			String temp = "";
			for(int x = level-1;x>0;x--)
				temp += " ";
			last = temp += last;
		}
		
		try {
			//Create spaces
			String space = "";
			for(int x=0;x<level;x++)
				space += " ";
			
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(add==true & last!=null) {
					if(line.charAt(level-1)!=' ')
						add = false;
				}
				
				if(last!=null) if(line.equals(last)) add = true;
				if(last==null) add = true;
				if(space.length()>2)
					if(line.indexOf(space.substring(0,space.length()-2))==0) add = true;
				if(line.indexOf(space)==0 & line.charAt(level)!=' ' & add)
					re.add(line.substring(level));
				//System.out.println(line.indexOf(space)+" "+line.charAt(level)+" "+add+" "+last+" |"+line);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//System.out.println(re.size());
		return re;
	}
	
	public void tiedTick() {
		if(KeyManager.keyRelease(KeyEvent.VK_ESCAPE))
			StateManager.overlayState = -1;
		if(mouseDisable>0) mouseDisable--;
	}
	
	public void render(Graphics g) {
		for(int x=0;x<dio.size();x++) {
			g.setColor(Color.green);
			g.setFont( new Font("Serif",Font.PLAIN,15) );
			g.drawString(dio.get(x),0, 30*(x+1));
		}
		
		renderPortrait(g);
		renderMessage(g);
	}
	public void renderPortrait(Graphics g) {
		g.scalable = false;
		int headshotWidth = 300, headshotHeight = 300, border = 10;
		double height = Main.height-headshotHeight*Main.scale-border;
		
		g.setColor(new Color( 28, 30, 31));
			g.fillRect(border, Main.height-headshotHeight*Main.scale-border, headshotWidth*Main.scale, headshotHeight*Main.scale);
			g.drawImage(Assets.assets[28],border, height, headshotWidth*Main.scale, headshotHeight*Main.scale);
		g.setColor(new Color(200,200,200));
			g.drawRect(border, height, headshotWidth*Main.scale, headshotHeight*Main.scale);
			
		//Render Message
		int fontSize = (int)(Main.height/30.0);
		if(dio.size()>0) { 
			g.setColor(Color.green.brighter());
			g.setFont( new Font("Serif",Font.PLAIN,fontSize) );
			
			g.drawOutlinedString(dio.get(0),
					border * 2 + headshotWidth*Main.scale,
					Main.height-headshotHeight*Main.scale-border,
					Color.GREEN, Color.BLACK);
		}
		for(int z = 1;z < dio.size();z++) {
			double textBuffer = 5;
			double x = border * 2 + headshotWidth*Main.scale;
			double y = Main.height-headshotHeight*Main.scale-border + (z*(fontSize+textBuffer));
			double mx = MouseManager.mouseX;
			double my = MouseManager.mouseY;
			if(my>y & my<y+fontSize+textBuffer) {
				if(MouseManager.leftPressed & mouseDisable==0) {
					//g.drawOutlinedString("    "+dio.get(z), x, y, Color.GRAY.darker(), Color.BLACK);
					g.drawOutlinedString("    "+dio.get(z), x, y, Color.YELLOW, Color.BLACK);
					mouseDisable = 30;
					level+=1;
					loadChar(name,level,dio.get(z));
				}else 
					g.drawOutlinedString("    "+dio.get(z), x, y, Color.GRAY, Color.BLACK);
			} else
				g.drawOutlinedString("    "+dio.get(z), x, y, Color.GREEN, Color.BLACK);
			
		}
	}
	public void renderMessage(Graphics g) {
		
	}
}