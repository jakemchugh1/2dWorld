package entities;

import collisions.CollisionBall;
import collisions.CollisionBox;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.LoadTexture;

public class Rock extends Entity {

    private CollisionBox collisions;

    public Rock(float x, float y, int width, int height){
        super(LoadTexture("rock"), x, y, width, height, true);

        collisions = new CollisionBox(64, super.getPosition());

    }

    public CollisionBox getCollisions(){
        return collisions;
    }
}
