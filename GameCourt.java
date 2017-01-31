/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	// the state of the game logic
    private Snake snake;
    private Food food;
    private int score;
   // private Map<Integer>
    

	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text (i.e. Running...)

	// Game constants
	public static final int COURT_WIDTH = 300;
	public static final int COURT_HEIGHT = 300;
	public static final int SNAKE_VELOCITY = 4;
	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 100;

	public GameCourt(JLabel status) {
	    snake = new Snake(COURT_WIDTH, COURT_HEIGHT);
	    //reset();
	    spawnFood();
	    
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// The timer is an object which triggers an action periodically
		// with the given INTERVAL. One registers an ActionListener with
		// this timer, whose actionPerformed() method will be called
		// each time the timer triggers. We define a helper method
		// called tick() that actually does everything that should
		// be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start(); // MAKE SURE TO START THE TIMER!

		// Enable keyboard focus on the court area.
		// When this component has the keyboard focus, key
		// events will be handled by its key listener.
		setFocusable(true);

		// This key listener allows the square to move as long
		// as an arrow key is pressed, by changing the square's
		// velocity accordingly. (The tick method below actually
		// moves the square.)
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT && 
				        snake.getHeadDirection() != Direction.RIGHT) {
				    snake.setHeadDirection(Direction.LEFT);
				    snake.getSnakeList().getLast().setDirection(Direction.LEFT);
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT &&
				        snake.getHeadDirection() != Direction.LEFT) {
				    snake.setHeadDirection(Direction.RIGHT);
				    snake.getSnakeList().getLast().setDirection(Direction.RIGHT);
				}
				else if (e.getKeyCode() == KeyEvent.VK_DOWN &&
				        snake.getHeadDirection() != Direction.UP) {
				    snake.setHeadDirection(Direction.DOWN);
				    snake.getSnakeList().getLast().setDirection(Direction.DOWN);
				}
				else if (e.getKeyCode() == KeyEvent.VK_UP && 
				        snake.getHeadDirection() != Direction.DOWN) {
				   snake.setHeadDirection(Direction.UP);
				    snake.getSnakeList().getLast().setDirection(Direction.UP);
				}
			}
		});

		this.status = status;
	}
	


	/**
	 * (Re-)set the game to its initial state.
	 */
	public void reset() {
	    snake.getSnakeList().clear();
	    snake.setHeadDirection(Direction.DOWN);
	    snake.addOnePieceToSnake();
		
	    spawnFood();

		playing = true;
		score = 0;
	    status.setText("Score: " + score);
	    status.repaint();
	    
	    System.out.println(score);

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 */
	void tick() {
		if (playing) {
			// advance the square and snitch in their
			// current direction.
		    snake.move(snake.getHeadDirection());
		    
			if (snake.getHead().intersects(food)) {
			    food.uniqueFunction(snake);
			    snake.addOnePieceToSnake();
			    spawnFood();
			    score += 10;
			    status.setText("Score: " + score);
			    status.repaint();
			}
			
			checkLoseConditions();

			// update the display
			repaint();
		}
	}
	
	public void checkLoseConditions() {
	    boolean userLost = false;
	    
	    if (snake.getHead().getPosInGrid().x < 0) {
	        userLost = true;
            playing = false;
            status.setText("You lose!");
        }
        else if (snake.getHead().getPosInGrid().x > Grid.row) {
            userLost = true;
            playing = false;
            status.setText("You lose!");
        }
        if (snake.getHead().getPosInGrid().y < 0) {
            userLost = true;
            playing = false;
            status.setText("You lose!");
        }
        else if (snake.getHead().getPosInGrid().y > Grid.col) {
            userLost = true;
            playing = false;
            status.setText("You lose!");
        }

        for(int i = 1; i < snake.getSnakeList().size() - 4; i++) {
            if (snake.getHead().intersects(snake.getSnakeList().get(i))) {
                userLost = true;
                playing = false;
                status.setText("You lose!");
            }
        }
        
        try {
            if(userLost == true) {
                writeHighScore();    
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    public void spawnFood() {
        food = new Food(null);
        double rand = Math.random();
        if(rand < 0.5) {
            food = new AddThreeSnakeObjFood(null);
        }
        else {
            food = new Food(null);
        }
        
        Random random = new Random();
        int index_x = random.nextInt(Grid.row);
        int index_y = random.nextInt(Grid.col);
        Point newPoint = new Point(index_x, index_y);
        
        ArrayList<Point> snakeObjPos = new ArrayList<Point>();
        
        for(SnakeObj snake : snake.getSnakeList()) {
            snakeObjPos.add(snake.getPosInGrid());
        }
    
        while(snakeObjPos.contains(newPoint)) {
                index_x = random.nextInt(Grid.row);
                index_y = random.nextInt(Grid.col);
                newPoint = new Point(index_x, index_y);
        }
       food.setPosInGrid(newPoint); 
    }
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		food.draw(g);
		Grid.draw(g);
		snake.draw(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}

    public int getHighscore() throws IOException {
	    FileReader file = null;
	    BufferedReader reader = null;
	    try {
	        file = new FileReader("highscores.txt");
	        reader = new BufferedReader(file);
	        String l = reader.readLine();
	        String[] arr = l.split(" - ");
	        return Integer.parseInt(arr[1]);
	    }
	    catch (IOException e){
	        return -1;
	    }
	    finally {
	        try {
	            if(reader != null) {
	                reader.close();  
	            }
	        }
	        catch (IOException e) { 
	            throw e;
	        }
	    }
	}
	
	public void writeHighScore() throws IOException {
	     if(score > getHighscore()) {
	         String name = JOptionPane.showInputDialog
	                 ("Congratulations on a new highscore!  Enter your name: ");
	         String highscore = name + " - " + score + "\n";
	         
	         File score = new File("highscore.txt");
	         if(!score.exists()) {
	             try {
	                 score.createNewFile();
	             }
	             catch(IOException e){
	                 e.printStackTrace();
	             }
	         }
	         
	
	         FileWriter write = new FileWriter("highscore.txt", true);
	         BufferedWriter writer = new BufferedWriter(write);
	         try {
	             writer.append(highscore);
	             writer.flush();
	         }
	         catch (Exception e){

	         }
	         finally {
	             try {
	                 if(write != null) {
	                     write.close();  
	                 }
	             }
	             catch (IOException e) { 
	                 throw e;
	             }
	         }
	     }
	}
}
