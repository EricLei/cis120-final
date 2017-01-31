import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

public class MyTests {
    private SnakeObj head;
    private LinkedList<SnakeObj> snakeList;
    private Food food;
    
    public MyTests() {
        head = new SnakeObj(new Point (0, 5));
        snakeList = new LinkedList<SnakeObj>();
        snakeList.add(head);
        snakeList.add(new SnakeObj(new Point(0, 6)));
        food = new Food(new Point(10, 10));
    }
    
    @Test
    public void testCollisionWithFood() {
        assertEquals("Collision with Food: ", false, head.intersects(food));
        food = new Food(new Point(0, 5));
        assertEquals("Collision with Food: ", true, head.intersects(food));
    }
    
    @Test
    public void testCollisionWithSnake() {
        assertEquals("Collision with Snake: ", false, head.intersects(snakeList.get(1)));
        head = new SnakeObj(new Point (0, 6));
        assertEquals("Collision with Snake: ", true, head.intersects(snakeList.get(1)));
    }
    
    @Test
    public void testCollisionWithWall() {
        ArrayList<Point> leftWall = new ArrayList<Point>(30);
        for(int i = 0; i < leftWall.size(); i++) {
            leftWall.add(new Point (-1, i));
        }
        
        for(Point point : leftWall) {
            assertEquals("Collision with Left Wall: ", false, head.equals(point));
        }
        
        ArrayList<Point> rightWall = new ArrayList<Point>(30);
        for(int i = 0; i < rightWall.size(); i++) {
            leftWall.add(new Point (31, i));
        }
        
        for(Point point : rightWall) {
            assertEquals("Collision with Right Wall: ", false, head.equals(point));
        }
        
        ArrayList<Point> topWall = new ArrayList<Point>(30);
        for(int i = 0; i < topWall.size(); i++) {
            leftWall.add(new Point (i, -1));
        }
        
        for(Point point : topWall) {
            assertEquals("Collision with Right Wall: ", false, head.equals(point));
        }
        
        ArrayList<Point> bottomWall = new ArrayList<Point>(30);
        for(int i = 0; i < bottomWall.size(); i++) {
            leftWall.add(new Point (i, 31));
        }
        
        for(Point point : bottomWall) {
            assertEquals("Collision with Right Wall: ", false, head.equals(point));
        }
    }
}
