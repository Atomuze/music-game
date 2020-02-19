package main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicEngine {

	public void playMusic(String filePath) {
		// TODO Auto-generated method stub
		try {
			File music = new File(filePath);
			
			if(music.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				System.out.println("music start play...");
			}else {
				System.out.println("can't find file");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
