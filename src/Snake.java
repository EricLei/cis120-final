
/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.util.LinkedList;

/**
 * A basic game object displayed as a black square, starting in the upper left
 * corner of the game court.
 * 
 */
public class Snake {
    public static final int SIZE = 10;
    public static final int INIT_X = 0;
    public static final int INIT_Y = 0;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    private LinkedList<SnakeObj> snakeList;
    private SnakeObj head;
    private Direction direction;

    /**
     * Note that, because we don't need to do anything special when constructing
     * a Square, we simply use the superclass constructor called with the
     * correct parameters
     */
    public Snake(int courtWidth, int courtHeight) {
        snakeList = new LinkedList<SnakeObj>();
        addOnePieceToSnake();
    }

    public void addOnePieceToSnake() {
        if (snakeList.size() == 0) {
            snakeList.add(new SnakeObj(new Point(0, 0)));
            head = snakeList.getFirst();
        }
        
        Direction dir = snakeList.getLast().getDirection();
        Point tail = snakeList.getLast().getPosInGrid();
        Point newPoint = null;

        if (dir == Direction.UP) {
            newPoint = new Point(tail.x, tail.y + 1);
        } else if (dir == Direction.DOWN) {
            newPoint = new Point(tail.x, tail.y - 1);
        } else if (dir == Direction.LEFT) {
            newPoint = new Point(tail.x + 1, tail.y);
        } else if (dir == Direction.RIGHT) {
            newPoint = new Point(tail.x - 1, tail.y);
        }
        SnakeObj obj = new SnakeObj(newPoint);
        obj.setDirection(dir);
        snakeList.addLast(obj);
    }

    public void move(Direction dir) {
        Point newPoint = head.getPosInGrid();
        if (dir == Direction.UP) {
            newPoint = new Point(head.getPosInGrid().x, head.getPosInGrid().y - 1);
        } else if (dir == Direction.DOWN) {
            newPoint = new Point(head.getPosInGrid().x, head.getPosInGrid().y + 1);
        } else if (dir == Direction.LEFT) {
            newPoint = new Point(head.getPosInGrid().x - 1, head.getPosInGrid().y);
        } else if (dir == Direction.RIGHT) {
            newPoint = new Point(head.getPosInGrid().x + 1, head.getPosInGrid().y);
        }

        for (int i = snakeList.size() - 1; i > 0; i--) {
            snakeList.get(i).setPosInGrid(snakeList.get(i - 1).getPosInGrid());
        }

        head.setPosInGrid(newPoint);
    }

    public void draw(Graphics g) {
        for (SnakeObj snake : snakeList) {
            snake.draw(g);
        }
    }

    public void setHeadDirection(Direction dir) {
        direction = dir;
    }

    public Direction getHeadDirection() {
        return direction;
    }

    public LinkedList<SnakeObj> getSnakeList() {
        return snakeList;
    }

    public void setSnakeList(LinkedList<SnakeObj> snakeList) {
        this.snakeList = snakeList;
    }

    public SnakeObj getHead() {
        return head;
    }

    public void setHead(SnakeObj head) {
        this.head = head;
    }
}
