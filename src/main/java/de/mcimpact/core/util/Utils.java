package de.mcimpact.core.util;

import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.world.MissileWarsLevelData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static File ROOT = Bukkit.getServer().getWorldContainer();
    public static Map<Player, List<Location>> BLOCKS = new HashMap<>();


    public static boolean isOnGround(NetPlayer netPlayer) {

        Player player = Bukkit.getPlayer(netPlayer.getUniqueId());

        return !player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.AIR);
    }

    public static String[] getSubdirectories(String path) {
        File file = new File(path);
        String[] directories = file.list((current, name) -> new File(current, name).isDirectory());
        return directories;
    }

    public static File[] stringArrayToFolders(String path, String[] folders) {

        File[] files = new File[folders.length];
        for (int i = 0; i < folders.length; i++) {
            files[i] = new File(path + "/" + folders[i]);
        }
        return files;
    }

    public static String getCoordsFromBlock(Block block) {
        return block.getLocation().getBlockX() + "," + block.getLocation().getBlockY() + ","
                + block.getLocation().getBlockZ();
    }

    public static void createWall(Player player, Material material, int distance) {

        MissileWarsLevelData data = MissileWars.GAME.getMissileWarsLevel().getData();
        int playerX = player.getLocation().getBlockX();
        int playerZ = player.getLocation().getBlockZ();
        int playerY = player.getLocation().getBlockY();


        if (playerX + distance >= data.getWallsX().getFirst()) {
            if (!BLOCKS.containsKey(player)) {
                BLOCKS.put(player, new ArrayList<>());
            }
            List<Location> circle = circle(player.getLocation(), distance, distance, false, true, 1);
            for (Location location : circle) {
                if (location.getX() == data.getWallsX().getFirst()) {
                    if (player.getEyeLocation().distance(location) <= distance) {
                        if (location.getBlock().getType() == Material.AIR) {
                            location.getBlock().setType(material);
                            BLOCKS.get(player).add(location);
                        }
                    }
                }
            }
            /*
            int i = 0;
            while (i <= distance) {
                if (playerX + i == data.getWallsX().getFirst()) {
                    Location loc = new Location(player.getWorld(), player.getLocation().getBlockX() + i, player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                    Block block = player.getWorld().getBlockAt(loc);
                    if (block.getType() == Material.AIR) {
                        BLOCKS.get(player).add(loc);
                        block.setType(material);
                    }
                    checkedblocks.add(loc);
                    neighboursx(player, material, distance, loc);
                    checkedblocks = new ArrayList<>();
                }
                i++;
            }
         */
        }
        if (playerX - distance <= data.getWallsX().getSecond()) {
            if (!BLOCKS.containsKey(player)) {
                BLOCKS.put(player, new ArrayList<>());
            }
            List<Location> circle = circle(player.getLocation(), distance, distance, false, true, 0);
            for (Location location : circle) {
                if (location.getX() == data.getWallsX().getSecond()) {
                    if (player.getEyeLocation().distance(location) <= distance) {
                        if (location.getBlock().getType() == Material.AIR) {
                            location.getBlock().setType(material);
                            BLOCKS.get(player).add(location);
                        }
                    }
                }
            }
        }
        if (playerZ + distance >= data.getWallsZ().getFirst()) {
            if (!BLOCKS.containsKey(player)) {
                BLOCKS.put(player, new ArrayList<>());
            }
            List<Location> circle = circle(player.getLocation(), distance, distance, false, true, 0);
            for (Location location : circle) {
                if (location.getZ() == data.getWallsZ().getFirst()) {
                    if (player.getEyeLocation().distance(location) <= distance) {
                        if (location.getBlock().getType() == Material.AIR) {
                            location.getBlock().setType(material);
                            BLOCKS.get(player).add(location);
                        }
                    }
                }
            }
        }
        if (playerZ - distance <= data.getWallsZ().getSecond()) {
            if (!BLOCKS.containsKey(player)) {
                BLOCKS.put(player, new ArrayList<>());
            }
            List<Location> circle = circle(player.getLocation(), distance, distance, false, true, 0);
            for (Location location : circle) {
                if (location.getX() == data.getWallsZ().getSecond()) {
                    if (player.getEyeLocation().distance(location) <= distance) {
                        if (location.getBlock().getType() == Material.AIR) {
                            location.getBlock().setType(material);
                            BLOCKS.get(player).add(location);
                        }
                    }
                }
            }
        }
        if (playerY + distance >= data.getWallY()) {
            if (!BLOCKS.containsKey(player)) {
                BLOCKS.put(player, new ArrayList<>());
            }
            List<Location> circle = circle(player.getLocation(), distance, distance, false, true, 0);
            for (Location location : circle) {
                if (location.getY() == data.getWallY()) {
                    if (player.getEyeLocation().distance(location) <= distance) {
                        if (location.getBlock().getType() == Material.AIR) {
                            location.getBlock().setType(material);
                            BLOCKS.get(player).add(location);
                        }
                    }
                }
            }
        }
        /*
        if (player.getLocation().getX() - distance <= data.getWallsX().getSecond()) {
            int i = 0;
            while (i <= distance) {
                if (player.getLocation().getX() - i == data.getWallsX().getSecond()) {
                    Location loc = new Location(player.getWorld(), player.getLocation().getBlockX() - i, player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                    Block block = player.getWorld().getBlockAt(loc);
                    if (block.getType() == Material.AIR) {
                        BLOCKS.get(player).add(loc);
                        block.setType(material);
                    }
                    neighboursx(player, material, distance, loc);
                }
                i++;
            }
        }
         */
    }
/*
    private static void neighboursx(Player player, Material material, int distance, Location location) {

        Block block1 = player.getWorld().getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ() + 1);
        Block block2 = player.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() + 1, location.getBlockZ());
        Block block3 = player.getWorld().getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ() - 1);
        Block block4 = player.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() - 1, location.getBlockZ());
        boolean block1test = false;
        boolean block2test = false;
        boolean block3test = false;
        boolean block4test = false;

        if(player.getEyeLocation().distance(block1.getLocation()) <= distance && !checkedblocks.contains(block1.getLocation())) {
            if (block1.getType() == Material.AIR) {
                BLOCKS.get(player).add(block1.getLocation());
                block1.setType(material);
            }
            checkedblocks.add(block1.getLocation());
        }
        if(player.getEyeLocation().distance(block2.getLocation()) <= distance && !checkedblocks.contains(block1.getLocation())) {
            if (block2.getType() == Material.AIR) {
                BLOCKS.get(player).add(block2.getLocation());
                block2.setType(material);
            }
            checkedblocks.add(block2.getLocation());
        }
        if(player.getEyeLocation().distance(block3.getLocation()) <= distance && !checkedblocks.contains(block1.getLocation())) {
            if (block3.getType() == Material.AIR) {
                BLOCKS.get(player).add(block3.getLocation());
                block3.setType(material);
            }
            checkedblocks.add(block3.getLocation());
        }
        if(player.getEyeLocation().distance(block4.getLocation()) <= distance && !checkedblocks.contains(block1.getLocation())) {
            if (block4.getType() == Material.AIR) {
                BLOCKS.get(player).add(block4.getLocation());
                block4.setType(material);
            }
            checkedblocks.add(block4.getLocation());
        }
        if (block1test) {
            neighboursx(player, material, distance, block1.getLocation());
        }
        if (block2test) {
            neighboursx(player, material, distance, block2.getLocation());
        }
        if (block3test) {
            neighboursx(player, material, distance, block3.getLocation());
        }
        if (block4test) {
            neighboursx(player, material, distance, block4.getLocation());
        }
    }
 */
    private static List<Location> circle(Location loc, int radius, int height, boolean hollow, boolean sphere, int plusY){
        List<Location> circleblocks = new ArrayList<Location>();
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();

        for(int x = cx - radius; x <= cx + radius; x++){
            for (int z = cz - radius; z <= cz + radius; z++){
                for(int y = (sphere ? cy - radius : cy); y < (sphere ? cy + radius : cy + height); y++){
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);

                    if(dist < radius * radius && !(hollow && dist < (radius - 1) * (radius - 1))){
                        Location l = new Location(loc.getWorld(), x, y + plusY, z);
                        circleblocks.add(l);
                    }
                }
            }
        }

        return circleblocks;
    }

}
