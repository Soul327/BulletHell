package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Boss.Boss;
import Boss.NightsRow;
import Boss.Robbin;
import Gadget.Gadget;
import Gadget.GlitchBack;
import Gadget.PetRock;
import Gadget.Snap;
import Gadget.Teleporter;
import Gadget.TraceTeleporter;
import GameState.GameState;
import Gun.Acurveacy;
import Gun.DerpDeHerb;
import Gun.Gun;
import Gun.MyFirstGun;
import Gun.SMG;
import Main.StateManager;
import Misc.Graphics;
import Misc.KeyManager;

public class MenuState {
	int select = 1,gunSelect = 0,gadgetSelect = 0,bossSelect = 0;
	ArrayList<Gadget> gadget = new ArrayList<Gadget>();
	ArrayList<Gun> guns = new ArrayList<Gun>();
	ArrayList<Boss> bosses = new ArrayList<Boss>();
	
	public MenuState(){
		init();
	}
	public void init() {
		gadget = new ArrayList<Gadget>();
		guns = new ArrayList<Gun>();
		bosses = new ArrayList<Boss>();
		
		bosses.add(new Robbin());
		bosses.add(new NightsRow());
		
		gadget.add(new Teleporter());
		gadget.add(new TraceTeleporter());
		gadget.add(new PetRock());
		gadget.add(new GlitchBack());
		gadget.add(new Snap());
		
		guns.add(new MyFirstGun());
		guns.add(new DerpDeHerb());
		guns.add(new Acurveacy());
		guns.add(new SMG());
	}
	public void tick() {
		if(KeyManager.keyRelease(KeyEvent.VK_UP)) select--;
		if(KeyManager.keyRelease(KeyEvent.VK_DOWN)) select++;
		if(KeyManager.keyRelease(KeyEvent.VK_ENTER)) {
			StateManager.state = 1;
			StateManager.gameState = new GameState();
			StateManager.gameState.world.player.gun = guns.get(gunSelect);
			StateManager.gameState.world.player.gadget = gadget.get(gadgetSelect);
			StateManager.gameState.world.boss = bosses.get(bossSelect);
		}
		if(KeyManager.keyRelease(KeyEvent.VK_RIGHT)) {
			switch(select) {
				case 0:
					bossSelect++;
					if(bossSelect>=bosses.size()) bossSelect--;
					if(bossSelect<0) bossSelect--;
					break;
				case 1:
					gunSelect++;
					if(gunSelect>=guns.size()) gunSelect--;
					if(gunSelect<0) gunSelect--;
					break;
				case 2:
					gadgetSelect++;
					if(gadgetSelect>=gadget.size()) gadgetSelect--;
					if(gadgetSelect<0) gadgetSelect--;
					break;
			}
		}
		if(KeyManager.keyRelease(KeyEvent.VK_LEFT)) {
			switch(select) {
				case 0:
					bossSelect--;
					if(bossSelect>=bosses.size()) bossSelect++;
					if(bossSelect<0) bossSelect++;
					break;
				case 1:
					gunSelect--;
					if(gunSelect>=guns.size()) gunSelect++;
					if(gunSelect<0) gunSelect++;
					break;
				case 2:
					gadgetSelect--;
					if(gadgetSelect>=gadget.size()) gadgetSelect++;
					if(gadgetSelect<0) gadgetSelect++;
					break;
			}
		}
	}
	public void render(Graphics g) {
		int line = 0;
		g.setColor(Color.gray); g.fillRect(15, 100, 250, 125);
		g.setColor(Color.white); g.drawRect(15, 100, 250, 125);
		
		
		g.setColor(Color.white);
		g.setFont(new Font("Serif",Font.PLAIN,55));
		g.drawString("Game Title", 0, line);
		line+=70;
		
		g.setColor(Color.white);
		g.setFont(new Font("Serif",Font.PLAIN,15));
		g.drawString("Go fuck shit up!", 20, line);
		line+=20;
		
		g.setColor(Color.white);
		if(select==0) {
			g.setColor(Color.white);
			g.setColor(Color.gray); g.fillRect(275, 100, 750, 125);
			g.setColor(Color.white); g.drawRect(275, 100, 750, 125);
			g.setFont(new Font("Serif",Font.PLAIN,20));
			g.drawString(bosses.get(bossSelect).des, 280, 90);
			g.setColor(Color.yellow);
		}
		g.setFont(new Font("Serif",Font.PLAIN,25));
		g.drawString("<"+bosses.get(bossSelect).name+">", 20, line);
		line+=30;
		
		g.setColor(Color.white);
		if(select==1) {
			g.setColor(Color.white);
			g.setColor(Color.gray); g.fillRect(275, 100, 715, 125);
			g.setColor(Color.white); g.drawRect(275, 100, 715, 125);
			g.setFont(new Font("Serif",Font.PLAIN,20));
			g.drawString(guns.get(gunSelect).decription, 280, 90);
			//
			int x=1000,y=100,width=250,height=130;
			Gun gun = guns.get(gunSelect);
			g.setColor(Color.gray); g.fillRect(x, y, width, height);
			g.setColor(Color.white); g.drawRect(x, y, width, height);
			g.setColor(Color.white); g.drawString(gun.name, x+g.getStringLength(gun.name)/1.5, y);
			g.setColor(Color.white); g.drawString("Damage", x+5, y+g.getFontHeight());
				g.setColor(Color.white); g.drawString(""+gun.damage, x+width-g.getStringLength(gun.damage+"")-5, y+g.getFontHeight());
			g.setColor(Color.white); g.drawString("Accuracy", x+5, y+g.getFontHeight()+18);
				g.setColor(Color.white); g.drawString(""+gun.accuracy, x+width-g.getStringLength(gun.accuracy+"")-5, y+g.getFontHeight()+18);
			g.setColor(Color.white); g.drawString("Fire Delay", x+5, y+g.getFontHeight()+18*2);
				g.setColor(Color.white); g.drawString(""+gun.fireSpeed, x+width-g.getStringLength(gun.fireSpeed+"")-5, y+g.getFontHeight()+18*2);
			g.setColor(Color.white); g.drawString("Reload Speed", x+5, y+g.getFontHeight()+18*3);
				g.setColor(Color.white); g.drawString(""+gun.reloadSpeed, x+width-g.getStringLength(gun.reloadSpeed+"")-5, y+g.getFontHeight()+18*3);
			g.setColor(Color.white); g.drawString("Magazine Size", x+5, y+g.getFontHeight()+18*4);
				g.setColor(Color.white); g.drawString(""+gun.magSize, x+width-g.getStringLength(gun.magSize+"")-5, y+g.getFontHeight()+18*4);
			
			//
			g.setColor(Color.yellow);
		}
		g.setFont(new Font("Serif",Font.PLAIN,25));
		g.drawString("<"+guns.get(gunSelect).name+">", 20, line);
		line+=30;
		
		g.setColor(Color.white);
		if(select==2) {
			g.setColor(Color.white);
			g.setColor(Color.gray); g.fillRect(275, 100, 750, 125);
			g.setColor(Color.white); g.drawRect(275, 100, 750, 125);
			g.setFont(new Font("Serif",Font.PLAIN,20));
			g.drawString(gadget.get(gadgetSelect).decription, 280, 90);
			g.setColor(Color.yellow);
		}
		g.setFont(new Font("Serif",Font.PLAIN,25));
		g.drawString("<"+gadget.get(gadgetSelect).name+">", 20, line);
		line+=30;
		
		g.setColor(Color.white);
		if(select==3) g.setColor(Color.yellow);
		g.setFont(new Font("Serif",Font.PLAIN,25));
		g.drawString("  FIGHT", 20, line);
		line+=30;
	}
}
