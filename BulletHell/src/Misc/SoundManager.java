package Misc;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Main.Main;

public class SoundManager extends Thread{
	//*
	public void run() {
		File actual = new File("res/Sounds/explosions");
		for( File f : actual.listFiles()) System.out.println( f.getName()+"\n  "+f.getAbsolutePath());
		
    try {
      Clip clip = AudioSystem.getClip();
      AudioInputStream inputStream = AudioSystem.getAudioInputStream(
        Main.class.getResourceAsStream("C:\\Users\\souls\\OneDrive\\Programing\\Java\\Eclipse\\BulletHell\\res\\Sounds\\explosions\\explosion01.wav"));
      clip.open(inputStream);
      clip.start(); 
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
	}
	//*/
}
