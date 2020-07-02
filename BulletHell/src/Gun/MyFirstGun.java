package Gun;

public class MyFirstGun extends Gun{
	public MyFirstGun() {
		damage = 11;
		accuracy = 89.3;
		fireSpeed = 1;
		reloadSpeed = 1;
		magSize = 10;
		ratingBonus = 2.5;
		fireMode = 1;
		name = "My First Gun";
		decription = "A weak pistol that should only be picked as a last resort";
		init();
	}
}