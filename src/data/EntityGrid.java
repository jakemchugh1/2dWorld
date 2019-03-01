package data;

import entities.Entity;
import entities.Rock;
import entities.Tree;
import org.newdawn.slick.opengl.Texture;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.LoadTexture;

public class EntityGrid {

    public Entity[][] map;
    public int[][] savedEntities;
    public HashSet<Entity> entityList;

    public EntityGrid(){
        entityList = new HashSet<>();
        map = new Entity[20][15];
        for (int i = 0; i < map.length; i++){
            for (int j = 0 ; j < map[i].length; j++){
                map[i][j] = new Entity(LoadTexture("tree"),i*64,j*64,64,64);
            }
        }
    }

    public EntityGrid(String name){
        entityList = new HashSet<>();
        savedEntities = load(name);
        map = new Entity[20][15];
        for(int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                Entity tempEnt;
                switch(savedEntities[j][i]){
                    case 0:
                        tempEnt =  new Entity(LoadTexture("empty"),i*64,j*64,64,64);
                        map[i][j] = tempEnt;
                        entityList.add(tempEnt);
                        break;
                    case 1:
                        tempEnt = new Tree(i*64,j*64,64,64);
                        map[i][j] = tempEnt;
                        entityList.add(tempEnt);
                        break;
                    case 2:
                        tempEnt = new Rock(i*64,j*64,64,64);
                        map[i][j] = tempEnt;
                        entityList.add(tempEnt);
                        break;
                    case 3:
                        tempEnt = new Entity(LoadTexture("bush"),i*64,j*64,64,64);
                        map[i][j] = tempEnt;
                        entityList.add(tempEnt);
                        break;
                }
            }
        }
    }

    /*public EntityGrid(String name){
        saved = load(name);
        map = new Tile[20][15];
        for(int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                switch(saved[j][i]){
                    case 0:
                        map[i][j] = new Tile(i*64, j*64, 64, 64, TileType.Empty);
                        break;
                    case 1:
                        map[i][j] = new Tile(i*64, j*64, 64, 64, TileType.Grass);
                        break;
                    case 2:
                        map[i][j] = new Tile(i*64, j*64, 64, 64, TileType.Water);
                        break;
                    case 3:
                        map[i][j] = new Tile(i*64, j*64, 64, 64, TileType.Wood);
                        break;
                }
            }
        }
    }*/
    public void Draw(){
        for(int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                Entity t = map[i][j];
                DrawQuadTex(t.getTexture(), t.getX(), t.getY(), t.getWidth(), t.getHeight());
            }
        }
    }

    public void save(String name){
        try {
            PrintStream output = new PrintStream("src/maps/"+name+".txt");
            for(int i = 0; i < 15; i++){
                for (int j = 0; j < 20; j++){
                    output.print(savedEntities[i][j] + " ");
                }output.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }System.out.println("File saved as "+name+".txt");

    }

    public int[][] load(String fileName){
        int[][] downloadedMap = {
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
        try {

            BufferedReader br = new BufferedReader(new FileReader("src/maps/"+fileName+".txt"));
            Scanner scanner = new Scanner(br);
            while(scanner.hasNextLine()) {
                for (int i = 0; i < 15; i++) {
                    for (int j = 0; j < 20; j++) {
                        try{
                            downloadedMap[i][j] = Integer.valueOf(scanner.next());
                        }catch (NoSuchElementException e){
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return downloadedMap;



    }

    public void setEntity(int xCoord, int yCoord, int type){
        savedEntities[yCoord][xCoord] = type;
        switch(savedEntities[yCoord][xCoord]){
            case 0:
                map[xCoord][yCoord] = new Entity(LoadTexture("empty"),xCoord*64,yCoord*64,64,64);
                break;
            case 1:
                map[xCoord][yCoord] = new Tree(xCoord*64,yCoord*64,64,64);
                break;
            case 2:
                map[xCoord][yCoord] = new Rock(xCoord*64,yCoord*64,64,64);
                break;
            case 3:
                map[xCoord][yCoord] = new Entity(LoadTexture("bush"),xCoord*64,yCoord*64,64,64);
                break;
        }

    }

    public Entity[][] getMap() {
        return map;
    }

    public HashSet<Entity> getEntityList() {
        return entityList;
    }
}
