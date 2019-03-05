package entities;

import data.TileType;
import entities.Entity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.HashSet;
import java.util.Set;

import static helpers.Artist.*;

public class EntityManager {

    private HashSet<Entity> entityList;
    private TileType[] types;
    private int selectedType;
    private String[] entityType;
    private boolean tileOrEntity;
    private boolean godMode;
    private boolean pressed;

    public EntityManager(HashSet<Entity> entityList){
        types = new TileType[4];
        entityType = new String[4];
        this.entityList = entityList;
        this.types[0] = TileType.Empty;
        this.types[1] = TileType.Water;
        this.types[2] = TileType.Grass;
        this.types[3] = TileType.Wood;
        this.entityType[0] = "empty";
        this.entityType[1] = "tree";
        this.entityType[2] = "bush";
        this.entityType[3] = "rock";
        selectedType = 0;
        tileOrEntity = false;
        pressed = true;
    }

    public void SetEntity(){
        float mouseX = Mouse.getX();
        float mouseY = HEIGHT - Mouse.getY()-1;
        System.out.println("here");
        boolean exists = false;

        HashSet<Entity> toBeRemoved = new HashSet<>();

        for(Entity entity : entityList){
            if(entity.getX() < mouseX && (entity.getX() + entity.getWidth()) > mouseX && entity.getY() < mouseY && (entity.getY() + entity.getHeight()) > mouseY){
                toBeRemoved.add(entity);
                exists = true;
                System.out.println("entity changed");
            }else{
                System.out.println("mouseX " + (int)Math.floor(mouseX/64));
                System.out.println("mouseY " + (int)Math.floor(mouseY/64));
            }

        }
        for(Entity e : toBeRemoved){
            entityList.remove(e);
        }
        if (!exists){
            entityList.add(new Entity(LoadTexture("rock"), (int)Math.floor(mouseX/64)*64, (int)Math.floor(mouseY/64)*64, 64, 64, true));
        }
        else{
            //entityList.add(new Entity(LoadTexture("rock"), (int)Math.floor(mouseX/64), (int)Math.floor(mouseY/64), 64, 64, true));
        }


    }

    public void CheckInputs(){
        while(Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_TAB) tileOrEntity = !tileOrEntity;
            if (Keyboard.getEventKey() == Keyboard.KEY_0) selectedType = 0;
            if (Keyboard.getEventKey() == Keyboard.KEY_1) selectedType = 1;
            if (Keyboard.getEventKey() == Keyboard.KEY_2) selectedType = 2;
            if (Keyboard.getEventKey() == Keyboard.KEY_3) selectedType = 3;
        }
        if(Mouse.isButtonDown(1) && !pressed){
                SetEntity();
                pressed = true;
        }else{
            pressed = false;
        }

    }

    public HashSet<Entity> getEntitySet(){
        return entityList;
    }
}
