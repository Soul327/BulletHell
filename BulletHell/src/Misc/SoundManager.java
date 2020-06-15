package Misc;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import Main.Main;

public class SoundManager extends Thread{
	public static float volume = .5f;
	String location;
	Clip clip;
	
	public SoundManager(String location) {
		this.location = location;
	}
	//*
	public void run() {
		try {
			clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(location).getAbsoluteFile()); 
			clip.open(inputStream);
			
			setVolume(SoundManager.volume);
			
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public float getVolume() {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		return (float) Math.pow(10f, gainControl.getValue() / 20f);
	}
	
	public void setVolume(float volume) {
		if (volume < 0f || volume > 1f)
			throw new IllegalArgumentException("Volume not valid: " + volume);
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(20f * (float) Math.log10(volume));
}
	//*/
}

class sound {
	
}