package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;

public class GameValue extends Canvas {
	public static double Speed = 6;
	public static double speed = 1+(0.1*(Speed-6));					
	public static int musicDelaytime = 250;			//millsecond
	public static double perfectJudgeTime = 33.0;	//millsecond
	public static double greatJudgeTime = 83.0;		//millsecond
	public static int bpm = 60;
	public static int combo = 100;
	public static boolean autoPlay = false;
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int width = (int) screenSize.getWidth();
	public static final int height = (int) screenSize.getHeight();
	public static final int judgLine = (int) 3*height/4;
	
}
