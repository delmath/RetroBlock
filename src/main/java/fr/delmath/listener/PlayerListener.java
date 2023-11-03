package fr.delmath.listener;

import fr.delmath.RetroBlock;
import fr.delmath.utils.Wand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import fr.delmath.utils.Selection;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class PlayerListener implements Listener {
    private final long[] lastRightClick = new long[9];

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player pls = e.getPlayer();
        Wand.removeWand(pls);
    }

    @EventHandler
    public void onItemClick(PlayerInteractEvent e) {
        Player pls = e.getPlayer();
        if (Wand.isWand(e.getItem())) {
            int hotbarSlot = pls.getInventory().getHeldItemSlot();
            long currentTime = System.currentTimeMillis();

            // Délai en millisecondes (par exemple, 1 seconde)
            long clickDelay = 300;
            if (e.getAction() == Action.LEFT_CLICK_AIR) {
                e.setCancelled(true);
                Selection.selectFirstCorner(pls, pls.getLocation());
            } else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                e.setCancelled(true);
                Selection.selectFirstCorner(pls, Objects.requireNonNull(e.getClickedBlock()).getLocation());
            } else if (e.getAction() == Action.RIGHT_CLICK_AIR && (currentTime - lastRightClick[hotbarSlot] > clickDelay)) {
                e.setCancelled(true);
                Selection.selectSecondCorner(pls, pls.getLocation());
                lastRightClick[hotbarSlot] = currentTime;
            } else if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (currentTime - lastRightClick[hotbarSlot] > clickDelay)) {
                e.setCancelled(true);
                Selection.selectSecondCorner(pls, Objects.requireNonNull(e.getClickedBlock()).getLocation());
                lastRightClick[hotbarSlot] = currentTime;
            }
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent e) {
        Player pls = e.getPlayer();
        if (Wand.removeWand(pls)) {
            RetroBlock.getInstance().getAreas().removeIf(ar -> ar.getPlayer() == pls);
        }
    }
}
