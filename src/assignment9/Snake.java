package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;

	public Snake() {
		segments = new LinkedList<>();
		segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE)); // Start in center
		deltaX = 0;
		deltaY = 0;
	}

	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}

	public void move() {
		BodySegment head = segments.getFirst();
		double newX = head.getX() + deltaX;
		double newY = head.getY() + deltaY;

		// Move tail segments forward
		for (int i = segments.size() - 1; i > 0; i--) {
			BodySegment prev = segments.get(i - 1);
			segments.get(i).setPosition(prev.getX(), prev.getY());
		}

		// Move head
		head.setPosition(newX, newY);
	}
	
	/*
	 * changeDirection maps the latest WASD keystroke to (deltaX, deltaY) steps, 
	 * and move() first drags every tail segment onto the spot of the segment ahead of it, 
	 * then places the head a fixed MOVEMENT_SIZE farther in the chosen direction.
	 */

	public void draw() {
		for (BodySegment segment : segments) {
			segment.draw();
		}
	}

	public boolean eatFood(Food f) {
		BodySegment head = segments.getFirst();
		double dist = Math.hypot(head.getX() - f.getX(), head.getY() - f.getY());
		if (dist < SEGMENT_SIZE + Food.FOOD_SIZE) {
			// Add new segment at tail's position
			BodySegment tail = segments.getLast();
			segments.add(new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE));
			return true;
		}
		
		/*When the head‑to‑food distance is smaller than the two radii combined, 
		 *the method adds a brand‑new BodySegment at the current tail position, 
		 *lengthening the LinkedList<BodySegment> that represents the snake, 
		 *and returns true to signal the bite.
		 */
		
		return false;
	}

	public boolean isInbounds() {
		BodySegment head = segments.getFirst();
		double x = head.getX();
		double y = head.getY();
		return x >= 0 && x <= 1 && y >= 0 && y <= 1;
	}
}
