package se.mickedahlgren.tutorial;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

interface Direction {
	byte U = 0; 
	byte R = 1;	
	byte D = 2;
	byte L = 3;
	byte None = -1;
}

public class Snake{
	Random random;
	int GAME_UNITS = GamePanel.GAME_UNITS;
	int UNIT_SIZE = GamePanel.UNIT_SIZE;
	int SCREEN_W = GamePanel.SCREEN_W;
	int SCREEN_H = GamePanel.SCREEN_H;
	
	interface SnakeState {
		String normal = "normal";
		String power1 = "power1";
	}
	String state = SnakeState.normal;
	int bodyParts = 6;
	int direction = Direction.R;
	int powerUpDuration = 0;
	int powerUpDurationLimit = 60;
	int applesEaten = 0;
	final int xPos[] = new int[GAME_UNITS];
	final int yPos[] = new int[GAME_UNITS];
	Color bodyCol = new Color(50, 168, 82);
	Color headCol = new Color(50, 168, 82);
	void handleState() {
		if(this.state == SnakeState.power1) {
			this.powerUpDuration++;
		}
		if(this.powerUpDuration >= this.powerUpDurationLimit) {
			this.state = SnakeState.normal;
			this.powerUpDuration = 0;
		}
	}
	void grow() {
		this.bodyParts++;
	}
	
	void shrink() {
		this.bodyParts--;
	}
	
	void eatApple(){
		this.applesEaten++;
		this.grow();
	}
	
	void powerUp() {
		this.state = SnakeState.power1;
	}
	
	void turnRight() {
		this.direction += this.direction == 3 ? -3 : 1;
	}
	
	void turnLeft() {
		this.direction += this.direction == 0 ? 3 : -1;
	}
	
	void turn(byte d) {
		switch(d) {
		case Direction.L:
			if(this.direction == Direction.U) {
				this.turnLeft();
			} else if (this.direction == Direction.D) {
				this.turnRight();
			}
			break;
		case Direction.R:
			if(this.direction == Direction.U) {
				this.turnRight();
			} else if (this.direction == Direction.D) {
				this.turnLeft();
			}
			break;
		case Direction.D:
			
			if(this.direction == Direction.L) {
				this.turnLeft(); 
			} else if(this.direction == Direction.R) {
				this.turnRight(); 
			};
			break;
		case Direction.U:
			if(this.direction == Direction.L) {
				this.turnRight(); 
			} else if(this.direction == Direction.R) {
				this.turnLeft(); 
			};
			break;
		}
	}
	Boolean isSelfColliding() {
		if(this.state == SnakeState.power1) {
			return false;
		}
		for(int i = this.bodyParts; i>0; i--) {
			if((this.xPos[0] == this.xPos[i]) && (this.yPos[0] == this.yPos[i])) {
				return true;
			}
		}
		return false;
	}
	void move() {
		// each body part takes the position of their left-side neighbor in the array;
		for(int i = this.bodyParts; i>0; i--) {
			this.xPos[i] = this.xPos[i - 1];
			this.yPos[i] = this.yPos[i - 1];
		}
	
		// set new head position
		switch(this.direction) {
			case Direction.U:
				this.yPos[0] = this.yPos[0] - UNIT_SIZE;
				break;
			case Direction.D:
				this.yPos[0] = this.yPos[0] +  UNIT_SIZE;
				break;
			case Direction.R:
				this.xPos[0] = this.xPos[0] + UNIT_SIZE;
				break;
			case Direction.L:
				this.xPos[0] = this.xPos[0] - UNIT_SIZE;
				break;
		}

		if(this.xPos[0] >= SCREEN_W) {
			this.xPos[0] = 0;
		}
		if(this.xPos[0] < 0) {
			this.xPos[0] = SCREEN_W;
		}
		if(this.yPos[0] >= SCREEN_H) {
			this.yPos[0] = 0;
		}
		if(this.yPos[0] < 0) {
			this.yPos[0] = SCREEN_H;
		}
		
	}
	
	// draw the snake positions to screen
	void draw(Graphics g) {
		random = new Random();
		for(int i = 0; i < this.bodyParts; i++) {
			// snake body
			int red = Math.max(4, bodyCol.getBlue() - (this.bodyParts - i));
			int green = Math.max(84,  bodyCol.getGreen() - (this.bodyParts - i));
			int blue = Math.max(27,  bodyCol.getRed() - (this.bodyParts - i));

			
			if(this.state == SnakeState.power1) {
				red = Math.max(50 + this.powerUpDuration, random.nextInt(255));
				green = Math.max(50 + this.powerUpDuration, random.nextInt(255));
				blue = Math.max(50 + this.powerUpDuration, random.nextInt(255));
			}
			
			g.setColor(new Color(red, green, blue));
			g.fillRect(this.xPos[i], this.yPos[i], UNIT_SIZE, UNIT_SIZE);
			// snake head
			if(i == 0) {
				g.setColor(headCol);
				g.fillRect(this.xPos[i], this.yPos[i], UNIT_SIZE, UNIT_SIZE);
			}
		}
	}

}
