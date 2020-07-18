package Boss;

public class Obj {
	
	public boolean completed = false;
	public int type = 0;
	public String name = "",des = "Test Discription";
	Boss boss;
	
	public Obj(int type, Boss boss) {
		this.type = type;
		this.boss = boss;
		switch(type) {
			case 1:
				name = "Completion";
				des = "Reduce the bosses health to 0 or less";
				break;
			default:
				name = "Unknown Obj";
				if(Math.random()<.5) completed = true;
				break;
		}
		name += completed;
	}
	public void checkComp() {
		switch(type) {
			case 1:
				if(boss.health <= 0)
					completed = true;
				break;
		}
	}
}
