package me.ovara.function.updateNotifier;

import me.ovara.BetterBuildingMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckUpdate implements Listener {

    public static Boolean isUpToDate = false;

    private static BetterBuildingMain plugin = BetterBuildingMain.getPlugin(BetterBuildingMain.class);

    public static void checkForUpdates() {
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
