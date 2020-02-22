package main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicEngine {

	public void playMusic(String filePath) {
		// TODO Auto-generated method stub
		try {
			
			System.out.println("Music engine start");
			
			File music = new File(filePath);
			
			if(music.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				System.out.println("Music start play...");
			}else {
				System.out.println("Can't find music file");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
