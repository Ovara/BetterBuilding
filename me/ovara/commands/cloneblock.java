package me.ovara.commands;

import com.destroystokyo.paper.block.TargetBlockInfo;
import me.ovara.function.BBItem;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cloneblock implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("cloneblock")) {
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to execute this command!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("betterbuilding.cloneblock")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length != 0) {
            player.sendMessage(ChatColor.RED + "Invalid arguments!");
            return true;
        }

        Block lookingAt = player.getTargetBlock(5, TargetBlockInfo.FluidMode.ALWAYS);
        if (lookingAt != null) {
            player.getInventory().addItem(new BBItem().get(lookingAt.getType(), lookingAt.getBlockData().getAsString()));
        } else player.sendMessage(ChatColor.RED + "You aren't looking at a block!");
        return true;
    }
}
