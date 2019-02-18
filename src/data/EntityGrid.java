package data;

import entities.Entity;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.LoadTexture;

public class EntityGrid {

    public Entity[][] map;
    public int[][] saved;

    public EntityGrid(){
        map = new Entity[20][15];
        for (int i = 0; i < map.length; i++){
            for (int j = 0 ; j < map[i].length; j++){
                map[i][j] = new Entity(LoadTexture("tree"),5*64,4*64,64,64,0);;
            }
        }
    }

    public EntityGrid(String name){
        saved = load(name);
        map = new Entity[20][15];
        for(int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                switch(saved[j][i]){
                    case 0:
                        map[i][j] = new Entity(LoadTexture("empty"),i*64,j*64,64,64,0);
                        break;
                    case 1:
                        map[i][j] = new Entity(LoadTexture("tree"),i*64,j*64,64,64,0);
                        break;
                    case 2:
                        map[i][j] = new Entity(LoadTexture("rock"),i*64,j*64,64,64,0);
                        break;
                    case 3:
                        map[i][j] = new Entity(LoadTexture("bush"),i*64,j*64,64,64,0);
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
                    output.print(saved[i][j] + " ");
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
}
