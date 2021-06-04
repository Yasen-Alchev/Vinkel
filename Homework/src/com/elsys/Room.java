package com.elsys;

import java.awt.*;
import java.util.Random;
import java.util.TreeMap;

public class Room {
    TreeMap<Coordinates, GameObject> room;
    private final Coordinates[][] allCoordinates;
    Coordinates playerCoordinates;
    Player player;
    Random rand = new Random();

    Room(Main game)
    {
        this.player = new Player(game);
        this.room = new TreeMap<>();
        this.allCoordinates = new Coordinates[15][15];
        for(int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 15; j++)
            {
                allCoordinates[i][j] = new Coordinates(i, j);
            }
        }
        playerCoordinates = new Coordinates(0, 7);
    }

    Coordinates getCoordinate(int x, int y)
    {
        return allCoordinates[x][y];
    }

    void dropHealth(int count){
        drop(count, new HealthUp(this.player));
    }
    void dropArmor(int count){
        drop(count, new ArmorUp(this.player));
    }

    void drop(int count, GameObject object)
    {
        for(int i = 0; i < count; ++i)
        {
            int randX = rand.nextInt(15);
            int randY = rand.nextInt(15);
            while (!(room.get(new Coordinates(randX, randY)) instanceof EmptySpace)){
                randX = rand.nextInt(15);
                randY = rand.nextInt(15);
            }
            room.put(this.getCoordinate(randX, randY), object);
        }
    }

    void addObject(int bound, GameObject object){
        for(int i = 0; i <= rand.nextInt(bound); i++) {
            int rand_x_stones = rand.nextInt(14);
            int rand_y_stones = rand.nextInt(14);
            if (room.get(getCoordinate(rand_x_stones, rand_y_stones)) instanceof EmptySpace) {
                room.replace(getCoordinate(rand_x_stones, rand_y_stones), object);
            }
        }
    }

    void generateRoom()
    {
        for(int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 15; j++)
            {
                EmptySpace emptySpace = new EmptySpace();
                room.put(this.getCoordinate(i, j), emptySpace);
            }
        }

        addObject(5, new Rock());
        addObject(3, new Orc());
        addObject(2, new Warlock());
        room.replace(playerCoordinates, player);
    }

    void renderRoom(Graphics2D g)
    {
        for(int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 15; j++)
            {
                room.get(this.getCoordinate(i,j)).draw(g, this.getCoordinate(i, j));
            }
        }
    }

    void movement(int u, int n, int y, boolean isVertical) {
        if (playerCoordinates.getX() == u && !isVertical)
            return;
        if (playerCoordinates.getY() == u && isVertical)
            return;
        Coordinates newPosition = getCoordinate(playerCoordinates.getX() + n, playerCoordinates.getY() + y);
        if (!(room.get(newPosition) instanceof EmptySpace || room.get(newPosition) instanceof Item))
            return;
        if(room.get(newPosition) instanceof Item){
            ((Item) room.get(newPosition)).picked();
        }
        room.replace(playerCoordinates, new EmptySpace());
        playerCoordinates = newPosition;
        room.replace(playerCoordinates, player);
    }
    void moveLeft()
    {
        movement(0, -1, 0, false);
    }

    void moveRight()
    {
        movement(14, 1, 0, false);
    }

    void moveUp()
    {
        movement(0, 0, -1, true);
    }

    void moveDown()
    {
        movement(14, 0, 1, true);
    }

    void attacking(int u, int n, int y, boolean isVertical) {
        if(playerCoordinates.getY() == u && isVertical)
            return;
        if(playerCoordinates.getX() == u && !isVertical)
            return;
        Coordinates enemyPosition = getCoordinate(playerCoordinates.getX() + n, playerCoordinates.getY() + y);
        if(!(room.get(enemyPosition) instanceof Enemy))
            return;
        ((Enemy) room.get(enemyPosition)).dealDmg(player);
        if(((Enemy) room.get(enemyPosition)).takeDmg(player.ad))
        {
            room.replace(enemyPosition, new EmptySpace());
        }
    }

    void attackUp()
    {
        attacking(0, 0, -1, true);
    }

    void attackDown()
    {
        attacking(14, 0, 1, true);
    }

    void attackLeft()
    {
        attacking(0, -1, 0, false);
    }

    void attackRight()
    {
        attacking(14, 1, 0, false);
    }
}
