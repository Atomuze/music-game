package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Main extends Canvas implements ActionListener {
	Typed hit = new Typed();
	Notes note = new Notes();
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	
	public static int judgeLinePos = 523;
	int notePlace[] = new int[note.combo];	//0 is roll 1 is height
	int roll[][] = new int[note.combo][2];
	int width = gd.getDisplayMode().getWidth();
	int height = gd.getDisplayMode().getHeight();
	int noteRunRange = (int) 3*height/4;
	
	int fps = 0;
	double clickEffect = -1;
	
	MusicEngine musicObject = new MusicEngine();
	String filePath = "TheLamentationsOfTheLostOne.wav";
	
	//-------------------play interface---------------------------- 
	double speed = PlayingConfig.speed;
	int musicDelaytime = PlayingConfig.musicDelaytime;
	double perfectJudgeTime = PlayingConfig.perfectJudgeTime;
	double greatJudgeTime = PlayingConfig.greatJudgeTime;
	//-------------------------------------------------------------
	
	double perfectRange = (perfectJudgeTime/(1000.0/speed))*523.0;
	double greatRange = (greatJudgeTime/(1000.0/speed))*523.0;
	long startTime = System.currentTimeMillis();
	long clickEffectTime = 0;
	long clickEffectTime2 = 0;
	
	static int key = -1;
	static boolean click = false;
	boolean start = false;
	boolean musicStart = false;
	boolean notesDelete = false;
	
	private void start() {
		if(start)
			return;
		start = true;
		System.out.println("game start");
		run();
	}
	
	public void run() {
		long time1 = System.currentTimeMillis();
		while (start) {
			render();
			fps++;
			long time2 = System.currentTimeMillis();
			long passedTime = time2 - time1; 
			if((int)passedTime >= 1000) {
				passedTime = 0;
				System.out.println("fps= " + fps);
				fps = 0;
				time1 = time2;
			}
			//System.out.println(passedTime);
		}
		
	}
	
	public void render() {
		long passedTime2 = System.currentTimeMillis() - startTime;
		
		if(passedTime2 > musicDelaytime && !musicStart) {
			musicObject.playMusic(filePath);
			musicStart = true;
		}
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(0, 540, width, 5);
		g.fillRect((70+100*2), 0, 5, height);
		g.fillRect((70+100*3), 0, 5, height);
		g.fillRect((70+100*4), 0, 5, height);
		g.fillRect((70+100*5), 0, 10, height);
		g.fillRect((70+100*6), 0, 5, height);
		g.fillRect((70+100*7), 0, 5, height);
		g.fillRect((70+100*8), 0, 5, height);
		
		for(int i=0; i<note.combo; i++) {
			
			//Calculate the place
			long newTime = System.currentTimeMillis();
			long playTime = newTime - startTime;
			int dynamicNoteRange = (int) ((((int)playTime)/(1000.0/speed))*noteRunRange);
			
			int noteHeight = note.notes[i][1] + dynamicNoteRange;
			notePlace[i] = note.notes[i][1] + dynamicNoteRange;
			roll[i][0] = note.notes[i][0];
			roll[i][1] = note.notes[i][2];
			
			//g.fillOval((400+100*(roll[i]+1)), noteHeight, 50, 50);
			g.fillRect((100+100*(roll[i][0]+1)), noteHeight, 50 + (roll[i][1]-roll[i][0]) * 100, 10);
			
			//System.out.println("i=" + i + " /roll=" + roll[i][0] + " /noteHeight=" + noteHeight);
			
			//Judgment
			//Judge line 
			
			if(click) {
				for(int j=0; j<note.combo; j++) {
					
					if(notePlace[j]>judgeLinePos - (int)perfectRange && notePlace[j]<judgeLinePos + (int)perfectRange && roll[j][0] <= key && key <= roll[j][1]) {
						System.out.println("perfect");
						note.notes[j][1] = 1000;
						
						clickEffectTime = System.currentTimeMillis();
						g.fillRect(250+(int) (100.0*clickEffect), 527, 50, 30);
						key = -1;
					}else if(notePlace[j]>judgeLinePos - (int)greatRange && notePlace[j]<judgeLinePos + (int)greatRange && roll[j][0] <= key && key <= roll[j][1]) {
						System.out.println("great");
						note.notes[j][1] = 1000;
						
						clickEffectTime = System.currentTimeMillis();
						clickEffect = (roll[j][0] + roll[j][1])/2;
						g.fillRect(250+(int) (100.0*clickEffect), 527, 50, 30);
						
						key = -1;
					}
					
				}
				
			}
			
			if(PlayingConfig.autoPlay) {
				if(notePlace[i]>noteRunRange) {
					note.notes[i][1] = noteRunRange;
					note.notes[i][1] = 1000;
					clickEffect = (roll[i][0] + roll[i][1])/2;
					g.fillRect(250+(int) (100.0*clickEffect), 527, 50, 30);
				}
			}`	
			
			click = false;
			//deleted note
			if(notePlace[i]>700) {
				note.notes[i][1] = 1000;
			}
		}
		
		bs.show();
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		Main main = new Main();
		Typed type = new Typed();
		
		frame.pack();
		frame.setVisible(true);
		frame.setSize(main.width,main.height);
		frame.setLocation(-10, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(main);
		frame.addKeyListener(type);
		
		main.start();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}