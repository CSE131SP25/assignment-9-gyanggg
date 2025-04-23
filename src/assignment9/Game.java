package assignment9;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {

	private Snake snake;
	private Food food;

	public Game() {
		StdDraw.enableDoubleBuffering();
		snake = new Snake();
		food = new Food();
	}

	public void play() {
		while (snake.isInbounds()) {
			int dir = getKeypress();
			snake.changeDirection(dir);
			snake.move();

			if (snake.eatFood(food)) {
				food = new Food(); // Replace eaten food
			}
			
			/*
			 * As soon as eatFood comes back true, 
			 * the old food object is discarded and replaced with new Food(), 
			 * whose constructor chooses fresh random x and y coordinates with Math.random(), 
			 * instantly relocating the snack.
			 */

			updateDrawing();
		}
		
		/*
		 * The main loop continues only while snake.isInbounds() returns true; 
		 * when the head crosses the 0–1 borders, 
		 * the loop quits and the screen is cleared and over‑laid with a red “Game Over!” message.
		 */

		// Game Over
		StdDraw.clear();
		StdDraw.setPenColor(java.awt.Color.RED);
		StdDraw.text(0.5, 0.5, "Game Over!");
		StdDraw.show();
	}

	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}

	private void updateDrawing() {
		StdDraw.clear();
		snake.draw();
		food.draw();
		StdDraw.show();
		StdDraw.pause(50);
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}

/*
 * play() is the game engine: it enables double‑buffering, 
 * then in a loop (a) polls the keyboard, 
 * (b) updates the snake’s direction and position, 
 * (c) checks for a food collision and respawns food when needed, 
 * (d) redraws the board every 50 ms with updateDrawing(). 
 * The loop terminates when the snake exits the unit square, 
 * at which point it clears the canvas and shows a red “Game Over!” banner.
*/ 
