package de.mcimpact.missilewars.listeners;

import de.mcimpact.core.Core;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.errors.PlayerResolvingException;
import de.mcimpact.missilewars.game.GameStatus;
import de.mcimpact.missilewars.game.MissileWarsGame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    MissileWarsGame game = MissileWarsGame.getInstance();


    @EventHandler
    public void onQuit(PlayerQuitEvent e) throws PlayerResolvingException {
        //MissileWars.broadcast("missilewars.message.debug", "event: " + e.getEventName());

        NetPlayer player = Core.getPlayerUtils().getPlayer(e.getPlayer().getUniqueId());

        if (player == null) throw new PlayerResolvingException(e.getPlayer().getUniqueId());

        //Take Action

        if (game.getGameStatus() == GameStatus.LOBBY) {
            game.teamer.remove(player);
            player.sendMessage(Core.getTranslatableComponent("missilewars.message.movement.playerquit", player.getName()));
        }


    }


}
