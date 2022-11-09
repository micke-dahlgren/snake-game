package se.mickedahlgren.tutorial;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	GameFrame(){
		this.add(new GamePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		// places window in middle of the screen
		this.setLocationRelativeTo(null);
	}
}
