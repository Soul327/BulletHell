package Gun;

public class MyFirstGun extends Gun{
	public MyFirstGun() {
		damage = 11;
		accuracy = 89.3;
		fireSpeed = 12.5;
		reloadSpeed = 1;
		magSize = 10;
		name = "My First Gun";
		decription = "A weak pistol that should only be picked as your only option";
		init();
	}
}
