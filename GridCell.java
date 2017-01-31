import java.awt.Point;

public class GridCell {
    private GameObj obj;
    private Point position;
    
    public GridCell(GameObj gameObj, Point pos) {
        obj = gameObj;
        position = pos;
    }

    public GameObj getObj() {
        return obj;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setObj(GameObj obj) {
        this.obj = obj;
    }
}
