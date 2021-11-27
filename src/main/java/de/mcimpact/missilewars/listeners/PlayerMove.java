package de.mcimpact.missilewars.listeners;

import de.mcimpact.core.Core;
import de.mcimpact.core.game.Game;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.gamephase.GamePhase;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
       // event.getPlayer().sendActionBar("ground:" + event.getPlayer().isOnGround());
        if(MissileWars.GAME.getGameStatus() == GameStatus.LOBBY) {
        event.getPlayer().setAllowFlight(true);
        }else if(MissileWars.GAME.getGameStatus() == GameStatus.GAME) {
            //GamePhase stuff
            NetPlayer netPlayer = Core.getPlayerUtils().getPlayer(event.getPlayer().getUniqueId());
            if(GamePhase.hasContact(netPlayer, Material.WATER) && MissileWars.GAME.isPlayingPlayer(netPlayer)) {
                GamePhase.killPlayer(new PlayerKill.KillInformation(netPlayer, Core.translate(Core.getTranslatableComponent("missilewars.message.died", netPlayer.getName())),
                        null, event.getPlayer().getLocation(), EntityDamageEvent.DamageCause.CONTACT));
            }
        }

        if (event.getPlayer().isOnGround())
            FlightAttempt.uuidSet.add(event.getPlayer().getUniqueId());
    }
}
