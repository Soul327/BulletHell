package Menu;

import Misc.Graphics;
import Misc.Prerender;

import java.awt.Color;

import Main.Main;

public class Loading {
	
	public void tick() {
		
	}
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(Main.width/6, Main.height-Main.height/10, (Main.width-Main.width/3)*( (double)Prerender.prerenderSmoke/Prerender.prerenderSmokeSize ), 20);
		g.setColor(Color.white);
		g.drawRect(Main.width/6, Main.height-Main.height/10, (Main.width-Main.width/3), 20);
		
		g.setColor(Color.white);
		g.fillRect(Main.width/6, Main.height-Main.height/10+25, (Main.width-Main.width/3)*( (double)Prerender.prerenderSmoke/Prerender.prerenderSmokeSize ), 20);
		g.setColor(Color.white);
		g.drawRect(Main.width/6, Main.height-Main.height/10+25, (Main.width-Main.width/3), 20);
	}
}
