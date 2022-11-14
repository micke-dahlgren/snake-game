package se.mickedahlgren.tutorial;


import java.util.Random;

import se.mickedahlgren.tutorial.Snake.SnakeState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;

public class GameLogic {
	

	boolean running = false;
	Random random;
	Snake snake = new Snake();
	Apple apple = new Apple();
	PowerUp powerUp1 = new PowerUp();
	int standardDelay = 120;
	int powerUpDelay = 60;
	int gameTimeDelay = 120;
	GameLogic(){
		random = new Random();
	}
	
	public void startGame(){
		// spawns new apple
		apple.spawn();
		powerUp1.moveOffScreen();
		powerUp1.resetSpawnTimer();
		running = true;

	}
	
	void draw(Graphics g) {
		// paint the apple;
		apple.draw(g);
		
		// paint snake
		snake.draw(g);
		
		// paint powerUp
		powerUp1.draw(g);
	}


	public void gameOver(Graphics g) {
		GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fonts = gEnv.getAvailableFontFamilyNames();
		int randomFontIndex = random.nextInt(fonts.length - 1);
		g.setColor(Color.red);
		g.setFont(new Font(fonts[randomFontIndex], Font.BOLD, 75));

		g.drawString(
			"Game Over", 
			GamePanel.SCREEN_W/2 - 200, 
			GamePanel.SCREEN_H/2 
		);
		g.drawString(
				"Your score: " + snake.applesEaten, 
				GamePanel.SCREEN_W/2 - 200, 
				GamePanel.SCREEN_H/2 + 100
			);
	}


	void gameLoop() {
		if(running) {
			
			snake.move();
			
			// this is dumb
			if (apple.checkCollision(snake.xPos[0], snake.yPos[0])) {
				snake.eatApple();
			};
			
			// this is also dumb
			if (powerUp1.checkCollision(snake.xPos[0], snake.yPos[0])) {
				snake.powerUp();
				powerUp1.moveOffScreen();
				gameTimeDelay = powerUpDelay;
			};
	
			snake.handleState();
			if(snake.state == SnakeState.normal) {
				gameTimeDelay = standardDelay;
			}
			if(snake.isSelfColliding()) {
				running = false;
			}
			
			if(!powerUp1.isOnScreen() && snake.state != SnakeState.power1) {
				powerUp1.increaseSpawnCounter();
			}
			if(powerUp1.isTimeToSpawn()) {
				powerUp1.spawn();
				powerUp1.resetSpawnTimer();
				
			}
			
		}
	}
	
	

}
