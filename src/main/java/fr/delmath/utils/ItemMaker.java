package fr.delmath.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemMaker {

    public static ItemStack itemMaker(Material mat, String displayName, List<String> lore, List<ItemFlag> itemFlags) {
        ItemStack i = new ItemStack(mat);
        ItemMeta im = i.getItemMeta();

        if (displayName != null) {
            im.setDisplayName(displayName);
        }

        if (lore != null) {
            im.setLore(lore);
        }

        if (itemFlags != null) {
            for (ItemFlag flag : itemFlags) {
                im.addItemFlags(flag);
            }
        }

        i.setItemMeta(im);

        return i;
    }

}
