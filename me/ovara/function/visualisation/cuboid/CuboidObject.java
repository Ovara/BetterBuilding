package me.ovara.function.visualisation.cuboid;

import me.ovara.BetterBuildingMain;
import me.ovara.function.visualisation.VisualisationObject;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class CuboidObject extends VisualisationObject {

    private List<Location> locs;

    private BetterBuildingMain plugin;

    public CuboidObject(Player owner, Location position1, Location position2, int showtime) {
        super(owner, position1, position2, (int) Math.floor(showtime*20));
        this.plugin = BetterBuildingMain.getPlugin(BetterBuildingMain.class);
    }

    @Override
    public void show() {
        locs = getCorners();
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i >= getShowtime()) {
                    this.cancel();
                }
                for (Location loc : locs) {
                    if (!loc.getBlock().getType().isSolid()) {
                        if (i % 15 == 0) {
                            getOwner().spawnParticle(Particle.FLAME, loc, 1, 0.0, 0.0, 0.0, 0.001);
                        }
                    }
                }
                i++;
            }
        }.runTaskTimer(plugin, 1L, 1L);

    }

    public List<Location> getCorners() {
        if (getPosition1() == null || getPosition2() == null) {
            return null;
        }
        List<Location> corners = new ArrayList<>();

        int topBlockX = (getPosition1().getBlockX() < getPosition2().getBlockX() ? getPosition2().getBlockX() : getPosition1().getBlockX());
        int bottomBlockX = (getPosition1().getBlockX() > getPosition2().getBlockX() ? getPosition2().getBlockX() : getPosition1().getBlockX());

        int topBlockY = (getPosition1().getBlockY() < getPosition2().getBlockY() ? getPosition2().getBlockY() : getPosition1().getBlockY());
        int bottomBlockY = (getPosition1().getBlockY() > getPosition2().getBlockY() ? getPosition2().getBlockY() : getPosition1().getBlockY());

        int topBlockZ = (getPosition1().getBlockZ() < getPosition2().getBlockZ() ? getPosition2().getBlockZ() : getPosition1().getBlockZ());
        int bottomBlockZ = (getPosition1().getBlockZ() > getPosition2().getBlockZ() ? getPosition2().getBlockZ() : getPosition1().getBlockZ());

        for (int x = bottomBlockX; x <= topBlockX; x++) {
            for (int z = bottomBlockZ; z <= topBlockZ; z++) {
                for (int y = bottomBlockY; y <= topBlockY; y++) {
                    Block block = getPosition1().getWorld().getBlockAt(x, y, z);
                    Location blockLoc = block.getLocation();
                    if (plugin.getConfig().getBoolean("cuboidModeFull")) {
                        if (
                                (block.getLocation().getBlockX() == getPosition1().getBlockX() || block.getLocation().getBlockX() == getPosition2().getBlockX())
                                        || (block.getLocation().getBlockY() == getPosition1().getBlockY() || block.getLocation().getBlockY() == getPosition2().getBlockY())
                                        || (block.getLocation().getBlockZ() == getPosition1().getBlockZ() || block.getLocation().getBlockZ() == getPosition2().getBlockZ())

                        ) {
                            corners.add(block.getLocation().toCenterLocation());
                        }
                    } else {
                        if (
                                ((blockLoc.getBlockX() == topBlockX ||  blockLoc.getBlockX() == bottomBlockX)
                                        &&
                                        (blockLoc.getBlockY() == topBlockY ||  blockLoc.getBlockY() == bottomBlockY))
                                        || ((blockLoc.getBlockX() == topBlockX ||  blockLoc.getBlockX() == bottomBlockX)
                                        &&
                                        (blockLoc.getBlockZ() == topBlockZ ||  blockLoc.getBlockZ() == bottomBlockZ))
                                        || ((blockLoc.getBlockY() == topBlockY ||  blockLoc.getBlockY() == bottomBlockY)
                                        &&
                                        (blockLoc.getBlockZ() == topBlockZ ||  blockLoc.getBlockZ() == bottomBlockZ))
                        ) {
                            corners.add(block.getLocation().toCenterLocation());
                        }
                    }
                }
            }
        }
        return corners;
    }

}
