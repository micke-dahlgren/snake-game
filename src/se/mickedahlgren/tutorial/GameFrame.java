package se.mickedahlgren.tutorial;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5062988707170943793L;

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
