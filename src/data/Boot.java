package data;

import collisions.CollisionBox;
import entities.Entity;
import entities.Player;
import helpers.Clock;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;
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

        Player player = new Player(LoadTexture("boston terrier"),640,480,64,64,3);
        Creator creator = new Creator(entities, grid);

        while (!Display.isCloseRequested()) {
            Clock.update();


            grid.Draw();
            entities.Draw();
            player.Draw();
            //testing collisions
            for (Entity entity: entities.getEntityList()){
                if(entity.hasCollisions()){
                    player.checkCollisions(entity.getCollisions());
                }
            }

            player.Update();
            creator.CheckInputs();

           // tree.Draw();
           // tree.Update();

            Display.update();
            Display.sync(60);

        }//grid.load("map");
        grid = creator.getMap();
        creator.getEntityGrid().save("entityMap");
        grid.save("map");
        //entities.save("entityMap");
    }

    public static void main(String[] args) {

        new Boot();

    }

}
