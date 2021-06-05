package com.elsys;

import java.awt.*;
import java.util.Random;
import java.util.TreeMap;

public class Room {
    Main game;
    TreeMap<Coordinates, GameObject> room;
    private final Coordinates[][] allCoordinates;
    Coordinates playerCoordinates;
    Player player;
    Quest quest;
    int totalEnemies;
    Random rand = new Random();

    Room(Main game)
    {
        this.player = new Player(game);
        this.room = new TreeMap<>();
        this.quest = new WarlockQuest();
        this.game = game;
        this.totalEnemies = 0;
        this.allCoordinates = new Coordinates[15][15];
        for(int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 15; j++)
            {
                allCoordinates[i][j] = new Coordinates(i, j);
            }
        }
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

    void addObject(GameObject object){
        int randX = rand.nextInt(14);
        int randY = rand.nextInt(14);
        if (room.get(getCoordinate(randX, randY)) instanceof EmptySpace) {
            room.replace(getCoordinate(randX, randY), object);
            if(object instanceof Enemy)
                totalEnemies++;
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

        dropHealth(rand.nextInt(2) + 1);
        dropArmor(rand.nextInt(2) + 1);
        for(int i = 0; i <= rand.nextInt(5); ++i)
            addObject(new Rock());
        for(int i = 0; i <= rand.nextInt(2); ++i)
            addObject(new Orc());
        for(int i = 0; i <= rand.nextInt(1); ++i)
            addObject(new Warlock());
        playerCoordinates = getCoordinate(0,7);
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
        quest.draw(g);
    }

    void movement(int border, int x, int y, boolean isVertical) {
        if (playerCoordinates.getX() == border && !isVertical)
            return;
        if (playerCoordinates.getY() == border && isVertical)
            return;
        Coordinates newPosition = getCoordinate(playerCoordinates.getX() + x, playerCoordinates.getY() + y);
        if (!(room.get(newPosition) instanceof Passable))
            return;
        if(room.get(newPosition) instanceof Door)
        {
            this.generateRoom();
            return;
        }
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

    void attacking(int border, int x, int y, boolean isVertical) {
        if(playerCoordinates.getY() == border && isVertical)
            return;
        if(playerCoordinates.getX() == border && !isVertical)
            return;
        Coordinates enemyPosition = getCoordinate(playerCoordinates.getX() + x, playerCoordinates.getY() + y);
        if(!(room.get(enemyPosition) instanceof Enemy))
            return;
        ((Enemy) room.get(enemyPosition)).dealDmg(player);
        if(((Enemy) room.get(enemyPosition)).takeDmg(player.ad))
        {

            if(quest.makeProgress((Enemy) room.get(enemyPosition)))
                game.stop(true);
            room.replace(enemyPosition, new EmptySpace());
            totalEnemies--;
            if(totalEnemies == 0)
            {
                room.replace(getCoordinate(14, 7), new Door());
            }
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
