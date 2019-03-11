package me.ovara.function.visualisation.line;

import me.ovara.BetterBuildingMain;
import me.ovara.function.visualisation.VisualisationObject;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class LineObject extends VisualisationObject {

    private List<Location> locs;

    private BetterBuildingMain plugin;

    public LineObject(Player owner, Location position1, Location position2, int showtime) {
        super(owner, position1, position2, (int) Math.floor(showtime*20));
        this.plugin = BetterBuildingMain.getPlugin(BetterBuildingMain.class);
    }

    @Override
    public void show() {
        locs = new ArrayList<>();
        double length = getPosition1().toVector().distance(getPosition2().toVector());
        for (int i = 0; i < length; i++) {
            Location pos_ = getPosition1().clone().add(getPosition2().toVector().subtract(getPosition1().toVector()).normalize().multiply(i));
            locs.add(pos_);
        }
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
}
