package Main;

import Misc.PlaySound;

public class MakeSound {

	public static void main(String args[]) throws InterruptedException {
		new PlaySound("res/Sounds/explosions/explosion08.wav").start(); Thread.sleep(1000);
		new PlaySound("res/Sounds/explosions/explosion01.wav").start(); Thread.sleep(1000);
		new PlaySound("res/Sounds/explosions/explosion04.wav").start(); Thread.sleep(1000);
		new PlaySound("res/Sounds/explosions/explosion02.wav").start(); Thread.sleep(1000);
	}
}