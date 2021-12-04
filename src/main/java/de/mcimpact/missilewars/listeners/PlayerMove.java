package de.mcimpact.missilewars.listeners;

import de.mcimpact.core.util.Utils;
import de.mcimpact.core.Core;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.gamephase.GamePhase;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import org.bukkit.Material;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        // event.getPlayer().sendActionBar("ground:" + event.getPlayer().isOnGround());
        if (MissileWars.GAME.getGameStatus() == GameStatus.LOBBY) {
            event.getPlayer().setAllowFlight(true);

        } else if (MissileWars.GAME.getGameStatus() == GameStatus.GAME) {
            //GamePhase stuff
            NetPlayer netPlayer = Core.getPlayerUtils().getPlayer(event.getPlayer().getUniqueId());
            if (GamePhase.isInMaterial(netPlayer, Material.WATER) && MissileWars.GAME.isPlayingPlayer(netPlayer)) {
                GamePhase.killPlayer(new PlayerKill.KillInformation(netPlayer,
                        null, event.getPlayer().getLocation(), EntityDamageEvent.DamageCause.CONTACT));
            }
            if (event.getPlayer().getLocation().getBlockY() < -10) {
                GamePhase.killPlayer(new PlayerKill.KillInformation(netPlayer,
                        null, event.getPlayer().getLocation(), EntityDamageEvent.DamageCause.VOID));
                event.getPlayer().teleport(MissileWars.GAME.getSpwanOfPlayer(event.getPlayer()));
            }

        }
      
        if (event.getPlayer().isOnGround()) {
            FlightAttempt.uuidSet.add(event.getPlayer().getUniqueId());
        }
        if (MissileWars.GAME.getGameStatus() == GameStatus.GAME) {
            /*
            if (event.getPlayer().getLocation().getX() <= MissileWars.GAME.getMissileWarsLevel().getData().getWallsX().getFirst()) {
                event.setCancelled(true);
            }
            if (event.getPlayer().getLocation().getX() >= MissileWars.GAME.getMissileWarsLevel().getData().getWallsX().getSecond()) {
                event.setCancelled(true);
            }
            if (event.getPlayer().getLocation().getZ() <= MissileWars.GAME.getMissileWarsLevel().getData().getWallsZ().getFirst()) {
                event.setCancelled(true);
            }
            if (event.getPlayer().getLocation().getZ() >= MissileWars.GAME.getMissileWarsLevel().getData().getWallsZ().getSecond()) {
                event.setCancelled(true);
            }
            if (event.getPlayer().getLocation().getY() >= MissileWars.GAME.getMissileWarsLevel().getData().getWallY()) {
                event.setCancelled(true);
            }
             */
            if (MissileWars.GAME.isPlayingPlayer(event.getPlayer())) {
                if (Utils.BLOCKS.containsKey(event.getPlayer())) {
                    for(Location location : Utils.BLOCKS.get(event.getPlayer())) {
                        if (event.getPlayer().getEyeLocation().distance(location) > 5) {
                            location.getWorld().getBlockAt(location).setType(Material.AIR);
                        }
                    }
                }
                Utils.createWall(event.getPlayer(), Material.RED_STAINED_GLASS, 5);
            }
        }
    }
}
