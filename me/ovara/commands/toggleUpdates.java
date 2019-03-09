package me.ovara.commands;

import me.ovara.function.Listeners.PlayerUpdateStateManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class toggleUpdates implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!cmd.getName().equalsIgnoreCase("toggleupdates")) {
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to execute this command!");
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("betterbuilding.toggleupdates")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length != 0) {
            player.sendMessage(ChatColor.RED + "This command doesn't need any arguments!");
            return true;
        }

        PlayerUpdateStateManager.togglePlayerState(player);
        player.sendMessage("Block updates set to " + !PlayerUpdateStateManager.getPlayerState(player));
        return true;
    }
}
