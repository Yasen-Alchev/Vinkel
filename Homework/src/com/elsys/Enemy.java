package com.elsys;

public interface Enemy extends GameObject {
    boolean takeDmg(int damage);

    void dealDmg(Player player);
}
