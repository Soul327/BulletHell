package Gun;

import GameState.Bullet;
import GameState.World;
import Misc.MouseManager;

public class SMG extends Gun{
	public SMG() {
		damage = 5;
		accuracy = 70;
		fireSpeed = 2;
		reloadSpeed = .9;
		magSize = 25;
		bulletSpeed = 15;
		name = "SMG";
		decription = "A SMG.";
		init();
	}
	boolean inc = true;
	public void activate() {}
}
