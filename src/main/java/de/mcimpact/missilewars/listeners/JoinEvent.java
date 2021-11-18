package de.mcimpact.missilewars.listeners;

import de.mcimpact.core.Core;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.MissileWarsPlugin;
import de.mcimpact.missilewars.errors.PlayerResolvingException;
import de.mcimpact.missilewars.game.GameStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws PlayerResolvingException {

        NetPlayer player = Core.getPlayerUtils().getPlayer(e.getPlayer().getUniqueId());

        if(player == null) throw new PlayerResolvingException(e.getPlayer().getUniqueId());

        if(MissileWarsPlugin.GAME.getGameStatus() == GameStatus.LOBBY) {





        }else {


        }


    }


}
