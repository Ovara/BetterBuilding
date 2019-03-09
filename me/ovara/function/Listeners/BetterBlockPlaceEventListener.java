package me.ovara.function.Listeners;

import me.ovara.BetterBuildingMain;
import me.ovara.function.BlockSetter;
import me.ovara.function.PlaceholderHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BetterBlockPlaceEventListener implements Listener {

    private BetterBuildingMain plugin;

    public BetterBlockPlaceEventListener() {
        plugin = BetterBuildingMain.getPlugin(BetterBuildingMain.class);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) { return; }

        Block blockPlacedOn = event.getClickedBlock().getRelative(event.getBlockFace());
        if (!event.getHand().equals(EquipmentSlot.HAND)) { return; }

        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType().equals(Material.AIR)) { return; }

        ItemMeta meta = item.getItemMeta();
        if (!meta.hasLore()) { return; }
        List<String> lore = meta.getLore();
        if (lore.size() != 2) { return; }
        if (lore.get(1).equals(ChatColor.DARK_GRAY + plugin.getDescription().getVersion())) {
            String blockdataString = new PlaceholderHandler().convertPlaceHolders(player, lore.get(0));
            BlockData blockToPlace = Bukkit.createBlockData(blockdataString);
            new BlockSetter().setBlock(blockPlacedOn, blockToPlace);
        }
    }
}
