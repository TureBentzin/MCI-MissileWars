package de.mcimpact.missilewars.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;


public class FlightAttempt implements Listener {

    @EventHandler
    public void onFlightAttempt(PlayerToggleFlightEvent event) {

        if (!event.getPlayer().isFlying() && event.getPlayer().getGameMode() != GameMode.CREATIVE) {

            event.getPlayer().setVelocity(event.getPlayer().getVelocity().add(new Vector(0, 3, 0)));
            event.setCancelled(true);

        }

    }

}
