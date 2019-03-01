package data;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static helpers.Artist.*;

public class TileGrid {

    public Tile[][] map;
    public int[][] saved;

    public TileGrid(){
        map = new Tile[20][15];
        for (int i = 0; i < map.length; i++){
            for (int j = 0 ; j < map[i].length; j++){
                map[i][j] = new Tile(i*64, j*64, 64, 64, TileType.Empty);
            }
        }
    }

    public TileGrid(int[][] newMap){
        saved = newMap;
        map = new Tile[20][15];
        for(int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                switch(newMap[j][i]){
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
    }

    public TileGrid(String name){
        saved = load("map");
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
    }

    public void SetTile(int xCoord, int yCoord, int type){
        saved[yCoord][xCoord] = type;
        switch(saved[yCoord][xCoord]){
            case 0:
                map[xCoord][yCoord] = new Tile(xCoord*64, yCoord*64, 64, 64, TileType.Empty);
                break;
            case 1:
                map[xCoord][yCoord] = new Tile(xCoord*64, yCoord*64, 64, 64, TileType.Grass);
                break;
            case 2:
                map[xCoord][yCoord] = new Tile(xCoord*64, yCoord*64, 64, 64, TileType.Water);
                break;
            case 3:
                map[xCoord][yCoord] = new Tile(xCoord*64, yCoord*64, 64, 64, TileType.Wood);
                break;
        }
    }

    public Tile GetTile(int xCoord, int yCoord){
        return map[xCoord][yCoord];
    }

    public void Draw(){
        for(int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                Tile t = map[i][j];
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
        int[][] tempInt = {
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
                           // e.printStackTrace;
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
