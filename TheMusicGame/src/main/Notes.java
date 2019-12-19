package main;

import javax.swing.JPanel;

public class Notes extends JPanel {
	
	//---------------------play interface-------------------------------
	int bpm = PlayerInterface.bpm;
	int combo = PlayerInterface.combo;
	double speed = PlayerInterface.speed;
	//------------------------------------------------------------------
	
	int DistanceOneBeats = (int) (523/(bpm/60)*speed);
	int notes[][] = new int [combo][3];
	int roll[][] = new int[combo][2];
	double distanceToJudgeLine[] = new double[combo];
	
	Notes(){
		//-------------------note disign--------------------------------
		for(int i = 0; i<combo; i++) {
			roll[i][0] = -10;
			roll[i][1] = -10;
		}
		
		for(int i = 0; i<combo; i=i+2) {
			roll[i][0] = 0;
			roll[i][1] = 3;
		}
		for(int i = 1; i<combo; i=i+2) {
			roll[i][0] = 4;
			roll[i][1] = 7;
		}
		
		for(int i = 1; i<combo; i=i+2) {
			distanceToJudgeLine[i] = -i*DistanceOneBeats;
		}
		
		//-----------------------------------------------------------
		
		for(int i=0; i<combo; i++) {
			notes[i][0] = roll[i][0];
			notes[i][2] = roll[i][1];
			notes[i][1] = (int)distanceToJudgeLine[i] - 1046;
			
			
			/*
			System.out.println("DistanceOneBeats = " + DistanceOneBeats);
			System.out.println("distanceToJudgeLine = " + distanceToJudgeLine[i]);
			System.out.println();
			*/
		}
	}
	
}
