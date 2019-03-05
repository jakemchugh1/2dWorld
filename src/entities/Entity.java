package entities;

import collisions.Collision;
import collisions.CollisionBox;
import data.Tile;
import org.newdawn.slick.opengl.Texture;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.input.Mouse;

import static helpers.Artist.DrawQuadTex;

public class Entity {
    private int width, height;

    private float x, y;
    private float mouseX, mouseY;
    private Vector2f position;

    Texture texture;
    private Tile startTile;

    private boolean hasCollisions;

    private Collision collisions;

    public Entity(Texture texture, float x, float y, int width, int height, boolean hasCollisions){
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        position = new Vector2f(x,y);
        position.x = x+(width/2);
        position.y = y+(height/2);
        position = new Vector2f(x,y);
        this.hasCollisions = hasCollisions;
        collisions = new CollisionBox(width, position);



    }
    public Entity(Texture texture, float x, float y, int width, int height){
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        position = new Vector2f(x,y);
        position.x = x+(width/2);
        position.y = y+(height/2);
        hasCollisions = false;
        collisions = new CollisionBox(width, position);


    }
    public void Draw(){
        DrawQuadTex(texture, x, y, width, height);
    }



    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getMouseX() {
        return mouseX;
    }

    public void setMouseX(float mouseX) {
        this.mouseX = mouseX;
    }

    public float getMouseY() {
        return mouseY;
    }

    public void setMouseY(float mouseY) {
        this.mouseY = mouseY;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position){
        this.position = position;
    }

    public Collision getCollisions() {
        return collisions;
    }

    public void setCollisions(Collision collisions) {
        this.collisions = collisions;
    }

    public boolean hasCollisions() {
        return hasCollisions;
    }
}
