package me.ovara;

import me.ovara.commands.betterBlock;
import me.ovara.commands.cloneblock;
import me.ovara.function.Listeners.BetterBlockPlaceEventListener;
import me.ovara.function.Listeners.BlockUpdateBlocker;
import me.ovara.function.Listeners.PlayerUpdateStateManager;
import me.ovara.commands.toggleUpdates;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterBuildingMain extends JavaPlugin {

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
        registerEvents();
        registerCommands();
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new PlayerUpdateStateManager(), this);
        this.getServer().getPluginManager().registerEvents(new BlockUpdateBlocker(), this);
        this.getServer().getPluginManager().registerEvents(new BetterBlockPlaceEventListener(), this);
    }

    private void registerCommands() {
        this.getCommand("toggleupdates").setExecutor(new toggleUpdates());
        this.getCommand("betterblock").setExecutor(new betterBlock());
        this.getCommand("cloneblock").setExecutor(new cloneblock());
    }
}
