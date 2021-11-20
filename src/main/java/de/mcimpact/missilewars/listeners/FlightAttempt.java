package de.mcimpact.missilewars.listeners;

import de.mcimpact.core.Core;
import de.mcimpact.core.paper.LocalPlayer;
import de.mcimpact.core.util.Utils;
import de.mcimpact.missilewars.MissileWars;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;


public class FlightAttempt implements Listener {

    @EventHandler
    @SuppressWarnings("deprecated")
    public void onFlightAttempt(PlayerToggleFlightEvent event) {
        MissileWars.broadcast("missilewars.message.debug", "flight attempt: " + event.getPlayer().getCustomName());
        if (!event.getPlayer().isFlying() && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            if(Utils.isOnGround(Core.getPlayerUtils().getPlayer(event.getPlayer().getUniqueId()))) {
                MissileWars.broadcast("missilewars.message.debug", "double jump: " + event.getPlayer().getCustomName());
                Vector vector = event.getPlayer().getVelocity();

                vector.add(new Vector(0, 0.5, 0));

                event.getPlayer().setVelocity(event.getPlayer().getVelocity().add(vector));

            }
            event.getPlayer().setFlying(false);
            event.setCancelled(true);
        }

    }

}
