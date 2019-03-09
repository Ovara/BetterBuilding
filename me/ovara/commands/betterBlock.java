package me.ovara.commands;

import me.ovara.function.BBItem;
import me.ovara.function.PlaceholderHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class betterBlock implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!cmd.getName().equalsIgnoreCase("betterblock")) {
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to execute this command!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("betterbuilding.betterblock")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invalid arguments!");
            return true;
        }


        String name = args[0];

        BlockData block;

        String blockString;

        try {
            blockString = new PlaceholderHandler().convertPlaceHolders(player, name);
            block = Bukkit.createBlockData(blockString);
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Invalid material or blockdata!");
            return true;
        }

        player.getInventory().addItem(new BBItem().get(block.getMaterial(), name));
        return true;

    }
}
