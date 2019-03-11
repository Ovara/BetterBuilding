package me.ovara.function;

import me.ovara.BetterBuildingMain;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class BBItem {

    private BetterBuildingMain plugin;

    public BBItem() {
        plugin = BetterBuildingMain.getPlugin(BetterBuildingMain.class);
    }

    public ItemStack get(Material mat, String blockData) {
        ItemStack item;

        Material material = mat;

        item = new ItemStack(mat, 1);
        if (!material.isItem() || (material.equals(Material.AIR))) {
            item.setType(Material.STRUCTURE_VOID);
        }
        ItemMeta meta = item.getItemMeta();
        String[] lore = {
                blockData,
                ChatColor.DARK_GRAY + plugin.getDescription().getVersion()
        };
        meta.setLore(Arrays.asList(lore));
        meta.setDisplayName(ChatColor.RESET + mat.toString());
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
}
