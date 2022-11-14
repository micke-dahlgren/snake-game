package se.mickedahlgren.tutorial;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	static final int UNIT_SIZE = 20;
    static final int SCREEN_W = ((int)size.getWidth()/2) - (((int)size.getWidth() / 2) % UNIT_SIZE);
    static final int SCREEN_H = ((int)size.getHeight()/2)  - (((int)size.getHeight() / 2) % UNIT_SIZE);
    static final int GAME_UNITS = (SCREEN_W * SCREEN_H) / UNIT_SIZE;
    static GameLogic gameLogic = new GameLogic();
	int gameTimeDelay = gameLogic.gameTimeDelay;
	Timer timer;
	
    GamePanel(){
		this.setPreferredSize(new Dimension(GamePanel.SCREEN_W, GamePanel.SCREEN_H));
		this.setBackground(Color.black);
		this.setFocusable(true);
		timer = new Timer(gameTimeDelay, this);
		timer.start();
		this.addKeyListener(new MyKeyAdapter());
		gameLogic.startGame();
		
    }
    
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
    
    public static void draw(Graphics g) {
		if(!gameLogic.running) {
			gameLogic.gameOver(g);
			return;
		}
		// debug grid
		for(int i = 0; i < SCREEN_W / UNIT_SIZE; i++) {
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_H);
			g.drawLine(0, i*UNIT_SIZE, SCREEN_W, i*UNIT_SIZE);
		}
		
		gameLogic.draw(g);
		
		// paint score
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int margin = (int)GamePanel.SCREEN_W/32;
		g.drawString(
			"Score: " + gameLogic.snake.applesEaten,
			(GamePanel.SCREEN_W - margin - 100), 
			margin
		);
	}
    
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				gameLogic.snake.turn(Direction.L);
				break;
			case KeyEvent.VK_RIGHT:
				gameLogic.snake.turn(Direction.R);
				break;
			case KeyEvent.VK_DOWN:
				gameLogic.snake.turn(Direction.D);
				break;
			case KeyEvent.VK_UP:
				gameLogic.snake.turn(Direction.U);
				break;
			case KeyEvent.VK_SPACE:
				// snake.powerUp();
				break;
			case KeyEvent.VK_ESCAPE:
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				break;
			}
			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gameLogic.gameLoop();
		timer.setDelay(gameLogic.gameTimeDelay);
		if(!gameLogic.running) {
			timer.stop();
		}
		repaint();
		
	}
}
