package de.mcimpact.missilewars.listeners;

import de.mcimpact.core.Core;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.core.util.Utils;
import de.mcimpact.missilewars.errors.PlayerResolvingException;
import de.mcimpact.missilewars.game.GameStatus;
import de.mcimpact.missilewars.game.MissileWarsGame;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    MissileWarsGame game = MissileWarsGame.getInstance();


    @EventHandler
    public void onQuit(PlayerQuitEvent e) throws PlayerResolvingException {
        //MissileWars.broadcast("missilewars.message.debug", "event: " + e.getEventName());

        e.quitMessage(Component.text(""));

        NetPlayer player = Core.getPlayerUtils().getPlayer(e.getPlayer().getUniqueId());

        if (player == null) throw new PlayerResolvingException(e.getPlayer().getUniqueId());

        //Take Action

        if (game.getGameStatus() == GameStatus.LOBBY) {
            game.removePlayer(player);
            player.sendMessage(Core.getTranslatableComponent("missilewars.message.movement.playerquit", player.getName()));
        }
        if (Utils.BLOCKS.containsKey(e.getPlayer())) {
            for(Location location : Utils.BLOCKS.get(e.getPlayer())) {
                location.getWorld().getBlockAt(location).setType(Material.AIR);
            }
        }


    }


}
