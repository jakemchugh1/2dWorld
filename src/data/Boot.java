package data;

import entities.Entity;
import helpers.Clock;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import static data.TileType.*;
import static helpers.Artist.*;
import static org.lwjgl.opengl.GL11.*;

public class Boot {

    public int DisplayHeight;
    public int DisplayWidth;

    public Boot(){

        BeginSession();

        int[][] map = {
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

        TileGrid grid = new TileGrid("map");
        EntityGrid entities = new EntityGrid("entityMap");

        Entity e = new Entity(LoadTexture("player"),640,480,64,64,3);
        //Entity tree = new Entity(LoadTexture("tree"),5*64,4*64,64,64,0);

        while (!Display.isCloseRequested()) {
            Clock.update();


            grid.Draw();
            entities.Draw();
            e.Draw();
            e.Update();

           // tree.Draw();
           // tree.Update();

            Display.update();
            Display.sync(60);

        }//grid.load("map");
       // grid.save("map");
    }

    public static void main(String[] args) {

        new Boot();

    }

}
