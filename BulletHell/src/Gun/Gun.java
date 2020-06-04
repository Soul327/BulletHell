package Gun;

import GameState.Bullet;
import GameState.Player;
import GameState.World;
import Main.Main;
import Main.StateManager;
import Misc.Mat;
import Misc.MouseManager;

public abstract class Gun {
	public int damage = 11,ammoUse = 1;
	public double accuracy = 89.3, fireSpeed = 12.5, reloadSpeed = 30, magSize = 10, bulletSpeed = 10;
	public double mag = magSize,reloadTimmer=0,fireTimmer = 0;
	public String name = "", decription = "";
	
	public void init() {
		mag = magSize;
	}
	public void fire() {
		if(mag>=ammoUse & fireTimmer<=0 & reloadTimmer<=0) {
			double angle = 0;
			if(Main.scaling) {
				 angle = -Math.toDegrees(
						Mat.getAngle(
								(StateManager.gameState.world.player.x+StateManager.gameState.world.player.width/2+StateManager.gameState.world.camX)*Main.scale, 
								(StateManager.gameState.world.player.y+StateManager.gameState.world.player.height/2+StateManager.gameState.world.camY)*Main.scale,
								MouseManager.mouseX,
								MouseManager.mouseY));
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
			StateManager.gameState.world.bullet.add(bul);
			activate();
			mag -= ammoUse;
			fireTimmer=(Main.maxFPS*fireSpeed)/60;
		}
	}
	public void activate() {}
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
