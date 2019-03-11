package me.ovara.function.Listeners;

import me.ovara.BetterBuildingMain;
import me.ovara.function.BlockSetter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BlockUpdateBlocker implements Listener {

    private BetterBuildingMain plugin;

    public BlockUpdateBlocker() {
        plugin = BetterBuildingMain.getPlugin(BetterBuildingMain.class);
    }

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block placedBlock = event.getBlockPlaced();
        if (isBBitem(player)) {
            event.setCancelled(true);
            return;
        }
        if (player.hasPermission("betterbuilding.toggleupdates") && PlayerUpdateStateManager.getPlayerState(player)) {
            event.setCancelled(true);
            BlockSetter blockSetter = new BlockSetter();
            blockSetter.setBlock(placedBlock, placedBlock.getBlockData());
            return;
        }
    }

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block placedBlock = event.getBlock();
        if ((player.hasPermission("betterbuilding.toggleupdates") && PlayerUpdateStateManager.getPlayerState(player)) || isBBitem(player)) {
            event.setCancelled(true);
            BlockSetter blockSetter = new BlockSetter();
            blockSetter.setBlock(placedBlock, Bukkit.createBlockData(Material.AIR));
            return;
        }
    }

    public boolean isBBitem(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().equals(Material.AIR)) { return false; }
        ItemMeta meta = item.getItemMeta();
        if (meta.hasLore()) {
            List<String> lore = meta.getLore();
            return lore.get(1).equals(ChatColor.DARK_GRAY + plugin.getDescription().getVersion());
        }
        return false;
    }
}
