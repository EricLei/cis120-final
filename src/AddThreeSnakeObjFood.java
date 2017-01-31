import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AddThreeSnakeObjFood extends Food {

    public static final String img_file = "cherry.png";
    private static BufferedImage img;

    public AddThreeSnakeObjFood(Point point) {
        super(point);
        try {
            if (img == null) {
                img = ImageIO.read(new File(img_file));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    @Override
    public void uniqueFunction(Snake snake) {
        snake.addOnePieceToSnake();
        snake.addOnePieceToSnake();
        snake.addOnePieceToSnake();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, getPosInGrid().x * 10, getPosInGrid().y * 10, width, height, null);
    }
}
