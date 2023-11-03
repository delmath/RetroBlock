package fr.delmath.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class area {

    Player player;
    Location firstCorner;
    Location secondCorner;

    public area(Player player, Location firstCorner, Location secondCorner) {
        this.player = player;
        this.firstCorner = firstCorner;
        this.secondCorner = secondCorner;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getFirstCorner() {
        return firstCorner;
    }

    public void setFirstCorner(Location firstCorner) {
        this.firstCorner = firstCorner;
    }

    public Location getSecondCorner() {
        return secondCorner;
    }

    public void setSecondCorner(Location secondCorner) {
        this.secondCorner = secondCorner;
    }
}
