package data;

import collisions.CollisionBox;
import entities.Entity;
import entities.Player;
import helpers.Clock;
import entities.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        HashSet<Entity> entitySet = new HashSet<>();
        entitySet.add(new Entity(LoadTexture("tree"), 128, 128, 64, 64, true));

        Player player = new Player(LoadTexture("boston terrier"),640,480,64,64,3);
        EntityManager manager = new EntityManager(entitySet);

        while (!Display.isCloseRequested()) {
            Clock.update();


            grid.Draw();
            //entities.Draw();
            player.Draw();
            //testing collisions
            for (Entity entity: entitySet){
                entity.Draw();
                if(entity.hasCollisions()){
                    player.checkCollisions(entity.getCollisions());
                }
            }

            player.Update();
            manager.CheckInputs();
            entitySet = manager.getEntitySet();

           // tree.Draw();
           // tree.Update();

            Display.update();
            Display.sync(60);

        }//grid.load("map");
        //grid.save("map");
        //entities.save("entityMap");
    }

    public static void main(String[] args) {

        new Boot();

    }

}
