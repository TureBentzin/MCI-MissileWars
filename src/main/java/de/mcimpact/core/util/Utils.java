package de.mcimpact.core.util;

import de.mcimpact.core.players.NetPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.io.File;

public class Utils {

    public static File ROOT = Bukkit.getServer().getWorldContainer();


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
}
