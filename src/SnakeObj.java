import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class SnakeObj extends GameObj {

    public SnakeObj(Point point) {
        super(0, 0, 10, 10, 10, 10, GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, point);
        posInGrid = point;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        if (getPosInGrid() != null) {
        g.fillRect(getPosInGrid().x * 10, getPosInGrid().y * 10, 10, 10);
        }
    }
}
