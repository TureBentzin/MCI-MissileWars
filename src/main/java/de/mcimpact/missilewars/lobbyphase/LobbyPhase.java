package de.mcimpact.missilewars.lobbyphase;

import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.MissileWars;
import net.kyori.adventure.audience.Audience;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.lang.management.BufferPoolMXBean;

public class LobbyPhase {

    public static void onSpawn(NetPlayer netPlayer) {

        Player bukkitPlayer = Bukkit.getPlayer(netPlayer.getUniqueId());
        Location spawnLocation = MissileWars.getLobby().getSpawnLocation();

        spawnLocation.getBlock().setType(Material.AIR);
        Location secondLocation =  spawnLocation.clone();
        secondLocation.add(0, 1, 0);
        secondLocation.getBlock().setType(Material.AIR);

        bukkitPlayer.getInventory().clear();
        bukkitPlayer.setGameMode(GameMode.ADVENTURE);
        bukkitPlayer.teleport(spawnLocation);
    }

    public static void onLobbyPhase(World lobby) {
        lobby.setAutoSave(false);
        lobby.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        lobby.setTime(0);
        lobby.setPVP(false);
        lobby.setSpawnFlags(false, false);

    }
}
