package se.mickedahlgren.tutorial;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class PowerUp extends RandomSpawnEntity{

	private int timeTilSpawn = 0;
	private int spawnCounter = 0;
	void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(this.x, this.y, UNIT_SIZE, UNIT_SIZE);
	}
	
	public void resetSpawnTimer() {
		random = new Random();
		this.timeTilSpawn = random.nextInt(100);
		this.spawnCounter = 0;
	}
	

	
	public boolean isTimeToSpawn() {
		return (this.spawnCounter >= this.timeTilSpawn);
	}
	
	public void increaseSpawnCounter() {
		this.spawnCounter++;
	}

	public void moveOffScreen() {
		this.x = -2 * UNIT_SIZE;
		this.y = -2 * UNIT_SIZE;
	}
	
	public Boolean isOnScreen() {
		return this.x > 0 && this.y > 0;
	}
}
