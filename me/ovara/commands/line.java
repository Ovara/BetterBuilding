package me.ovara.commands;

import me.ovara.function.visualisation.VisualisationManager;
import me.ovara.function.visualisation.line.LineObject;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class line implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!cmd.getName().equalsIgnoreCase("line")) {
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to execute this command!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("betterbuilding.vis.line")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invalid arguments!");
            return true;
        }

        int time;

        try {
            time = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Invalid time given!");
            return true;
        }

        LineObject lineObject = new LineObject(
                player,
                null,
                null,
                time);
        VisualisationManager.newVis(player, lineObject);

        return true;
    }
}
