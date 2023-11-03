package fr.delmath.listener;

import fr.delmath.RetroBlock;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import fr.delmath.utils.history;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class BlockListener implements Listener {

    private static String datafile = RetroBlock.getInstance().getFiles().getBlockDataFile().toString();

    @EventHandler
    public static void onBlockBreak(BlockBreakEvent e){
        String cord = e.getBlock().getLocation().toString();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        Date date = new Date();
        String dates = formatter.format(date);

        if(!history.blockExists(datafile, e.getBlock().getLocation().toString()))
            history.addBlock(datafile, cord, dates, e.getBlock().getBlockData().getMaterial().toString(), Material.AIR.toString(), EntityType.PLAYER.toString(), e.getPlayer().getDisplayName());
        else history.addModification(datafile, cord, dates, e.getBlock().getBlockData().getMaterial().toString(), Material.AIR.toString(), EntityType.PLAYER.toString(), e.getPlayer().getDisplayName());
    }
}
