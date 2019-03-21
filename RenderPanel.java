package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 752, 652);
		Snake snake = Snake.snake;
		g.setColor(Color.GREEN);
		for (Point point : snake.snakeparts) {
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		g.setColor(Color.RED);
		g.fillRect(snake.apple.x * Snake.SCALE, snake.apple.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		g.setColor(Color.BLACK);
		String str = "Length: " + (snake.tail+1) + ", Time: " + snake.time/10;
		g.drawString(str, snake.dim.width/4 - str.length()*2, 20);
		if (snake.over) {
			g.drawString("GAME-OVER", snake.dim.width/4 , snake.dim.width/4);
			g.drawString("Enter SPACE to start again!", snake.dim.width/4 - 40 , snake.dim.width/4+30);
		}
	}
}
