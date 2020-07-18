package Gun;

import GameState.Bullet;
import Misc.MouseManager;
import World.World;

public class SMG extends Gun{
	public SMG() {
		damage = 1;
		//accuracy = 70;
		accuracy = 100;
		fireSpeed = 2;
		reloadSpeed = .9;
		magSize = 25;
		bulletSpeed = 20;
		name = "SMG";
		decription = "A SMG.";
		init();
	}
	boolean inc = true;
	public void activate() {}
}