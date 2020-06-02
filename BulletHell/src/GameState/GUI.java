package GameState;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import Boss.Boss;
import Main.Main;
import Main.StateManager;
import Misc.Graphics;

public class GUI {
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
	public void render(Graphics g){
		g.scalable = false;
		if(boss==null & player==null) return;
		String s;
		int borderBuffer = 10,width,height;
		
		//Boss Health & Shield
		int temp = (int)(Main.width/2.2);
		width = (int)(Main.width*0.1382306477);
		height = (int)(Main.height*0.0264550898);
		g.setColor(new Color(50,50,50,150));
		g.fillRect(Main.width/2-temp/2+5, 13, temp-2, height);
		
		g.setColor(new Color(180, 12, 10).darker()); g.fillRect(Main.width/2-temp/2, 10, temp, height);
		g.setColor(new Color(180, 12, 10)); g.fillRect(Main.width/2-temp/2, 10, (boss.health/boss.maxHealth)*temp, height);
		g.setFont(new Font("Serif",Font.PLAIN,height));
		g.setColor(new Color(200,200,200)); g.drawString(boss.name, Main.width/2-temp/2+5, 0);
		
		//Player Health
		width = (int)(Main.width*0.1382306477);
		height = (int)(Main.height*0.0224550898);
		g.setColor(new Color(50,50,50,150));
		g.fillRect(borderBuffer-borderBuffer/2, Main.height-height-borderBuffer/2, width, height);
		
		g.setColor(new Color(180, 12, 10).darker()); g.fillRect(borderBuffer, Main.height-height-borderBuffer, width, height);
		g.setColor(new Color(180, 12, 10)); g.fillRect(borderBuffer, Main.height-height-borderBuffer, width*Math.max(StateManager.gameState.world.player.health/StateManager.gameState.world.player.maxHealth,0), height);
		g.setFont(new Font("Serif",Font.PLAIN,height));
		g.setColor(new Color(200,200,200)); g.drawString(StateManager.gameState.world.player.health+"/"+StateManager.gameState.world.player.maxHealth, borderBuffer, Main.height-height*1.75-borderBuffer/2);
		
		//Lower Right
		//Gadget
		if((double)player.gadget.coolDown/player.gadget.defaultCoolDown!=0){
			width = (int)(Main.width*0.1382306477);
			height = (int)(Main.height*0.0224550898);
			g.setColor(new Color(50,50,50,150));
			g.fillRect(Main.width-width-borderBuffer/2, Main.height-height*3+borderBuffer/2, width, height);
			
			g.setColor(new Color(255,140,  0).darker().darker());
			g.fillRect(Main.width-width-borderBuffer, Main.height-height*3, width, height);
			g.setColor(new Color(255,140,  0));
			g.fillRect(Main.width-borderBuffer-((double)player.gadget.coolDown/player.gadget.defaultCoolDown)*width, Main.height-height*3, Math.abs((double)-player.gadget.coolDown/player.gadget.defaultCoolDown)*width, height);
			g.setFont(new Font("Serif",Font.PLAIN,height));
			g.setColor(new Color(200,200,200));
		}
		//Mag
		width = (int)(Main.width*0.1579778831);
		height = (int)(Main.height*0.0224550898);
		g.setColor(new Color(50,50,50,150));
		g.fillRect(Main.width-width-borderBuffer/2, Main.height-height-borderBuffer/2, width, height);
		
		g.setColor(new Color(20, 25, 180).darker().darker()); 
		g.fillRect(Main.width-borderBuffer-width, Main.height-height-borderBuffer, width, height);
		g.setColor(new Color(20, 25, 180));
		g.fillRect(Main.width-borderBuffer-((double)player.gun.mag/player.gun.magSize)*width, Main.height-height-borderBuffer, ((double)player.gun.mag/player.gun.magSize)*width, height);
		g.setFont(new Font("Serif",Font.PLAIN,height));
		g.setColor(new Color(200,200,200));
		s = (int)StateManager.gameState.world.player.gun.mag+" / "+(int)StateManager.gameState.world.player.gun.magSize;
		g.drawString(s, Main.width-borderBuffer-g.getStringLength(s), Main.height-height*1.75-borderBuffer/2);
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
		g.scalable = true;
	}
}
