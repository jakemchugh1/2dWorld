package collisions;



import org.lwjgl.util.vector.Vector2f;



public interface Collision {

    public void updatePosition(Vector2f position);

    public boolean checkColliding(float xIn, float yIn);



    public float getxMin();

    public float getxMax();

    public float getyMin();

    public float getyMax();

    public Vector2f getCenterOfMass();

    public float getxMinMagnitude();

    public float getxMaxMagnitude();

    public float getyMinMagnitude();

    public float getyMaxMagnitude();

    public boolean isCustom();

}