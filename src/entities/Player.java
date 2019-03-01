package entities;

import collisions.Collision;
import collisions.CollisionBox;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.HEIGHT;

public class Player extends Entity{

    private float speed;
    private float tolerance;
    private float mouseX;
    private float mouseY;
    private float x;
    private float y;


    private boolean first;
    private boolean movement;
    private boolean isColliding;
    private boolean hasCollisions;

    public Player(Texture texture, float x, float y, int width, int height, float speed){
        super(texture, x, y, width, height);



        this.speed = speed;
        tolerance = 2f;
        first = true;
        this.x = x;
        this.y = y;


        isColliding = false;
        hasCollisions = true;

    }
    public void Update(){
        if(first){
            first = false;
        }else{
            if(Mouse.isButtonDown(0))Location();
            if(movement)Move();
        }
        Vector2f tempVec = new Vector2f(x,y);
        super.setPosition(tempVec);
        super.setCollisions(new CollisionBox(10, tempVec));
    }

    public boolean checkCollisions(Collision collision){



        if(!hasCollisions) {

            System.out.println("no collisions");

            return false;

        }

        else{



            isColliding = false;

            if(collision.checkColliding(super.getCollisions().getxMin(), super.getCollisions().getyMin())){

                isColliding = true;

            }

            if(isColliding){

                repel(collision);

            }



            return isColliding;

        }

    }

    public void repel(Collision collision){

        double[] distanceFromObject = new double[2];

        //calculating the distance from the center of mass of one object to another

        distanceFromObject[0] = super.getCollisions().getCenterOfMass().x-collision.getCenterOfMass().x;

        distanceFromObject[1] = super.getCollisions().getCenterOfMass().y-collision.getCenterOfMass().y;

        //finding the magnitude(actual distance) between the two center of masses

        double magnitude = Math.sqrt(Math.pow(distanceFromObject[0],2)+Math.pow(distanceFromObject[1],2));

        //x

        distanceFromObject[0] = (float) (distanceFromObject[0] / magnitude);

        //y

        distanceFromObject[1] = (float) (distanceFromObject[1] / magnitude);

        //moving player along the unit vector

        double repelStrength = speed + 0.0000001f;

        move(distanceFromObject[0]*repelStrength, distanceFromObject[1]*repelStrength);

    }


    private void Move(){
        if((mouseX-tolerance<x)&&(x<mouseX+tolerance)&&(mouseY-tolerance<y)&&(y<mouseY+tolerance)) movement = false;
        else {
            float adj = mouseX - x;
            float opp = mouseY - y;
            double magnitude = Math.sqrt(((adj * adj) + (opp * opp)));
            float moveX = (float) ((adj / magnitude) * speed);
            float moveY = (float)(opp / magnitude) * speed;
            x = (x + moveX);
            y = (y + moveY);

            super.setX(x);
            super.setY(y);

        }
    }

    private void move(double moveX, double moveY){
        x = (float) (x+moveX);
        y = (float) (y+moveY);
        super.setX((float)moveX);
        super.setY((float)moveY);
    }
    private void Location(){
        movement = true;
        mouseX = Mouse.getX()-32;
        mouseY = HEIGHT-Mouse.getY()-32;
    }
}
