package Main;

import java.awt.Color;
import java.util.ArrayList;

import Misc.Graphics;

public class Life {
	static double scale = 4;
	public static double spreadChance = .07;
	public static Cell[][] cell = new Cell[(int)Math.round(Main.width/scale)][(int)Math.round(Main.height/scale)];
	
	public Life() {
		init();
	}
	
	public void init() {
		spreadChance -= .001;
		System.out.println(spreadChance + " " + gen);
		gen = 0;
		for(int x = 0;x < cell.length;x++)
			for(int y = 0;y < cell[x].length;y++) {
				Cell cel = new Cell(x,y);
				if(Math.random()<.1) {
					cel.alive = true;
					if(Math.random()<.01)
						cel.infected = true;
				}
				cell[x][y] = cel;
			}
	}
	
	int tick = 0,gen=0;
	
	public void tick() {
		/*
		if(tick>Main.maxFPS/60) {
			tick = 0;
			generationTick();
		}
		tick++;
		//*/
		generationTick();
	}
	
	public int getAliveCells() {
		int re = 0;
		for(int x = 0;x < cell.length;x++)
			for(int y = 0;y < cell[x].length;y++)
				if(cell[x][y].alive) re++;
		return re;
	}
	
	boolean threadTick = false;
	
	public void generationTick() {
		gen ++;
		if(threadTick) {
			for(int x = 0;x < cell.length;x++)
				new ThreadTick(cell[x]).start();
		} else {
			for(int x = 0;x < cell.length;x++)
				for(int y = 0;y < cell[x].length;y++)
					cell[x][y].generationTick();
		}
		if(getAliveCells()<25)
			init();
	}
	
	boolean threadRender = true;
	
	public void render(Graphics g) {
		if(threadRender) {
			for(int x = 0;x < cell.length;x++)
				threadRender(g,x,cell[x]);
		} else {
			for(int x = 0;x < cell.length;x++)
				for(int y = 0;y < cell[x].length;y++) {
					if(cell[x][y].alive) {
						g.setColor(Color.green);
						g.fillRect(x*scale, y*scale, scale, scale);
					}
					g.setColor(Color.green.darker());
					//g.drawRect(x*scale, y*scale, scale, scale);
				}
		}
	}
	public void threadRender(Graphics g,int x,Cell[] cells) {
		for(int y = 0;y < cell[x].length;y++)
			if(cell[x][y].alive) {
				g.setColor(Color.green);
				if(cell[x][y].infected)
					g.setColor(Color.magenta);
				g.fillRect(x*scale, y*scale, scale, scale);
			}
	}
}

class ThreadTick extends Thread {
	
	Cell[] cells;
	
	ThreadTick(Cell[] cells) {
		this.cells = cells;
	}
	
	public void run() {
		for(Cell c:cells)
			c.getAdjacent();
	}
}

class Cell{
	
	boolean alive = false, infected = false;
	int x, y;
	
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void generationTick() {
		int adj = getAdjacent();
		//Cells with 0 or 1 neighbors die of loneliness
		//Cells with 2 or 3 neighbors survive
		//Cells with 4 or more neighbors die of overcrowding
		//Cells with exactly 3 neighbors come back to life
		if(alive) {
			getAdjacentInfect();
			if(infected & Math.random()<.1) {
				alive = false;
				infected = false;
				return;
			}
			
			if(adj<=1) alive = false;
			if(adj>=4) alive = false;
		} else {
			if(adj==3) alive = true;
		}
	}
	public int getAdjacent() {
		int re = 0;
		//1 2 3
		//4 . 5
		//6 7 8
		if(x-1>=0 & y-1>=0)
			if(Life.cell[x-1][y-1].alive) re++;//1
		if(y-1>=0)
			if(Life.cell[x  ][y-1].alive) re++;//2
		if(x+1<Life.cell.length & y-1>=0)
			if(Life.cell[x+1][y-1].alive) re++;//3
		if(x-1>=0)
			if(Life.cell[x-1][y  ].alive) re++;//4
		if(x+1<Life.cell.length)
			if(Life.cell[x+1][y  ].alive) re++;//5
		if(x-1>=0 & y+1<Life.cell[x].length)
			if(Life.cell[x-1][y+1].alive) re++;//6
		if(y+1<Life.cell[x].length)
			if(Life.cell[x  ][y+1].alive) re++;//7
		if(x+1<Life.cell.length & y+1<Life.cell[x].length)
			if(Life.cell[x+1][y+1].alive) re++;//8
		return re;
	}
	public void getAdjacentInfect() {
		//1 2 3
		//4 . 5
		//6 7 8
		if(x-1>=0 & y-1>=0)
			if(Life.cell[x-1][y-1].infected) {
				if(Math.random()<Life.spreadChance) {
					infected = true;
					return;
				}
			};//1
		if(y-1>=0)
			if(Life.cell[x  ][y-1].infected) {
				if(Math.random()<Life.spreadChance) {
					infected = true;
					return;
				}
			};//2
		if(x+1<Life.cell.length & y-1>=0)
			if(Life.cell[x+1][y-1].infected) {
				if(Math.random()<Life.spreadChance) {
					infected = true;
					return;
				}
			};//3
		if(x-1>=0)
			if(Life.cell[x-1][y  ].infected) {
				if(Math.random()<Life.spreadChance) {
					infected = true;
					return;
				}
			};//4
		if(x+1<Life.cell.length)
			if(Life.cell[x+1][y  ].infected) {
				if(Math.random()<Life.spreadChance) {
					infected = true;
					return;
				}
			};//5
		if(x-1>=0 & y+1<Life.cell[x].length)
			if(Life.cell[x-1][y+1].infected) {
				if(Math.random()<Life.spreadChance) {
					infected = true;
					return;
				}
			};//6
		if(y+1<Life.cell[x].length)
			if(Life.cell[x  ][y+1].infected) {
				if(Math.random()<Life.spreadChance) {
					infected = true;
					return;
				}
			};//7
		if(x+1<Life.cell.length & y+1<Life.cell[x].length)
			if(Life.cell[x+1][y+1].infected) {
				if(Math.random()<Life.spreadChance) {
					infected = true;
					return;
				}
			};//8
	}
}
