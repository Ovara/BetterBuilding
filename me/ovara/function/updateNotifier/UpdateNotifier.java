package me.ovara.function.updateNotifier;

import me.ovara.BetterBuildingMain;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateNotifier implements Listener {

    private BetterBuildingMain plugin = BetterBuildingMain.getPlugin(BetterBuildingMain.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp() && !CheckUpdate.isUpToDate && plugin.getConfig().getBoolean("check-for-update")) {
            TextComponent updateMessage = new TextComponent(ChatColor.RED + "New update for BetterBuilding is available!");
            updateMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Go to the BetterBuilding spigot page.").create()));
            updateMessage.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/better-building.65475/"));
            player.sendMessage(ChatColor.RED + "New update for BetterBuilding available!");
        }
    }
}
