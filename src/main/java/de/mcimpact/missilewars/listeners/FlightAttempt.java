package de.mcimpact.missilewars.listeners;

import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import net.kyori.adventure.sound.Sound;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class FlightAttempt implements Listener {

    public static Set<UUID> uuidSet = new HashSet<UUID>();

    @EventHandler
    @SuppressWarnings("deprecated")
    public void onFlightAttempt(PlayerToggleFlightEvent event) {
        if (MissileWars.GAME.getGameStatus() == GameStatus.LOBBY) {

            if (event.getPlayer().getLocation().getY() < 220)
                if (event.getPlayer().getGameMode() != GameMode.CREATIVE && uuidSet.contains(event.getPlayer().getUniqueId()) && event.getPlayer().hasPermission("missilewars.lobby.doublejump")) {
                    //MissileWars.broadcast("missilewars.message.debug", "double jump: " + event.getPlayer().getName());
                    Vector vector = event.getPlayer().getVelocity();

                    vector.add(new Vector(vector.getX() * 2.3, 0.7, vector.getZ() * 2.3));

                    event.getPlayer().setVelocity(vector);
                    event.getPlayer().playSound(Sound.sound(org.bukkit.Sound.ENTITY_FIREWORK_ROCKET_SHOOT.getKey(), Sound.Source.AMBIENT, 30, 0));
                    event.getPlayer().setAllowFlight(false);
                    uuidSet.remove(event.getPlayer().getUniqueId());
                }
            event.getPlayer().setFlying(false);
            event.setCancelled(true);
        } else if (MissileWars.GAME.getGameStatus() == GameStatus.GAME) {
            if (MissileWars.GAME.getOnlinePlayers().contains(event.getPlayer())) {
                event.getPlayer().setFlying(false);
                event.setCancelled(true);
            }
        }


    }

}
