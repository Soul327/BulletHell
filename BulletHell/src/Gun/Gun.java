package Gun;

import Entity.Bullet;
import GameState.Player;
import Main.Main;
import Main.StateManager;
import Misc.Mat;
import Misc.MouseManager;
import Misc.SoundManager;
import World.World;

public abstract class Gun {
	public static boolean kick = true;
	public boolean triggerHeld = false;
	/* fireMode
	 * 0 = Automatic
	 * 1 = Semiauto
	 */
	byte fireMode = 0;
	/* kickBack
	 * When a bullet is fired the camera will kick back
	 */
	byte kickBack = 5;
	public int ammoUse = 0;
	public double damage = 11, accuracy = 89.3, fireSpeed = 12.5, reloadSpeed = 30, magSize = 10, bulletSpeed = 10;
	public double mag = magSize,reloadTimmer=0,fireTimmer = 0;
	public double ratingBonus = 0;
	public String name = "", decription = "";
	
	public void init() {
		mag = magSize;
	}
	public void fire() {
		if(mag>=ammoUse & fireTimmer<=0 & reloadTimmer<=0) {
			if(triggerHeld & fireMode == 1) return;
			double angle = 0;
			if(Main.scaling) {
				 angle = -Math.toDegrees( GameState.GameState.getPlayerMouseAngle() );
			}else {
				angle = -Math.toDegrees(
						Mat.getAngle(
								StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2,
								StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2,
								MouseManager.mouseX-StateManager.gameState.world.camX,
								MouseManager.mouseY-StateManager.gameState.world.camY));
			}
			double acc = (Math.random()*(100-accuracy)-(100-accuracy)/2) / (accuracy/10);
			angle += acc;
			Bullet bul = new Bullet(bulletSpeed,angle);
			bul.x = (StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2)-bul.height/2;
			bul.y = (StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2)-bul.width/2;
			bul.playerDam = false;
			bul.bossDam = true;
			bul.damage = damage;
			//System.out.println("X:"+mx+" Y:"+my+" PX:"+px+" PY:"+py+" Angle:"+angle);
			StateManager.gameState.world.entities.add(bul);
			activate();
			mag -= ammoUse;
			fireTimmer=(Main.maxFPS*fireSpeed)/60;
			SoundManager.playSound("res/Crowd/applause"+(int)(Math.random()*3+1)+".wav",.25f);
			if(kick) {
				StateManager.gameState.world.camBounceX -= kickBack*Math.cos(Math.toRadians(angle));
				StateManager.gameState.world.camBounceY += kickBack*Math.sin(Math.toRadians(angle));
			}
		}
	}
	public void activate() {}
	/*
	 * 
	 */
	public void tick() {
		if(mag<ammoUse & reloadTimmer==0) {
			reloadTimmer=Main.maxFPS*reloadSpeed;
		}
		if(reloadTimmer>0) {
			reloadTimmer--;
			if(reloadTimmer<=0) {
				mag = magSize;
				reloadTimmer = 0;
			}
		}
		if(fireTimmer>0) fireTimmer--;
	}
}
