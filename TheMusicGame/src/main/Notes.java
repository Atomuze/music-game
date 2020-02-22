package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JPanel;

public class Notes extends JPanel {
	
	//---------------------------config---------------------------------
	int bpm = PlayingConfig.bpm;
	int combo = PlayingConfig.combo;
	double speed = PlayingConfig.speed;
	//------------------------------------------------------------------
	

	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	//Main main = new Main();
	int height = gd.getDisplayMode().getHeight();
	int noteRunRange = (int)height/2;
	
	double distanceBetweenOneBeats = (noteRunRange/(bpm/60)*speed);
	int notes[][] = new int [combo][3];
	int roll[][] = new int[combo][2];
	double distanceToJudgeLine[] = new double[combo];
	
	Notes(){
		
		System.out.println("Prepare notes : desing ");
		
		//-------------------note disign--------------------------------
		
		for(int i=1; i<combo; i++) {
			roll[i][0] = 4;
			roll[i][1] = 7;
		}
		
		//-------------------------
		
		for(int i=1; i<combo; i++) {
			distanceToJudgeLine[i] = -i*distanceBetweenOneBeats;
		}
		
		//-----------------------------------------------------------
		
		System.out.println("Prepare notes : drawing...");
		
		for(int i=0; i<combo; i++) {
			
			
			
			notes[i][0] = roll[i][0];
			notes[i][2] = roll[i][1];
			notes[i][1] = (int)distanceToJudgeLine[i];
			
			/*
			System.out.println("DistanceOneBeats = " + DistanceOneBeats);
			System.out.println("distanceToJudgeLine = " + distanceToJudgeLine[i]);
			System.out.println();
			*/
			
		}
	}
	
}
