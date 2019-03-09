package me.ovara.function.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerUpdateStateManager implements Listener {

    private static HashMap<UUID, Boolean> updatesToggled = new HashMap<>();

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("betterbuilding.toggleupdates")) {
            updatesToggled.put(player.getUniqueId(), false);
        }
    }

    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (updatesToggled.containsKey(player.getUniqueId()) && player.hasPermission("betterbuilding.toggleupdates")) {
            updatesToggled.remove(player.getUniqueId());
        }
    }

    public static Boolean getPlayerState(Player player) {
        if (!updatesToggled.containsKey(player.getUniqueId())) {
            return false;
        }
        return updatesToggled.get(player.getUniqueId());
    }

    private static void setPlayerState(Player player, Boolean state) {
        updatesToggled.put(player.getUniqueId(), state);
    }

    public static void togglePlayerState(Player player) {
        if (!updatesToggled.containsKey(player.getUniqueId())) {
            setPlayerState(player, false);
        }
        updatesToggled.put(player.getUniqueId(), !updatesToggled.get(player.getUniqueId()));
    }

}
