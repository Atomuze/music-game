package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JPanel;

public class Notes extends JPanel {
	
	//---------------------------config---------------------------------
	int bpm = GameValue.bpm;
	int combo = GameValue.combo;
	double speed = GameValue.speed;
	int judgLine = GameValue.judgLine;
	//------------------------------------------------------------------

	int noteRunRange = judgLine;
	int notes[][] = new int [combo][3];
	int roll[][] = new int[combo][2];
	double distanceBetweenOneBeats = (noteRunRange/(bpm/60)*speed);
	double distanceToJudgeLine[] = new double[combo];
	
	Notes(){
		
		System.out.println("Prepare notes : design");
		
		//-------------------note disign--------------------------------
		
		for(int i=1; i<combo; i++) {
			roll[i][0] = 4;
			roll[i][1] = 7;
		}
		
		//--------------------------------------------------------------
		
		for(int i=1; i<combo; i++) {
			distanceToJudgeLine[i] = -i*distanceBetweenOneBeats;
		}
		
		//--------------------------------------------------------------
		
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
