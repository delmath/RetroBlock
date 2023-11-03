package fr.delmath.utils;

import fr.delmath.RetroBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Wand {

    public static void giveWand(Player player){
        int emptySlot = -1;
        for (int x = 0; x < 9 && emptySlot == -1; x++) {
            if (player.getInventory().getItem(x) == null || player.getInventory().getItem(x).getType() == Material.AIR) {
                emptySlot = x;
            }
        }
        if (emptySlot != -1) {
            List<String> lore = new ArrayList<>();
            lore.add("§aSelected area:");
            lore.add("§a  -> corner 1: §c§gnot selected");
            lore.add("§a  -> corner 2: §c§gnot selected");
            List<ItemFlag> itemFlags = new ArrayList<>();
            itemFlags.add(ItemFlag.HIDE_ATTRIBUTES);
            itemFlags.add(ItemFlag.HIDE_UNBREAKABLE);
            ItemStack wand = ItemMaker.itemMaker(Material.GOLDEN_AXE, "Area Selector", lore, itemFlags);
            player.getInventory().setItem(emptySlot, wand);
            player.sendMessage("§aYour wand has been successfully given.");
            RetroBlock.getInstance().getAreas().add(new area(player, new Location(null, 0, 0, 0), new Location(null, 0, 0, 0)));
        } else player.sendMessage("§cYour hotbar is full. Clear space to add the wand.");
    }

    public static boolean removeWand(Player player) {
        Inventory inventory = player.getInventory();

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && item.getType() == Material.GOLDEN_AXE && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("Area Selector")) {
                inventory.setItem(i, null);
                return true;
            }
        }

        return false;
    }

    public static void updateWand(Player player) {
        area plsAr = null;
        for (area ar : RetroBlock.getInstance().getAreas()) {
            if (ar.getPlayer() == player) {
                plsAr = ar;
                break;
            }
        }
        List<String> lore = new ArrayList<>();
        lore.add("§aSelected area:");
        if(plsAr.getFirstCorner().getWorld() != null)
            lore.add("§aCorner 1 selected : §ax:§6" + plsAr.getFirstCorner().getX() + " §ay:§6" + plsAr.getFirstCorner().getY() + " §az:§6" + plsAr.getFirstCorner().getZ());
        else
            lore.add("§a  -> corner 1: §c§gnot selected");
        if(plsAr.getSecondCorner().getWorld() != null)
            lore.add("§aCorner 1 selected : §ax:§6" + plsAr.getSecondCorner().getX() + " §ay:§6" + plsAr.getSecondCorner().getY() + " §az:§6" + plsAr.getSecondCorner().getZ());
        else
            lore.add("§a  -> corner 2: §c§gnot selected");
        List<ItemFlag> itemFlags = new ArrayList<>();
        itemFlags.add(ItemFlag.HIDE_ATTRIBUTES);
        itemFlags.add(ItemFlag.HIDE_UNBREAKABLE);
        ItemStack wand = ItemMaker.itemMaker(Material.GOLDEN_AXE, "Area Selector", lore, itemFlags);

        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack item = player.getInventory().getItem(i);
            if (item != null && item.getType() == Material.GOLDEN_AXE && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("Area Selector")) {
                player.getInventory().setItem(i, null);
                break;
            }
        }

        int emptySlot = -1;
        for (int x = 0; x < 9 && emptySlot == -1; x++) {
            if (player.getInventory().getItem(x) == null || player.getInventory().getItem(x).getType() == Material.AIR) {
                emptySlot = x;
            }
        }

        player.getInventory().setItem(emptySlot, wand);
    }

    public static boolean isWand(ItemStack item)
    {
        return (item != null && item.getType() == Material.GOLDEN_AXE && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("Area Selector"));
    }
}
