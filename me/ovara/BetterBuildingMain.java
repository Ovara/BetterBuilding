package me.ovara;

import me.ovara.commands.*;
import me.ovara.function.Listeners.BetterBlockPlaceEventListener;
import me.ovara.function.Listeners.BlockUpdateBlocker;
import me.ovara.function.Listeners.PlayerUpdateStateManager;
import me.ovara.function.updateNotifier.CheckUpdate;
import me.ovara.function.updateNotifier.UpdateNotifier;
import me.ovara.function.visualisation.VisualisationClickListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterBuildingMain extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        registerEvents();
        registerCommands();
        CheckUpdate.checkForUpdates();
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new PlayerUpdateStateManager(), this);
        this.getServer().getPluginManager().registerEvents(new BlockUpdateBlocker(), this);
        this.getServer().getPluginManager().registerEvents(new BetterBlockPlaceEventListener(), this);
        this.getServer().getPluginManager().registerEvents(new UpdateNotifier(), this);
        this.getServer().getPluginManager().registerEvents(new VisualisationClickListener(), this);
    }

    private void registerCommands() {
        this.getCommand("toggleupdates").setExecutor(new toggleUpdates());
        this.getCommand("betterblock").setExecutor(new betterBlock());
        this.getCommand("cloneblock").setExecutor(new cloneblock());
        this.getCommand("line").setExecutor(new line());
        this.getCommand("cuboid").setExecutor(new cuboid());
    }

}
