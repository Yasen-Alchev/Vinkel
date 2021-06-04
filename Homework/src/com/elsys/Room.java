package com.elsys;

import java.awt.*;
import java.util.TreeMap;

public class Room {
    TreeMap<Coordinates, GameObject> room;
    private final Coordinates[][] allCoordinates;
    Coordinates playerCoordinates;
    Player player;

    Room()
    {
        this.player = new Player();
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
        room.replace(playerCoordinates, player);
        room.replace(getCoordinate(8,7), new Orc());
        room.replace(getCoordinate(9,7), new Rock());
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

    void moveLeft()
    {
        if(playerCoordinates.getX() == 0)
            return;
        Coordinates newPosition = getCoordinate(playerCoordinates.getX() - 1, playerCoordinates.getY());
        if(!(room.get(newPosition) instanceof EmptySpace))
            return;
        room.replace(playerCoordinates, new EmptySpace());
        playerCoordinates = newPosition;
        room.replace(playerCoordinates, player);
    }

    void moveRight()
    {
        if(playerCoordinates.getX() == 14)
            return;
        Coordinates newPosition = getCoordinate(playerCoordinates.getX() + 1, playerCoordinates.getY());
        if(!(room.get(newPosition) instanceof EmptySpace))
            return;
        room.replace(playerCoordinates, new EmptySpace());
        playerCoordinates = newPosition;
        room.replace(playerCoordinates, player);
    }

    void moveUp()
    {
        if(playerCoordinates.getY() == 0)
            return;
        Coordinates newPosition = getCoordinate(playerCoordinates.getX(), playerCoordinates.getY() - 1);
        if(!(room.get(newPosition) instanceof EmptySpace))
            return;
        room.replace(playerCoordinates, new EmptySpace());
        playerCoordinates = newPosition;
        room.replace(playerCoordinates, player);
    }

    void moveDown()
    {
        if(playerCoordinates.getY() == 14)
            return;
        Coordinates newPosition = getCoordinate(playerCoordinates.getX(), playerCoordinates.getY() + 1);
        if(!(room.get(newPosition) instanceof EmptySpace))
            return;
        room.replace(playerCoordinates, new EmptySpace());
        playerCoordinates = newPosition;
        room.replace(playerCoordinates, player);
    }

    void attackUp()
    {
        if(playerCoordinates.getY() == 0)
            return;
        Coordinates enemyPosition = getCoordinate(playerCoordinates.getX(), playerCoordinates.getY() - 1);
        if(!(room.get(enemyPosition) instanceof Enemy))
            return;
        ((Enemy) room.get(enemyPosition)).dealDmg(player);
        if(((Enemy) room.get(enemyPosition)).takeDmg(player.ad))
        {
            room.replace(enemyPosition, new EmptySpace());
        }
    }

    void attackDown()
    {
        if(playerCoordinates.getY() == 14)
            return;
        Coordinates enemyPosition = getCoordinate(playerCoordinates.getX(), playerCoordinates.getY() + 1);
        if(!(room.get(enemyPosition) instanceof Enemy))
            return;
        ((Enemy) room.get(enemyPosition)).dealDmg(player);
        if(((Enemy) room.get(enemyPosition)).takeDmg(player.ad))
        {
            room.replace(enemyPosition, new EmptySpace());
        }
    }

    void attackLeft()
    {
        if(playerCoordinates.getX() == 0)
            return;
        Coordinates enemyPosition = getCoordinate(playerCoordinates.getX() - 1, playerCoordinates.getY());
        if(!(room.get(enemyPosition) instanceof Enemy))
            return;
        ((Enemy) room.get(enemyPosition)).dealDmg(player);
        if(((Enemy) room.get(enemyPosition)).takeDmg(player.ad))
        {
            room.replace(enemyPosition, new EmptySpace());
        }
    }

    void attackRight()
    {
        if(playerCoordinates.getX() == 14)
            return;
        Coordinates enemyPosition = getCoordinate(playerCoordinates.getX() + 1, playerCoordinates.getY());
        if(!(room.get(enemyPosition) instanceof Enemy))
            return;
        ((Enemy) room.get(enemyPosition)).dealDmg(player);
        if(((Enemy) room.get(enemyPosition)).takeDmg(player.ad))
        {
            room.replace(enemyPosition, new EmptySpace());
        }
    }
}
