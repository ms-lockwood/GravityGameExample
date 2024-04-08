import java.awt.*;

public class Hero {
    //variable declaration section
    public String name;                //holds the name of the hero
    /**STEP 0 make xpos, ypos, dx and dy doubles  **/
    public double xpos;                //the x position
    public double ypos;                //the y position
    public double dx;                    //the speed of the hero in the x direction
    public double dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle rec;
    public Image pic;

    /**STEP 1 add the two following variables for GRAVITY and isJumping*/
    // Gravity components
    public double GRAVITY = 0.1; // You can adjust this value as needed
    public boolean isJumping=false;

    /**STEP 0 make xpos, ypos, dx and dy paramaters doubles  **/
    public Hero(double pXpos, double pYpos, double pDx, double pDy){
        xpos=pXpos;
        ypos=pYpos;
        dx=pDx;
        dy=pDy;
        width=80;
        height=80;
        isAlive=true;

        /**STEP 0.5 cast xpos and ypos as integers **/
        rec = new Rectangle((int)xpos, (int)ypos, width,height);

    }
    public Hero(){
        xpos=100;
        ypos=200;
        dx=2;
        dy=3;
        width=60;
        height=80;
        isAlive=true;
    }
    /**
     * * STEP 3) IN HERO CLASS update your move() method to use the change in dy
     * due to gravity. Note: to move down dy is positive. So gravity would be positive.
     */
    public void move(){//this is the user control jumping move method
        // Apply gravity
        dy = dy + GRAVITY;

        // Check if jump is pressed and the hero is on the ground
        if (isJumping && (ypos + height) >= 700) {
            dy = -5; // Adjust the jump height as needed
        }
        //Check if you aren't jumping. Then when you hit the ground, stop falling due to gravity.
        if (isJumping==false && (ypos + height) >= 700){
            dy=0;
        }
        // Update position
        xpos=xpos+ dx;
        ypos= ypos+ dy;

        /**STEP 0.5 cast xpos and ypos as integers **/
        rec = new Rectangle((int)xpos, (int)ypos, width,height);

    }

    //make a printInfo() method
    public void printInfo(){
        //System.out.println("X position: " + xpos);
        //OR
        System.out.println("(x,y): ("+xpos+", "+ypos+")" );
        System.out.println("x speed: "+ dx);
        System.out.println("y speed: "+ dy);
        System.out.println("width: "+ width);
        System.out.println("height: "+height);
        System.out.println("isAlive: "+ isAlive);
    }




}