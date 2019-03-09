package me.ovara.function;

import org.bukkit.entity.Player;

public class PlaceholderHandler {

    public String convertPlaceHolders(Player player, String blockdata) {
        if (blockdata.contains("%facing%")) {
            return blockdata.replace("%facing%", player.getFacing().getOppositeFace().toString().toLowerCase());
        }
        if (blockdata.contains("%axis%")) {
            switch (player.getFacing()) {
                case NORTH:
                case SOUTH:
                    return  blockdata.replace("%axis%", "z");
                case EAST:
                case WEST:
                    return blockdata.replace("%axis%", "x");
            }
        }
        return blockdata;
    }
}
