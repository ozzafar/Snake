
package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javax.swing.JFrame;

public class Snake implements ActionListener, KeyListener {
	public int ticks = 0, direction = DOWN, tail = 3, time = 0;
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 15;
	public Point head, apple;
	public ArrayList<Point> snakeparts = new ArrayList<Point>();
	public static Snake snake;
	public JFrame jframe;
	public RenderPanel renderpanel = new RenderPanel();
	public javax.swing.Timer timer = new javax.swing.Timer(60, this);
	public Random random;
	public boolean over = false;
	public Dimension dim;

	public Snake() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake");
		jframe.setVisible(true);
		jframe.setSize(752, 652);
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		jframe.add(renderpanel);
		jframe.setResizable(false); // can't change frame's size
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		random = new Random();
		apple = new Point(random.nextInt(38), random.nextInt(38));
		head = new Point(0, 0);
		timer.start();
	}

	public void startgame() {
		direction = DOWN;
		over = false;
		snakeparts.clear();
		tail = 3;
		time = 0;
		apple = new Point(random.nextInt(38), random.nextInt(38));
		random = new Random();
		head = new Point(0, 0);
		timer.start();
	}

	public static void main(String[] args) {
		snake = new Snake();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		renderpanel.repaint();
		ticks++;

		if ( head != null && over == false) {
			time++;
			snakeparts.add(head);
			if (snakeparts.size() > tail)
				snakeparts.remove(0);
			if (direction == UP)
				if (head.y - 1 >= 0)
					head = new Point(head.x, head.y - 1);
				else
					over = true;
			if (direction == DOWN)
				if (head.y + 1 < 42)
					head = new Point(head.x, head.y + 1);
				else
					over = true;
			if (direction == LEFT)
				if (head.x - 1 >= 0)
					head = new Point(head.x - 1, head.y);
				else
					over = true;
			if (direction == RIGHT)
				if (head.x + 1 < 50)
					head = new Point(head.x + 1, head.y);
				else
					over = true;

			if (apple != null && head.equals(apple)) {
				tail++;
				apple.setLocation(random.nextInt(47), random.nextInt(39));
			}
			for (Point point : snake.snakeparts) { //check collisions
				if (head.equals(point)) {
					over = true;
					System.out.println("o");
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if (i == KeyEvent.VK_LEFT && direction != RIGHT)
			direction = LEFT;
		if (i == KeyEvent.VK_RIGHT && direction != LEFT)
			direction = RIGHT;
		if (i == KeyEvent.VK_UP && direction != DOWN)
			direction = UP;
		if (i == KeyEvent.VK_DOWN && direction != UP)
			direction = DOWN;
		if (i == KeyEvent.VK_SPACE && over)
			startgame();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
