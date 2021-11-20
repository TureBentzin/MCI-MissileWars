package de.mcimpact.missilewars.listeners;

import de.mcimpact.core.Core;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.errors.PlayerResolvingException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {


    @EventHandler
    public void onQuit(PlayerQuitEvent e) throws PlayerResolvingException {
        MissileWars.broadcast("missilewars.message.debug", "event: " + e.getEventName());

        NetPlayer player = Core.getPlayerUtils().getPlayer(e.getPlayer().getUniqueId());

        if (player == null) throw new PlayerResolvingException(e.getPlayer().getUniqueId());
    }
}
