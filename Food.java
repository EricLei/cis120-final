import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Food extends GameObj {
    public static final String img_file = "apple.png";
    public static final int SIZE = 10;
    public static final int INIT_X = 200;
    public static final int INIT_Y = 200;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    
    public static int food_x;
    public static int food_y;

    private static BufferedImage img;

    public Food(Point point) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_X, INIT_Y, SIZE, SIZE, GameCourt.COURT_WIDTH,
                GameCourt.COURT_HEIGHT, point);
        posInGrid = point;
        
        try {
            if (img == null) {
                img = ImageIO.read(new File(img_file));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    public void uniqueFunction(Snake snake) {
        
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, getPosInGrid().x * 10, getPosInGrid().y * 10, width, height, null);
    }
}
