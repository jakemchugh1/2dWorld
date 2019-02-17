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
}
