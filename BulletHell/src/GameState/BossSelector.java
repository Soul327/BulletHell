package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Boss.Boss;
import Boss.NightsRow;
import Boss.Obj;
import Boss.Robbin;
import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;
import Misc.KeyManager;
import World.Arena;

public class BossSelector {
	
	boolean detailedView = false;
	int selected = 0;
	ArrayList<Boss> bosses = new ArrayList<Boss>();
	
	public BossSelector(){
		init();
	}
	public void init() {
		bosses = new ArrayList<Boss>();
		bosses.add(new Robbin());
		bosses.add(new NightsRow());
	}
	public void tiedTick() {
		if(detailedView) {
			if(KeyManager.keyRelease(KeyEvent.VK_ENTER) |
					KeyManager.keyRelease(KeyEvent.VK_E) ) {
				StateManager.overlayState = -1;
				Arena arena = new Arena();
				arena.boss = bosses.get(selected);
				StateManager.gameState.world = arena;
				init();
			}
			if(KeyManager.keyRelease(KeyEvent.VK_ESCAPE)) detailedView = false;
		} else {
			if(KeyManager.keyRelease(KeyEvent.VK_W)) selected--;
			if(KeyManager.keyRelease(KeyEvent.VK_S)) selected++;
			if(KeyManager.keyRelease(KeyEvent.VK_ENTER) |
					KeyManager.keyRelease(KeyEvent.VK_E) ) 
				detailedView = true;
			if(selected<0) selected = 0;
			if(selected>=bosses.size()) selected = bosses.size()-1;
		}
	}
	public void render(Graphics g) {
		if(detailedView)
			detailedView(g);
		else {
			for(int z=0;z<bosses.size();z++) {
				g.setFont( new Font("Serif",Font.PLAIN,40) );
				Color color = Color.green;
				if(z == selected) color = Color.gray;
				g.drawOutlinedString(bosses.get(z).name, 50, 40*z, color, Color.black);
			}
		}
	}
	public void detailedView(Graphics g) {
		//Selected
		Boss boss = bosses.get(selected);
		//g.drawOutlinedString(bosses.size()+" ", 100, 100);
		g.setFont( new Font("Serif",Font.PLAIN,40) );
		g.drawOutlinedString(boss.name, 10, 0, Color.green, Color.black);
		g.setFont( new Font("Serif",Font.PLAIN,30) );
		for(int z=0;z<boss.objs.size();z++) {
			Obj obj = boss.objs.get(z);
			Color color = Color.green;
			if(!obj.completed) {
				color = Color.gray;
				//Draw coin outline
				//g.drawImage(Assets.ani[1][0], 15, 55+(35*z), 25, 25);
			} else 
				g.drawImage(Assets.ani[0][0], 15, 55+(35*z), 25, 25);
			g.drawOutlinedString(obj.dis, 50, 40+(35*z), color, Color.black);
		}
	}
}
