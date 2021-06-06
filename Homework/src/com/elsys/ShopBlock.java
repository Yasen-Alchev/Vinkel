package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ShopBlock implements GameObject, Usable{
    public final static int itemsCount = 3;
    boolean[] shopItems = new boolean[itemsCount];
    int[] itemsPrices = new int[itemsCount];
    String itemNames[] = new String[itemsCount];
    Image img;
    ShopBlock(){
        Arrays.fill(shopItems, false);
        shopItems[0] = true;
        itemNames[0] = "Health: 2 coins";
        itemNames[1] = "Armor: 5 coins";
        itemNames[2] = "Wings(Flight Item): 15 coins";
        itemsPrices[0] = 2;
        itemsPrices[1] = 5;
        itemsPrices[2] = 15;
        try {
            this.img = ImageIO.read(new File("./resources/shop.png"));
            img = img.getScaledInstance(30, 30, Image.SCALE_FAST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Coordinates shopCoordinates = new Coordinates(7,7); //default coordinates

    @Override
    public void draw(Graphics2D g, Coordinates coordinates) {
        this.shopCoordinates = coordinates; //if the shop for some reason is moved somewhere else and update the default ones
        g.drawImage(img,coordinates.getX()*30,coordinates.getY()*30,null,null);

    }

    @Override
    public boolean isUsed(Room room)
    {
        for( int y = room.playerCoordinates.getY() - 1; y < room.playerCoordinates.getY() + 2; ++y)
        {
            if(y < 0 || y > 14)continue;
            for(int x = room.playerCoordinates.getX() - 1; x < room.playerCoordinates.getX() + 2; ++x)
            {
                if(x < 0 || x > 14)continue;
                if(this.shopCoordinates ==  room.getCoordinate(x, y)
                    && room.playerCoordinates !=  room.getCoordinate(x, y)){
                    return true;
                }
            }
            System.out.println();
        }
        return false;
    }

    public void drawMenu(Graphics2D g, int direction) //1 up 0 nothing -1 down
    {
        Font basic = new Font("TimesRoman", 1,  30);
        Font selected = new Font("TimesRoman", 1,  40);

        g.setColor(Color.white);
        g.setFont(basic);

        for (int i = 0; i < itemsCount; ++i){
            if(this.shopItems[i]){
                if(i + direction > itemsCount - 1){
                    shopItems[i] = false;
                    shopItems[0] = true;
                }
                else if(i + direction < 0){
                    shopItems[i] = false;
                    shopItems[itemsCount - 1] = true;
                }
                else
                {
                    shopItems[i] = false;
                    shopItems[i + direction] = true;
                }
                break;
            }
        }

        for (int i = 0; i < itemsCount; ++i){
            if(this.shopItems[i]){
                g.setFont(selected);
                g.setColor(Color.red);
                g.drawString(itemNames[i], Main.BoardX/2 - itemNames[i].length() * 7 - 5 * i, Main.BoardY/2 + 35 * i);
                g.setColor(Color.white);
                g.setFont(basic);
            }
            else {
                g.drawString(itemNames[i], Main.BoardX / 2 - itemNames[i].length() * 7 - 5 * i, Main.BoardY / 2 + 35 * i);
            }
        }
    }

    void buyItem(Player player){
        for (int i = 0; i < itemsCount; ++i){
            if(this.shopItems[i]){
                if(player.gold >= itemsPrices[i]) {
                    switch (i){
                        case 0:
                            if(player.health != 100) {
                                new HealthUp(player).picked();
                                player.gold -= itemsPrices[i];
                            }
                            break;
                        case 1:
                            new ArmorUp(player).picked();
                            player.gold -= itemsPrices[i];
                            break;
                        case 2:
                            player.gold -= itemsPrices[i];
                            break;
                    }
                }
            }
        }
    }
}
