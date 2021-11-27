package de.mcimpact.missilewars.listeners;

import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
       // event.getPlayer().sendActionBar("ground:" + event.getPlayer().isOnGround());
        if(MissileWars.GAME.getGameStatus() == GameStatus.LOBBY) {
        event.getPlayer().setAllowFlight(true);
        }
        if (event.getPlayer().isOnGround()) {
            FlightAttempt.uuidSet.add(event.getPlayer().getUniqueId());
        }
        if (MissileWars.GAME.getGameStatus() == GameStatus.GAME) {
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
        }
    }
}
