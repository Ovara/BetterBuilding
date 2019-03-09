package me.ovara;

import com.sun.org.apache.xpath.internal.operations.Bool;
import me.ovara.commands.betterBlock;
import me.ovara.commands.cloneblock;
import me.ovara.function.Listeners.BetterBlockPlaceEventListener;
import me.ovara.function.Listeners.BlockUpdateBlocker;
import me.ovara.function.Listeners.PlayerUpdateStateManager;
import me.ovara.commands.toggleUpdates;
import me.ovara.function.Listeners.UpdateNotifierOPJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class BetterBuildingMain extends JavaPlugin {

    public Boolean isUpToDate = false;

     /*
     *************************************
     *                                   *
     *          BetterBuilding           *
     *            by:  Ovara             *
     *                                   *
     *     V: 1.0-RELEASE, 09.03.2019    *
     *                                   *
     *************************************
     */

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        registerEvents();
        registerCommands();
        checkUpdate();
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new PlayerUpdateStateManager(), this);
        this.getServer().getPluginManager().registerEvents(new BlockUpdateBlocker(), this);
        this.getServer().getPluginManager().registerEvents(new BetterBlockPlaceEventListener(), this);
        this.getServer().getPluginManager().registerEvents(new UpdateNotifierOPJoinListener(), this);
    }

    private void registerCommands() {
        this.getCommand("toggleupdates").setExecutor(new toggleUpdates());
        this.getCommand("betterblock").setExecutor(new betterBlock());
        this.getCommand("cloneblock").setExecutor(new cloneblock());
    }

    private void checkUpdate() {
        if (this.getConfig().getBoolean("check-for-update")) {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=65475").openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
                if (!version.equalsIgnoreCase(this.getDescription().getVersion())) {
                    Bukkit.getLogger().info(ChatColor.GRAY + "[" + this.getDescription().getPrefix() + "] " + ChatColor.RED + "New update available: " + version);
                } else {
                    Bukkit.getLogger().info(ChatColor.GRAY + "[" + this.getDescription().getPrefix() + "] " + ChatColor.GREEN + "Plugin is up to date!");
                    isUpToDate = true;
                }
            } catch (IOException e) {
                Bukkit.getLogger().info(ChatColor.GRAY + "[" + this.getDescription().getPrefix() + "] " + ChatColor.RED + "Something went wrong when checking for updates!");
            }
        } else {
            Bukkit.getLogger().info(ChatColor.GRAY + "[" + this.getDescription().getPrefix() + "] " + "Not checking for updates.");

        }
    }
}
