package me.ovara.function;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class PlaceholderHandler {

    public enum Direction {
        CLOCKWISE, COUNTERCLOCKWISE
    }

    private List<BlockFace> sides = Arrays.asList(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST);

    public String convertPlaceHolders(Player player, String blockdata) {

        if (!(blockdata.contains("[") && blockdata.contains("]"))) {
            return blockdata;
        }

        String blockDataSubString = blockdata.substring(blockdata.indexOf("[") + 1, blockdata.lastIndexOf("]"));
        String[] states = blockDataSubString.split(",");
        List<String> dataList = new ArrayList(Arrays.asList(states));
        List<String> newData = new ArrayList<>();
        for (String state : dataList) {
            if (state.equals("facing=%facing%")) {
                newData.add(state.replace("facing=%facing%","facing=" + player.getFacing().getOppositeFace().toString().toLowerCase()));
            } else if (state.equals("axis=%axis%")) {
                BlockFace dir = player.getFacing();
                if (dir.equals(BlockFace.NORTH) || dir.equals(BlockFace.SOUTH)) {
                    newData.add(state.replace("axis=%axis%", "axis=z"));
                } else if (dir.equals(BlockFace.EAST) || dir.equals(BlockFace.WEST)) {
                    newData.add(state.replace("axis=%axis%", "axis=x"));
                }
            } else if (state.startsWith("%enabled_sides:") && state.endsWith("%")) {
                String sidesString = state.substring(state.indexOf("%enabled_sides:") + "%enabled_sides:".length(), state.lastIndexOf("%"));
                String[] sides_input = sidesString.split(";");
                for (String s : sides_input) {
                    switch (s.toLowerCase()) {
                        case "left":
                            newData.add(rotateDirection(player.getFacing(), Direction.COUNTERCLOCKWISE).toString().toLowerCase() + "=true");
                            break;
                        case "right":
                            newData.add(rotateDirection(player.getFacing(), Direction.CLOCKWISE).toString().toLowerCase() + "=true");
                            break;
                        case "front":
                            newData.add(player.getFacing().getOppositeFace().toString().toLowerCase() + "=true");
                            break;
                        case "back":
                            newData.add(player.getFacing().toString().toLowerCase() + "=true");
                            break;
                    }
                }
            } else {
                newData.add(state);
            }
        }

        blockdata = blockdata.replace("[" + blockDataSubString + "]", Arrays.toString(newData.toArray()).replace(" ", ""));

        return blockdata;
    }


    private BlockFace rotateDirection(BlockFace face, Direction direction) {

        if (direction.equals(Direction.CLOCKWISE)) {
            if (sides.indexOf(face) + 1 > sides.size() - 1) {
                return sides.get(0);
            } else {
                return sides.get(sides.indexOf(face) + 1);
            }
        } else if (direction.equals(Direction.COUNTERCLOCKWISE)) {
            if (sides.indexOf(face) - 1 < 0) {
                return sides.get(3);
            } else {
                return sides.get(sides.indexOf(face) -1);
            }
        } else {
            return BlockFace.NORTH;
        }
    }

}
