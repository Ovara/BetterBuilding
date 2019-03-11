package me.ovara;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class UpdateNotifier implements Listener {

    private BetterBuildingMain plugin = BetterBuildingMain.getPlugin(BetterBuildingMain.class);

    private Boolean isUpToDate = false;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp() && !isUpToDate && plugin.getConfig().getBoolean("check-for-update")) {
            TextComponent updateMessage = new TextComponent(ChatColor.RED + "New update for BetterBuilding is available!");
            updateMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Go to the BetterBuilding spigot page.").create()));
            updateMessage.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/better-building.65475/"));
            player.sendMessage(ChatColor.RED + "New update for BetterBuilding available!");
        }
    }

    public void checkForUpdates() {
        if (plugin.getConfig().getBoolean("check-for-update")) {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=65475").openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
                if (!version.equalsIgnoreCase(plugin.getDescription().getVersion())) {
                    Bukkit.getLogger().info(ChatColor.GRAY + "[" + plugin.getDescription().getPrefix() + "] " + ChatColor.RED + "New update available: " + version);
                } else {
                    Bukkit.getLogger().info(ChatColor.GRAY + "[" + plugin.getDescription().getPrefix() + "] " + ChatColor.GREEN + "Plugin is up to date!");
                    isUpToDate = true;
                }
            } catch (IOException e) {
                Bukkit.getLogger().info(ChatColor.GRAY + "[" + plugin.getDescription().getPrefix() + "] " + ChatColor.RED + "Something went wrong when checking for updates!");
            }
        } else {
            Bukkit.getLogger().info(ChatColor.GRAY + "[" + plugin.getDescription().getPrefix() + "] " + "Not checking for updates.");

        }
    }
}
