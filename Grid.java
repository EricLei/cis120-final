import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Grid extends JPanel{

    //private GridCell[][] board;
    public static final int row = GameCourt.COURT_WIDTH / 10;
    public static final int col = GameCourt.COURT_HEIGHT / 10;
    public static Rectangle[][] grid = new Rectangle[row][col];
    private Point position;

    public Grid() {
        grid = new Rectangle[row][col];
        position = new Point();
    }
    
   /* public void startUp(Graphics g, LinkedList<Snake> snake) {
        int x = 0;
        int y = 0;
        for(int i = 0; i < GameCourt.COURT_WIDTH; i+=10) {
            for(int j = 0; j < GameCourt.COURT_HEIGHT; j+=10) {
                g.setColor(Color.RED);
                g.drawRect(i, j, 10, 10);
                if(i == 0 && j == 0) {
                    Snake mySnake = new Snake(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
                    snake.add(mySnake);
                    board[x][y] = new GridCell(
                            mySnake, new Point(x, y));
                }
                else if (i == 200 && j == 200) {
                    board[x][y] = new GridCell(
                            new Food(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT), 
                            new Point(x, y));
                } else {
                    board[x][y] = new GridCell(
                            new EmptySpace(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT), 
                            new Point(x, y));
                }
                y++;
            }
            y = 0;
            x++;
        }
    }*/

    public static void draw(Graphics g) {
        int x = 0;
        int y = 0;
        for(int a = 0; a < row; a++) {
            for(int b = 0; b < col; b++) {
                g.setColor(Color.RED);
                g.drawRect(x, y, 10, 10);
                grid[a][b] = new Rectangle();
                x += 10;
            }  
            y += 10;
            x = 0;
        }
    }
}
