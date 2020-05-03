package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends Canvas implements ActionListener {
	
	//-------------------play config-------------------------------------
	int judgLine = GameValue.judgLine;
	int musicDelaytime = GameValue.musicDelaytime;
	int width = GameValue.width;
	int height = GameValue.height;
	double speed = GameValue.speed;
	double perfectJudgeTime = GameValue.perfectJudgeTime;
	double greatJudgeTime = GameValue.greatJudgeTime;
	boolean autoPlay = GameValue.autoPlay;
	//-------------------------------------------------------------------
	
	Typed hit = new Typed();
	Notes note = new Notes();
	ClickEffect clickEffect = new ClickEffect();
	Typed type = new Typed();
	MusicEngine musicObject = new MusicEngine();
	String filePath = "TheLamentationsOfTheLostOne.wav";

	static int key = -1;
	int notePlace[] = new int[note.combo];	//0 is roll 1 is height
	int roll[][] = new int[note.combo][2];
	
	int fps = 0;
	double perfectRange = (perfectJudgeTime/(1000.0/speed))*judgLine;
	double greatRange = (greatJudgeTime/(1000.0/speed))*judgLine;
	long startTime = System.currentTimeMillis();
	long clickEffectTime = 0;
	long clickEffectTime2 = 0;
	static boolean click = false;
	boolean start = true;
	boolean musicStart = false;
	boolean notesDelete = false;

	private void frame() {
		
	}
	
	private void start() {
		JFrame frame = new JFrame();
		JPanel startScence = new JPanel();
		JButton startButton = new JButton();
		
		frame.pack();
		frame.setVisible(true);
		frame.setSize(width,height);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(type);
		frame.add(this);
//		frame.add(startScence);
//
//		startButton.setSize(50, 100);
//		startButton.setLocation(200,500);
//		startButton.setText("START");
//		startButton.addActionListener(this);
//		
//		startScence.setBackground(Color.cyan);
//		startScence.add(startButton);
		
		System.out.println("noteRunRange(judgLine) : " + judgLine);
		System.out.println("Screen width : " + width + " / height : " + height);
		
		for(;;) {
			System.out.print("");
			if(start)
				break;
		}
		
		System.out.println("game start");
		startScence.setVisible(false);
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
		}
	}
	
	public void render() {
		
		long passedTime2 = System.currentTimeMillis() - startTime;
		
		if(passedTime2 > musicDelaytime && !musicStart) {
//			musicObject.playMusic(filePath);
//			musicStart = false;
		}
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//Draw background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(0, judgLine, width, 5);
		g.fillRect((70+100*2), 0, 5, height);
		g.fillRect((70+100*3), 0, 5, height);
		g.fillRect((70+100*4), 0, 5, height);
		g.fillRect((70+100*5), 0, 10, height);
		g.fillRect((70+100*6), 0, 5, height);
		g.fillRect((70+100*7), 0, 5, height);
		g.fillRect((70+100*8), 0, 5, height);
		
		for(int i=1; i<note.combo; i++) {
			//Calculate the place
			long newTime = System.currentTimeMillis();
			long playTime = newTime - startTime;
			int dynamicNoteRange = (int) ((((int)playTime)/(1000.0/speed))*judgLine);
			int noteHeight = note.notes[i][1] + dynamicNoteRange;
			notePlace[i] = note.notes[i][1] + dynamicNoteRange;
			roll[i][0] = note.notes[i][0];
			roll[i][1] = note.notes[i][2];
			
			if(autoPlay && noteHeight > judgLine) {
				//Do nothing
			}else {
				if(noteHeight < height) {
					g.fillRect((100+100*(roll[i][0]+1)), noteHeight, 50 + (roll[i][1]-roll[i][0]) * 100, 10);
				}
			}
			//Judgment
			if(click) {
				Thread t1 = new ClickEffect();
				t1.start();
				
				for(int j=0; j<note.combo; j++) {
					
					if(notePlace[j]>judgLine - (int)perfectRange && notePlace[j]<judgLine + (int)perfectRange && roll[j][0] <= key && key <= roll[j][1]) {
						System.out.println("perfect");
						note.notes[j][1] = 800;
						clickEffect.drawEffect();
						key = -1;
					
					}else if(notePlace[j]>judgLine - (int)greatRange && notePlace[j]<judgLine + (int)greatRange && roll[j][0] <= key && key <= roll[j][1]) {
						System.out.println("great");
						note.notes[j][1] = 800;
						key = -1;
					}
					
				}
				
			}
			
			click = false;
		}
		
		bs.show();
	}
	
	public static void main(String args[]) {
		Main main = new Main();
		main.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("start Button click");
		start = true;
	}
}