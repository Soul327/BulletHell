package GameState;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import Boss.Boss;
import Main.Main;
import Main.StateManager;
import Misc.Assets;
import Misc.Graphics;
import World.Arena;
import World.Crowd;
import World.World;

public class GUI {
	int borderBuffer = 10;
	Player player;
	Boss boss;
	
	public GUI() {}
	boolean firstTick = true;
	public void tick(){
		if(firstTick) { 
			firstTick = false;
			player = StateManager.gameState.world.player;
			boss = StateManager.gameState.world.boss;
		}
	}
	public void renderBossHealth(Graphics g) {
		g.scalable = false;
		int temp = (int)(Main.width/2.2);
		int width = (int)(Main.width*0.1382306477);
		int height = (int)(Main.height*0.0264550898);
		g.setColor(new Color(50,50,50,150));
		g.fillRect(Main.width/2-temp/2+5, 13, temp-2, height);
		
		g.setColor(new Color(180, 12, 10).darker()); g.fillRect(Main.width/2-temp/2, 10, temp, height);
		g.setColor(new Color(180, 12, 10)); g.fillRect(Main.width/2-temp/2, 10, (boss.health/boss.maxHealth)*temp, height);
		g.setFont(new Font("Serif",Font.PLAIN,height));
		String s = boss.name;
		if(boss.title!=null) 
			if(boss.title.length()!=0) 
				s = boss.name + ", " + boss.title;
		g.drawOutlinedString(s, Main.width/2-temp/2+5, 0);
	}
	public void renderRating(Graphics g) {
		g.scalable = false;
		World world = StateManager.gameState.world;
		if(world instanceof Arena) {
			Arena a = (Arena) world;
			Crowd crowd = a.crowd;
			
			int temp = (int)(Main.width/2.2);
			int top = 35;
			int height = (int)(Main.height*0.01);
			g.setColor(new Color(50,50,50,150));
			g.fillRect(Main.width/2-temp/2+5, top+3, temp-2, height);
			
			g.drawImage(Assets.assets[26], Main.width/2-temp/2.0, top, temp, height);
			//g.setColor(new Color(0, 250, 0)); g.fillRect(Main.width/2, top, temp/2, height);
			//g.setColor(new Color(136, 20, 20)); g.fillRect(Main.width/2-temp/2, top, temp/2, height);
			//Arrows
			//g.setColor(Color.white); g.fillRect((Main.width/2)+temp*((crowd.rating+crowd.bonus)/(crowd.limit*2)), top-2, 5, height+4);
			int offset = 1;
			g.drawImage(Assets.assets[27], (Main.width/2)+temp*((crowd.rating+crowd.bonus)/(crowd.limit*2)), top-offset, 5, height+offset*2);
			
			//g.setColor(new Color(180, 12, 10)); g.fillRect(Main.width/2-temp/2, top, (boss.health/boss.maxHealth)*temp, height);
		}
	}
	public void renderPlayerHealth(Graphics g) {
		g.scalable = true;
		/*
		double health = StateManager.gameState.world.player.health;
		double healthPerHeart = 20/4.0;
		int x = 0;
		//System.out.println(health+" "+healthPerHeart);
		
		while(health>0) {
			if(health>=healthPerHeart*4) {
				g.drawImage(Assets.ani[2][0], 32.5*x, 0, 32.5, 30);
				health -= healthPerHeart*4;
			} else if(health>=healthPerHeart*3){
				g.drawImage(Assets.ani[2][1], 32.5*x, 0, 32.5, 30);
				health -= healthPerHeart*3;
			} else if(health>=healthPerHeart*2){
				g.drawImage(Assets.ani[2][2], 32.5*x, 0, 32.5, 30);
				health -= healthPerHeart*2;
			} else if(health>=healthPerHeart*1){
				g.drawImage(Assets.ani[2][3], 32.5*x, 0, 32.5, 30);
				health -= healthPerHeart*1;
			} else 
				health = 0;
			x++;
		}
		g.scalable = false;
		//*/
		//*
		g.scalable = false;
		int width = (int)(Main.width*0.1382306477);
		int height = (int)(Main.height*0.0224550898);
		g.setColor(new Color(50,50,50,150));
		g.fillRect(borderBuffer-borderBuffer/2, Main.height-height-borderBuffer/2, width, height);
		
		g.setColor(new Color(180, 12, 10).darker()); g.fillRect(borderBuffer, Main.height-height-borderBuffer, width, height);
		g.setColor(new Color(180, 12, 10)); g.fillRect(borderBuffer, Main.height-height-borderBuffer, width*Math.max(StateManager.gameState.world.player.health/StateManager.gameState.world.player.maxHealth,0), height);
		g.setFont(new Font("Serif",Font.PLAIN,height));
		g.drawOutlinedString(StateManager.gameState.world.player.health+"/"+StateManager.gameState.world.player.maxHealth, borderBuffer, Main.height-height*1.75-borderBuffer/2);
		//*/
	}
	public void renderAmmo(Graphics g) {
		int width = (int)(Main.width*0.1579778831);
		int height = (int)(Main.height*0.0224550898);
		Color color = new Color(230,180,20);
		
		g.setColor(new Color(50,50,50,150));
		g.fillRect(Main.width-width-borderBuffer/2, Main.height-height-borderBuffer/2, width, height);
		
		g.setColor(color.darker().darker()); 
		g.fillRect(Main.width-borderBuffer-width, Main.height-height-borderBuffer, width, height);
		g.setColor(color);
		g.fillRect(Main.width-borderBuffer-((double)player.gun.mag/player.gun.magSize)*width, Main.height-height-borderBuffer, ((double)player.gun.mag/player.gun.magSize)*width, height);
		g.setFont(new Font("Serif",Font.PLAIN,height));
		g.setColor(new Color(200,200,200));
		String s = (int)StateManager.gameState.world.player.gun.mag+" / "+(int)StateManager.gameState.world.player.gun.magSize;
		g.drawOutlinedString(s, Main.width-borderBuffer-g.getStringLength(s), Main.height-height*1.75-borderBuffer/2);
	}
	public void renderGadget(Graphics g) {
		if((double)player.gadget.coolDown/player.gadget.defaultCoolDown!=0){
			int width = (int)(Main.width*0.1382306477);
			int height = (int)(Main.height*0.0224550898);
			g.setColor(new Color(50,50,50,150));
			g.fillRect(Main.width-width-borderBuffer/2, Main.height-height*3+borderBuffer/2, width, height);
			
			g.setColor(new Color(255,140,  0).darker().darker());
			g.fillRect(Main.width-width-borderBuffer, Main.height-height*3, width, height);
			g.setColor(new Color(255,140,  0));
			g.fillRect(Main.width-borderBuffer-((double)player.gadget.coolDown/player.gadget.defaultCoolDown)*width, Main.height-height*3, Math.abs((double)-player.gadget.coolDown/player.gadget.defaultCoolDown)*width, height);
			g.setFont(new Font("Serif",Font.PLAIN,height));
			g.setColor(new Color(200,200,200));
		}
	}
	public void render(Graphics g){
		if(boss==null & player==null) return;
		if(boss.renderHealth) renderBossHealth(g);
		renderRating(g);
		renderPlayerHealth(g);
		renderGadget(g);
		if(player.gun.ammoUse>0) renderAmmo(g);
		/*
		//Gun
		if(player.gun.reloadTimmer==0) {
			g.setColor(Color.yellow);
			g.fillRect(Main.width-200, Main.height-35, (player.gun.mag/player.gun.magSize)*200, 10);
		}else {
			g.setColor(Color.red);
			g.fillRect(Main.width-200, Main.height-35, (player.gun.reloadTimmer/player.gun.reloadSpeed)*200, 10);
		}
		g.setColor(Color.green);g.fillRect(Main.width-200, Main.height-45, (StateManager.gameState.world.player.gun.fireTimmer/StateManager.gameState.world.player.gun.fireSpeed)*200, 10);
		//Gadget
		//Cooldown
		g.setColor(Color.orange);
		g.fillRect(Main.width-200, Main.height-25, StateManager.gameState.world.player.gadget.coolDown, 25);
		*/
	}
}
