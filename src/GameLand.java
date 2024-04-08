//Game Example
//Lockwood Version 2023-24
// Learning goals:
// Make a Hero with the ability to jump
//This code also has an example of an array
/**FOR GRAVITY (or acceleration)
 * STEP 0) go to your HERO CLASS and make all of your xpos, ypos, dx and dy
 * doubles instead of ints
 * STEP 0.5) IN HERO CLASS you also have to make cast these as (int) for all the rectangles
 * AND you have to cast positions as (int)s for the image of your hero IN GAMELAND CLASS
 * STEP 1) IN HERO CLASS add GRAVITY and isJumping variables to your hero class
 * STEP 2) IN GAMELAND CLASS connect isJumping boolean to a keyCode in your KeyPressed() and KeyReleased()
 * STEP 3) IN HERO CLASS update your move() method to use the change in dy
 * */

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
//*******************************************************************************
// Class Definition
public class GameLand implements Runnable, KeyListener {
    //Variable Declaration Section

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    //Declare the objects used in the program below
    public Hero astro;

    //declare an array:
    public Obstacle[] rocks;

    //declare images:
    public Image astroPic;
    public Image backgroundPic;
    public Image rockPic;

    // Main method definition: PSVM
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        GameLand ex = new GameLand();   //creates a new instance of the game and tells GameLand() method to run
        new Thread(ex).start();       //creates a thread & starts up the code in the run( ) method
    }

    // Constructor Method
    // This has no return type and has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public GameLand() {
        setUpGraphics(); //this calls the setUpGraphics() method

        //create (construct) the objects needed for the game below

        astro = new Hero(400,500, 0, 0);

        //construct my array of meteors
        rocks = new Obstacle[28];
        for(int i=0;i< rocks.length;i=i+1){
            int randX = (int)(Math.random()*1000);
            int randY = (int)(Math.random()*700);
            rocks[i] = new Obstacle(randX,randY,2,3);
        }

        //for each object or array of objects that have a pictures, load in images as well
        astroPic= Toolkit.getDefaultToolkit().getImage("astroPic.png");
        rockPic = Toolkit.getDefaultToolkit().getImage("rock.png");
        backgroundPic = Toolkit.getDefaultToolkit().getImage( "spacePic.png");

    }// GameLand()

//*******************************************************************************
//User Method Section

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever using a while loop
        while (true) {
            moveThings();  //move all the game objects
            collisions();
            render();  // paint the graphics
            pause(20); // sleep for 20 ms
        }
    }

    public void moveThings() {
        /**STEP 2 call move for your hero **/
        astro.move();
        for(int i=0;i< rocks.length;i=i+1) {
            rocks[i].bouncingMove();
        }
    }

    public void collisions(){

    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        //draw background
        g.drawImage(backgroundPic, 0, 0, WIDTH+120, HEIGHT, null);

        //draw the image of your objects below:
        /**STEP 0.5 cast xpos and ypos as integers **/
        g.drawImage(astroPic, (int)astro.xpos, (int)astro.ypos, astro.width,astro.height, null);

        for(int i=0;i< rocks.length;i=i+1) {
            g.drawImage(rockPic, rocks[i].xpos, rocks[i].ypos, rocks[i].width,rocks[i].height, null);
        }
        //dispose the images each time(this allows for the illusion of movement).
        g.dispose();

        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Game Land");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);
        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //probably will stay empty, but we can't delete it
    }

     /**STEP 2) tie isJumping boolean to a keyCode in your KeyPressed() and KeyReleased()
      * here, the here is using spacebar to jump */
    @Override
    public void keyPressed(KeyEvent e) {
        char key =e.getKeyChar();
        int keyCode=e.getKeyCode();
        System.out.println("Key: "+ key+ ", KeyCode: "+ keyCode);
        if(keyCode==32){// spacebar is 32 // up movement
            astro.isJumping=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key =e.getKeyChar();
        int keyCode=e.getKeyCode();
        if(keyCode==32){// spacebar is 32 // up movement
           astro.isJumping=false;
        }
    }
}