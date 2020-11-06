package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Typed implements KeyListener {
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("Typed");
		if (e.getKeyCode() == KeyEvent.VK_A) {
			Main.key = 0;
			Main.click = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			Main.key = 1;
			Main.click = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			Main.key = 2;
			Main.click = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_F) {
			Main.key = 3;
			Main.click = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_J) {
			Main.key = 4;
			Main.click = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_K) {
			Main.key = 5;
			Main.click = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_L) {
			Main.key = 6;
			Main.click = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SEMICOLON) {
			Main.key = 7;
			Main.click = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
