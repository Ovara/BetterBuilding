package me.ovara.function.visualisation;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class VisualisationClickListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!VisualisationManager.containsPlayer(player)) { return; }

        if ((event.getAction().equals(Action.LEFT_CLICK_BLOCK))) {
            event.setCancelled(true);
            Block clickedBlock = event.getClickedBlock();
            VisualisationManager.addPos(player, clickedBlock.getLocation().toCenterLocation());
            return;
        }

        if ((player.isSneaking() && event.getAction().equals(Action.LEFT_CLICK_AIR))) {
            event.setCancelled(true);
            VisualisationManager.addPos(player, player.getEyeLocation().toCenterLocation());
            return;
        }
    }
}
