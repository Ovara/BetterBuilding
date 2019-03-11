package me.ovara.function.visualisation;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class VisualisationObject {

    private Player owner;

    private Location position1;
    private Location position2;
    private int showtime;

    public VisualisationObject(Player owner, Location pos1, Location pos2, Integer showtime) {

        this.owner = owner;
        this.position1 = pos1;
        this.position2 = pos2;
        this.showtime = showtime;

    }

    public void show() { }

    public Boolean checkPositions() {
        if (this.position1 == null || this.position2 == null) {
            return false;
        }
        this.show();
        VisualisationManager.removePlayer(owner);
        return true;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Location getPosition1() {
        return position1;
    }

    public void setPosition1(Location position1) {
        this.position1 = position1;
    }

    public Location getPosition2() {
        return position2;
    }

    public void setPosition2(Location position2) {
        this.position2 = position2;
    }

    public int getShowtime() {
        return showtime;
    }

    public void setShowtime(int showtime) {
        this.showtime = showtime;
    }
}
