package data;

import entities.Entity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static helpers.Artist.*;

public class Creator {

    private TileGrid map;
    private EntityGrid entityGrid;
    private TileType[] types;
    private int selectedType;
    private String[] entityType;
    private boolean tileOrEntity;

    public Creator(EntityGrid entities, TileGrid map){
        types = new TileType[4];
        entityType = new String[4];
        this.entityGrid = entities;
        this.map = map;
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
    }

    public void SetTile(){
        int mouseX = (int)(Math.floor(Mouse.getX() / 64));
        int mouseY = (int)(Math.floor((HEIGHT - Mouse.getY()-1) / 64));
        if(mouseX<14 || mouseY<19) {
            if(tileOrEntity)map.SetTile(mouseX, mouseY, selectedType);
            else {
                entityGrid.setEntity(mouseX, mouseY, selectedType);
            }

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
        if(Mouse.isButtonDown(1)){
            SetTile();
        }
    }

    public TileGrid getMap() {
        return map;
    }

    public EntityGrid getEntityGrid() {
        return entityGrid;
    }
}
