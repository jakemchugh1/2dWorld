package entities;

import data.Tile;
import org.newdawn.slick.opengl.Texture;
import org.lwjgl.input.Mouse;

import static helpers.Artist.DrawQuadTex;
import static helpers.Clock.Delta;

public class Entity {
    private int width, height;

    private float speed, x, y;
    private float mouseX, mouseY;
    private float tolerance;

    Texture texture;
    private Tile startTile;
    private boolean first = true;
    private boolean movement = false;

    public Entity(Texture texture, float x, float y, int width, int height, float speed){
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        tolerance = 1.5f;

    }
    public void Draw(){
        DrawQuadTex(texture, x, y, width, height);
    }

    public void Update(){


        if(first){
            first = false;
        }else{
            if(Mouse.isButtonDown(0))Location();
            if(movement)Move();
        }
    }
    private void Move(){
        if((mouseX-tolerance<x)&&(x<mouseX+tolerance)&&(mouseY-tolerance<y)&&(y<mouseY+tolerance)) movement = false;
        else {
            float adj = mouseX - x;
            float opp = mouseY - y;
            double magnitude = Math.sqrt(((adj * adj) + (opp * opp)));
            x = (float) (x + (adj / magnitude) * speed);
            y = (float) (y + (opp / magnitude) * speed);
        }
    }
    private void Location(){
        movement = true;
        mouseX = Mouse.getX()-32;
        mouseY = 960-Mouse.getY()-32;
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
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

    public float getTolerance() {
        return tolerance;
    }

    public void setTolerance(float tolerance) {
        this.tolerance = tolerance;
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

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isMovement() {
        return movement;
    }

    public void setMovement(boolean movement) {
        this.movement = movement;
    }
}
