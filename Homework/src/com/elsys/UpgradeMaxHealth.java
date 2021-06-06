package com.elsys;

public class UpgradeMaxHealth implements Item{
    private final int upgrade = 20;
    Player player;

    UpgradeMaxHealth(Player player){
        this.player = player;
    }

    @Override
    public void picked() {
        player.upgradeMaxHealth(upgrade);
    }
}
