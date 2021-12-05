package de.mcimpact.missilewars.listeners;

import de.mcimpact.core.Core;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.gamephase.GamePhase;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
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
                event.getPlayer().teleport(MissileWars.GAME.getSpawnOfPlayer(event.getPlayer()));
            }

        }


        if (event.getPlayer().isOnGround())
            FlightAttempt.uuidSet.add(event.getPlayer().getUniqueId());
    }
}
