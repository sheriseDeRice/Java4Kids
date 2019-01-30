package com.example.sherisesinyeelam.java4kids.snakegame1;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    public static final int GameWidth = 28;
    public static final int GameHeight = 42;

    private List<Coordinate> walls = new ArrayList<>();
    private List<Coordinate> snake = new ArrayList<>();

    private Direction currentDirection = Direction.East;

    private GameState currentState = GameState.Running;

    public GameEngine(){

    }

    public void initGame(){
        AddSnake();
        AddWalls();
    }

    public void updateDirection (Direction direction){
        if(Math.abs(direction.ordinal() - currentDirection.ordinal()) %2 == 1){
            currentDirection = direction;
        }
    }

    public void update(){
        // update snake
        switch (currentDirection){
            case North:
                UpdateSnake(0,-1);
                break;
            case East:
                UpdateSnake(1,0);
                break;
            case South:
                UpdateSnake(0,1);
                break;
            case West:
                UpdateSnake(-1,0);
                break;
        }

        // check if hitting wall
        for(Coordinate w : walls){
            if(snake.get(0).equals(w)){
                currentState = GameState.Lost;
            }
        }
    }

    public TileType[][] getMap(){
        TileType[][] map = new TileType[GameWidth][GameHeight];

        for (int x = 0; x<GameWidth; x++){
            for(int y = 0 ; y<GameHeight; y++){
                map[x][y] = TileType.Nothing;
            }
        }

        for (Coordinate s : snake){
            map[s.getX()][s.getY()] = TileType.SnakeTail;
        }
        map[snake.get(0).getX()][snake.get(0).getY()] = TileType.SnakeHead;

        for(Coordinate wall: walls){
            map[wall.getX()][wall.getY()] = TileType.Wall;
        }

        return map;
    }

    private void UpdateSnake(int x, int y){
        for(int i = snake.size()-1 ; i>0; i--){
            snake.get(i).setX(snake.get(i-1).getX());
            snake.get(i).setY(snake.get(i-1).getY());
        }

        snake.get(0).setX(snake.get(0).getX() + x);
        snake.get(0).setY(snake.get(0).getY() + y);
    }

    private void AddSnake() {
        snake.clear();

        snake.add(new Coordinate(7,7));
        snake.add(new Coordinate(6,7));
        snake.add(new Coordinate(5,7));
        snake.add(new Coordinate(4,7));
        snake.add(new Coordinate(3,7));
        snake.add(new Coordinate(2,7));
    }

    private void AddWalls() {
        //Top and Bottom walls
        for (int x = 0; x < GameWidth; x++){
            walls.add(new Coordinate(x,0));
            walls.add(new Coordinate(x, GameHeight-1));
        }

        //Left and right walls
        for (int y = 0; y < GameHeight; y++){
            walls.add(new Coordinate(0,y));
            walls.add(new Coordinate(GameWidth-1, y));
        }

    }

    public GameState getCurrentGameState(){
        return currentState;
    }


}

// Marcell Elek (2016). Android Tutorial - Snake - Part 1 [online]. https://www.youtube.com/watch?v=bPlG7ra83lo [accessed 04/01/2019]
