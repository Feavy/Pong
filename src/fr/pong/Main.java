package fr.pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		GamePanel p = new GamePanel(60, 600, 600);
		JFrame window = new JFrame("Pong");
		window.setSize(600, 600);
		window.setContentPane(p);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		window.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				p.keyReleased(e.getKeyCode());
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				p.keyPressed(e.getKeyCode());
				
			}
		});
		
	}

}
