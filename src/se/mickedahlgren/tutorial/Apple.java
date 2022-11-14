package se.mickedahlgren.tutorial;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Apple extends RandomSpawnEntity{

	void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(this.x, this.y, UNIT_SIZE, UNIT_SIZE);
	}
}
