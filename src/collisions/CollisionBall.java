package collisions;

import org.lwjgl.util.vector.Vector2f;

import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class CollisionBall implements Collision {

    //Current min and max values

    private float xMin;

    private float xMax;

    private float yMin;

    private float yMax;

    private float size;

    //min and max values for updating custom boxes

    private float xMinMagnitude;

    private float xMaxMagnitude;

    private float yMinMagnitude;

    private float yMaxMagnitude;



    private boolean isCustom;



    private Vector2f centerOfMass;



    public CollisionBall(float size, Vector2f position){

        this.size = size;

        centerOfMass = position;

        xMin = position.x-size;

        xMax = position.x+size;

        yMin = position.y-size;

        yMax = position.y+size;





        isCustom = false;

    }



    public CollisionBall(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax, Vector2f position){

        xMinMagnitude = xMin;

        xMaxMagnitude = xMin;

        yMinMagnitude = yMin;

        yMaxMagnitude = yMin;

        centerOfMass = position;

        xMin = position.x-xMinMagnitude;

        xMax = position.x+xMaxMagnitude;

        yMin = position.y-yMinMagnitude;

        yMax = position.y+yMaxMagnitude;



        isCustom = true;

    }



    public void updatePosition(Vector2f position){

        centerOfMass = position;

        if(isCustom){

            xMin = position.x-xMinMagnitude;

            xMax = position.x+xMaxMagnitude;

            yMin = position.y-yMinMagnitude;

            yMax = position.y+yMaxMagnitude;

        }else{

            xMin = position.x-size;

            xMax = position.x+size;

            yMin = position.y-size;

            yMax = position.y+size;

        }

    }



    public boolean checkColliding(float xIn, float yIn){

        double distance = sqrt(Math.pow((double)xIn,2) + Math.pow((double)yIn,2));

        if(distance < 64){

            return true;

        }

        else return false;

    }



    public float getxMin() {

        return xMin;

    }



    public float getxMax() {

        return xMax;

    }



    public float getyMin() {

        return yMin;

    }



    public float getyMax() {

        return yMax;

    }





    public Vector2f getCenterOfMass() {

        return centerOfMass;

    }

    public float getxMinMagnitude() {

        return xMinMagnitude;

    }



    public float getxMaxMagnitude() {

        return xMaxMagnitude;

    }



    public float getyMinMagnitude() {

        return yMinMagnitude;

    }



    public float getyMaxMagnitude() {

        return yMaxMagnitude;

    }



    public boolean isCustom() {

        return isCustom;

    }





}
