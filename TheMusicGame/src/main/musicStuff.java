package main;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

public class musicStuff {

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
