package de.mcimpact.missilewars.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
       // event.getPlayer().sendActionBar("ground:" + event.getPlayer().isOnGround());
        if(Missilewars.GAME.getGameStatus() == GameStatus.LOBBY) {
        event.getPlayer().setAllowFlight(true);
        }
        if (event.getPlayer().isOnGround())
            FlightAttempt.uuidSet.add(event.getPlayer().getUniqueId());
    }
}
