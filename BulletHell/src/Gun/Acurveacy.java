package Gun;

import GameState.Bullet;
import GameState.World;
import Misc.MouseManager;

public class Acurveacy extends Gun{
	public Acurveacy() {
		damage = 7;
		accuracy = 100;
		fireSpeed = 3;
		reloadSpeed = 35;
		magSize = 30;
		name = "Acurveacy";
		decription = "A gun that has changing accuracy and a fast firerate.";
		init();
	}
	boolean inc = true;
	public void activate() {
		if(inc) {
			accuracy+=1;
			if(accuracy>100)
				inc=false;
		}else {
			accuracy-=1;
			if(accuracy<5)
				inc=true;
		}
	}
}
