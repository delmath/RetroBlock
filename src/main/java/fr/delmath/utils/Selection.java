package fr.delmath.utils;

import fr.delmath.RetroBlock;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.delmath.utils.area;

public class Selection {

    public static void selectFirstCorner(Player player, Location loc){
        for (area ar : RetroBlock.getInstance().getAreas()) {
            if (ar.getPlayer() == player) {
                ar.setFirstCorner(loc);
                break;
            }
        }
        player.sendMessage("§aCorner 1 selected : §ax:§6" + loc.getX() + " §ay:§6" + loc.getY() + " §az:§6" + loc.getZ());
        Wand.updateWand(player);
    }
    public static void selectSecondCorner(Player player, Location loc){
        for (area ar : RetroBlock.getInstance().getAreas()) {
            if (ar.getPlayer() == player) {
                ar.setSecondCorner(loc);
                break;
            }
        }
        player.sendMessage("§aCorner 2 selected : §ax:§6" + loc.getX() + " §ay:§6" + loc.getY() + " §az:§6" + loc.getZ());
        Wand.updateWand(player);
    }

}
