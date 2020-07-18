package Boss;

public class Obj {
	
	public boolean completed = false;
	public int type = 0;
	public String dis = "";
	Boss boss;
	
	public Obj(int type, Boss boss) {
		this.type = type;
		this.boss = boss;
		switch(type) {
			case 1:
				dis = "Completion";
				break;
			default:
				dis = "Unknown Obj";
				if(Math.random()<.5) completed = true;
				break;
		}
		dis += completed;
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
