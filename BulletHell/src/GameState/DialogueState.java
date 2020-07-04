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
import Misc.Graphics;
import Misc.KeyManager;

public class DialogueState {
	
	File file;
	ArrayList<String> dio = new ArrayList<String>();
	int level = 0;
	
	public DialogueState() {
		file = new File("res/NPC_chat/testy.txt");
		//System.out.println(getMessage(0,null));
		//System.out.println(getMessage(1,"I don't see this as very fancy"));
		//System.out.println(getMessage(1,"Wowie is this a test or something"));
		//System.out.println(getMessage(2," One"));
		
		dio = getDio(0,null);
		for(String s:dio) System.out.println(s);
		System.out.println("----\n");
//		
//		dio = getDio(1,"I don't see this as very fancy"); 
//		for(String s:dio) System.out.println(s);
//		System.out.println("----\n");
//		
//		dio = getDio(1,"Wowie is this a test or something");
//		for(String s:dio) System.out.println(s);
//		System.out.println("----\n");
//		
//		dio = getDio(2,"One");
//		for(String s:dio) System.out.println(s);
//		System.out.println("----\n");
//		
//		dio = getDio(3,"Test");
//		for(String s:dio) System.out.println(s);
//		System.out.println("----\n");
		
		//System.exit(0);
	}
	public ArrayList<String> getDio(int level, String last) {
		boolean add = false;
		Scanner scanner;
		ArrayList<String> re = new ArrayList<String>();
		
		String temp = "";
		for(int x = level-1;x>0;x--)
			temp += " ";
		last = temp += last;
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
				//System.out.println(line.indexOf(space)+" "+line.charAt(level)+" "+add+"|"+line);
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
	}
	
	public void render(Graphics g) {
		for(int x=0;x<dio.size();x++) {
			g.setColor(Color.green);
			g.setFont( new Font("Serif",Font.PLAIN,15) );
			g.drawString(dio.get(x),0, 30*(x+1));
		}
		
		renderPortrait(g);
	}
	public void renderPortrait(Graphics g) {
		g.scalable = false;
		int headshotWidth = 300, headshotHeight = 300, border = 10;
		
		g.setColor(new Color( 28, 30, 31));
			g.fillRect(border, Main.height-headshotHeight*Main.scale-border, headshotWidth*Main.scale, headshotHeight*Main.scale);
		g.setColor(new Color(200,200,200));
			g.drawRect(border, Main.height-headshotHeight*Main.scale-border, headshotWidth*Main.scale, headshotHeight*Main.scale);
	}
}