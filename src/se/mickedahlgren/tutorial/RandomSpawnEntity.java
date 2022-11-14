package se.mickedahlgren.tutorial;

import java.util.Random;

public class RandomSpawnEntity {
	Random random;
	int x;
	int y;
	int GAME_UNITS = GamePanel.GAME_UNITS;
	int UNIT_SIZE = GamePanel.UNIT_SIZE;
	int SCREEN_W = GamePanel.SCREEN_W;
	int SCREEN_H = GamePanel.SCREEN_H;
	void spawn() {
		random = new Random();
		this.x = random.nextInt((int)SCREEN_W/UNIT_SIZE)*UNIT_SIZE;
		this.y = random.nextInt((int)SCREEN_H/UNIT_SIZE)*UNIT_SIZE;
	}
	
	Boolean checkCollision(int x, int y) {
		if(x == this.x && y == this.y) {
			this.spawn();
			return true;
		}
		return false;
	}
}
