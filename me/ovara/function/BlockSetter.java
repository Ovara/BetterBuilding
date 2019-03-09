package me.ovara.function;

import me.ovara.BetterBuildingMain;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockSetter {

    private BetterBuildingMain plugin;

    public BlockSetter() {
        plugin = BetterBuildingMain.getPlugin(BetterBuildingMain.class);
    }

    public void setBlock(Block atBlock, BlockData toBlockData) {

        new BukkitRunnable() {
            @Override
            public void run() {
                BlockState state = atBlock.getState();
                state.setBlockData(toBlockData);
                state.update(true, false);
            }
        }.runTaskLater(plugin, 1);

    }

}
