package com.elsys;

public abstract class Enemy implements GameObject {

    int hp;
    int damage;

    Enemy(int hp, int damage)
    {
        this.hp = hp;
        this.damage = damage;
    }

    abstract int dropGold();
    abstract boolean takeDmg(int damage);

    abstract void dealDmg(Player player);
}
