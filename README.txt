=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: ericlei
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. You may copy and paste from your proposal
  document if you did not change the features you are implementing.

  1. Modeling state using 2-D arrays or collections
     I stored the body of the snake in a LinkedList.  I also stored the grid in a 2D array with 
     each square representing particular row and column.

     Using a LinkedList allows access to the head and tail of the snake, which makes it
     easier to tell if the snake has eaten the food and it allows for easy access to 
     add body parts to the tail.  A 2D array also allows for a row and column representation.

  2. Inheritance and Subtyping
     I have a parent food class that will allow for different effects on the snake for
     different food items.
    
     Every food will have some general properties that are inherited from the parent class.  That way,
     I do not have to repeat code.  I created a AddThreeSnakeObjFood which adds an additional three 
     SnakeObj to the snake.

  3. Using I/O to parse a novel file format
     I plan to keep a high score table as well as a list as well as a scoring system 
     that adds points whenever the snake eats a piece of food.  Each food adds 10 points to the 
     score and there is a high score table in highscore.txt.  Using I/O allows for consistent way 
     to store data persistently between runs of my game.

  4. Using JUnit to test some features of your model
     I used JUnit to test different components of my game to make sure that they are
     working properly.  For instance, I tested for collisions, such as when the snake collides
     with the wall or with a piece of food.  This is not trivial because colliding wall entails
     losing the game and colliding with food is the objective of Snake.
     
     Adding JUnits is appropriate because it allows for independent testing of each component in the 
     game without involving the GUI.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Direction - unchanged from what was given
  
  Food - what the snake eats in order to increase his length by one.  
  
  AddThreeSnakeObjFood - acts like Food but adds three additional SnakeObj to the snake.
  
  Game - essentially unchanged from what was given except I added a JButton for instructions and 
         a JLabel to keep track of the score.
         
  GameCourt - contains the logic to allow the snake to move when an arrow key is pressed, updates
              the score whenever the snake collides with the food, checks the losing conditions,
              spawns the food at random locations, and writes the high score (if the player
              qualified).
  
  GameObj - the move method is not used (there is a new move method in Snake that moves the whole 
            snake) and the intercept method reflects coordinates using the grid positions rather 
            than the pixel positions.  Also contains a posInGrid and direction field to keep track
            of its position in the grid of points.
            
  Grid - formats the board into a 2D array with rows and columns so that each Point is a
         row-col coordinate which represents a position that the snake can move to.
         
  MyTests - JUnit testing for collision conditions, edge cases such as adding SnakeObj when the Food
            is at a corner and making sure each of the Point coordinates of each of the GameObj
            is updated.
            
  Snake - contains a list of SnakeObj and allows for a method to add another SnakeObj whenever the
          snake collides with the food and also has a method to move every SnakeObj in the snake.
  
  SnakeObj - contains its own draw method to draw the SnakeObj and takes in a Point so that it knows
             its position in the grid.

- Revisit your proposal document. What components of your plan did you end up
  keeping? What did you have to change? Why?
  I ended keeping everything in my proposal document, but I added a 2D array to create a grid to
  keep track of the snake and food's positions in the grid.  

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  Implementing the high scores and the grid took a while to implement.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  There is a good separation of functionality and there are setter and getter methods to ensure
  private state encapsulation.  If I were to refactor, I would use a high score manager class to 
  deal with the high scores.


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  None.

