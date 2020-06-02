package Gun;

public class DerpDeHerb extends Gun{
	public DerpDeHerb() {
		damage = 200;
		accuracy = 100;
		fireSpeed = 3;
		bulletSpeed = 3;
		reloadSpeed = 120;
		magSize = 1;
		name = "DerpDeHerb";
		decription = "A powerfull slow firing pistol that shoot a small bullet. Yea, I am not sure either.";
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
