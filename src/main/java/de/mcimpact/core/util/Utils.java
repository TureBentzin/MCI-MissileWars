package de.mcimpact.core.util;

import de.mcimpact.core.players.NetPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Utils {

    public static boolean isOnGround(NetPlayer netPlayer) {

        Player player = Bukkit.getPlayer(netPlayer.getUniqueId());

        return !player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.AIR);
    }
}
