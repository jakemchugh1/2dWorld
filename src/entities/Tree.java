package entities;

import collisions.CollisionBox;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.LoadTexture;

public class Tree extends Entity {

    private CollisionBox collisions;

    public Tree(float x, float y, int width, int height){
        super(LoadTexture("tree"), x, y, width, height, true);

        collisions = new CollisionBox(64, super.getPosition());

    }

    public CollisionBox getCollisions(){
        return collisions;
    }
}
