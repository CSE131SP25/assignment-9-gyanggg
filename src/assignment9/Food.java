package assignment9; 

import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public class Food {

	public static final double FOOD_SIZE = 0.02;
	private double x, y;

	public Food() {
		this.x = Math.random(); // random between 0.0 and 1.0
		this.y = Math.random();
	}

	public void draw() {
		StdDraw.setPenColor(Color.RED);
		StdDraw.filledCircle(x, y, FOOD_SIZE);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
