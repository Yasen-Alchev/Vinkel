package com.elsys;

class AttackDmgUp implements Item{
    Player player;

    private final int damage = 15;

    public AttackDmgUp(Player player) {
        this.player = player;
    }

    @Override
    public void picked() {
        player.addAd(damage);
    }
}
