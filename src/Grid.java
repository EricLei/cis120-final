import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Grid extends JPanel {

    // private GridCell[][] board;
    public static final int row = GameCourt.COURT_WIDTH / 10;
    public static final int col = GameCourt.COURT_HEIGHT / 10;
    public static Rectangle[][] grid = new Rectangle[row][col];
    private Point position;

    public Grid() {
        grid = new Rectangle[row][col];
        position = new Point();
    }
    
    public static void draw(Graphics g) {
        int x = 0;
        int y = 0;
        for (int a = 0; a < row; a++) {
            for (int b = 0; b < col; b++) {
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
