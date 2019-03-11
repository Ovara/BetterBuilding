package me.ovara.function.visualisation;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class VisualisationManager {

    private static HashMap<Player, VisualisationObject> playerVisInProgress = new HashMap<>();

    public static void newVis(Player player, VisualisationObject visObject) {
        playerVisInProgress.remove(player);
        playerVisInProgress.put(player, visObject);
    }

    public static Boolean containsPlayer(Player player) {
        return playerVisInProgress.containsKey(player);
    }

    public static void removePlayer(Player player) {
        playerVisInProgress.remove(player);
    }

    public static void addPos(Player player, Location pos) {
        VisualisationObject visObject = playerVisInProgress.get(player);
        if (visObject.getPosition1() == null) {
            visObject.setPosition1(pos);
            visObject.checkPositions();
            return;
        }
        if (visObject.getPosition2() == null) {
            visObject.setPosition2(pos);
            visObject.checkPositions();
            return;
        }
    }
}
