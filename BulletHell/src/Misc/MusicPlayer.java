package Misc;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
	
	public static Clip clip;
	
	public static void main(String args[]) {
		playerMusic("C:\\Users\\souls\\Downloads\\Game.wav");
		System.out.println("SOUND");
	}
	public static void playerMusic(String filePath) {
		try {
			File musicPath = new File(filePath);
			if(musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
