package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import GameState.Dialog;
import GameState.Player;
import Misc.Assets;
import Misc.KeyManager;
import Misc.MouseManager;
import Misc.Prerender;
import World.Crowd;

public class Main implements Runnable {
	
	public static boolean scaling = true;
	public static int width=0, height=0, devMode = 0, maxFPS = 120, baseWidth=1366, baseHeight=768;
	public static double scale = 1,fps=0;
	public static ArrayList<String> debugMessages = new ArrayList<String>();
	
	private boolean running = false;
	private Thread thread;
	private JFrame frame;
	private Canvas canvas;
	public static KeyManager keyManager;
	public static MouseManager mouseManager;
	public static StateManager stateManager;
	public Dialog dialogTest;
	public static Thread load;
	
	//Starts the program
	public static void main(String args[]) {
		Main main = new Main(); 
		main.start();
	}
	
	public Main(){
		fps = maxFPS;
		baseWidth = 1600; baseHeight = 900;
		Main.width = 1280; Main.height = 800;
		Main.width = baseWidth; Main.height = baseHeight;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		Assets.init();
		load = new Prerender();
		load.start();
	}
	
	private void init(){
		frame = new JFrame("");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addMouseListener(mouseManager);
		frame.addMouseMotionListener(mouseManager);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.addMouseListener(mouseManager);
		canvas.addMouseMotionListener(mouseManager);
		frame.add(canvas);
		frame.pack();
		frame.addKeyListener(keyManager);
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		width=canvas.getWidth();
		height=canvas.getHeight();
		
		stateManager=new StateManager();
		dialogTest = new Dialog();
	}
	private void tick(){
		width=canvas.getWidth();
		height=canvas.getHeight();
		keyManager.tick();
		if(KeyManager.keyRelease(KeyEvent.VK_EQUALS) & devMode<1) devMode++;
		if(KeyManager.keyRelease(KeyEvent.VK_MINUS) & devMode>0) devMode--;
		if(devMode>0) {
			if(KeyManager.keyRelease(KeyEvent.VK_F1)) Player.invincibility =! Player.invincibility;
			if(KeyManager.keyRelease(KeyEvent.VK_F2)) scaling =! scaling;
			if(KeyManager.keyRelease(KeyEvent.VK_F3)) Crowd.coinMode = !Crowd.coinMode;
			if(KeyManager.keyRelease(KeyEvent.VK_F4)) StateManager.overlayState = 3;
		}
		stateManager.tick();
		//dialogTest.tiedTick();
		debugMessages = new ArrayList<String>();
		if(Player.invincibility) debugMessages.add("Invincibility enabled");
		if(Crowd.coinMode) debugMessages.add("Coin mode enabled");
		
		try { debugMessages.add("Entity Size:"+StateManager.gameState.world.entities.size()); }catch(Exception e) {}
		//Sort debug
		
	}
	
	private BufferStrategy bs;
	private Graphics g;
	
	private void render(){
		scale = (double)width/baseWidth;
		bs = canvas.getBufferStrategy();
		if(bs == null){
			canvas.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//Clear Screen
		g.clearRect(0, 0, width, height);
		g.setColor(new Color(18,20,21));
		//g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		//Draw Here!
		
		Graphics2D g2d=(Graphics2D) g;
		
		stateManager.render(g2d);
		//dialogTest.render(new Misc.Graphics(g2d));
		
		//Drawing fps
		if(devMode>0) {
			g.setColor(Color.green);
			Font font = new Font("Serif",Font.PLAIN,15);
			g.setFont(font);
			g.drawString("FPS "+fps,0, 15);
			g.drawString("Scale "+scale,0, 30);
			for(int x=0;x<debugMessages.size();x++) {
				g.drawString(debugMessages.get(x),0, x*15+45);
			}
			g.drawString("Dev Mode "+devMode,0, height-20);
		}
		//End Drawing!
		bs.show();
		g.dispose();
	}
	public void run(){
		init();
		int ticks = 0;
		double timePerTick = 1000000000 / maxFPS;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000){
				fps=ticks;
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}