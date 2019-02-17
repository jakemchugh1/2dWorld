package data;

public enum TileType {

    Empty("tile",true),Grass("grass",true), Wood("wood",false), Water("water",false);

    String textureName;
    boolean passable;

    TileType(String textureName, boolean passable){
        this.textureName = textureName;
        this.passable = passable;
    }
}
