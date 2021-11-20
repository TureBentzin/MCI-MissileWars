package de.mcimpact.missilewars.listeners;

import de.mcimpact.core.Core;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.errors.PlayerResolvingException;
import de.mcimpact.missilewars.game.GameStatus;
import de.mcimpact.missilewars.game.MissileWarsGame;
import de.mcimpact.missilewars.lobbyphase.LobbyPhase;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    MissileWarsGame game = MissileWarsGame.getInstance();


    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws PlayerResolvingException {

        Player bukkitPlayer = e.getPlayer();
     //   MissileWars.broadcast("missilewars.message.debug", "event: " + e.getEventName());

        NetPlayer player = Core.getPlayerUtils().getPlayer(e.getPlayer().getUniqueId());

        if (player == null) throw new PlayerResolvingException(e.getPlayer().getUniqueId());



        if (MissileWars.GAME.getGameStatus() == GameStatus.LOBBY) {

            LobbyPhase.onSpawn(player);
            
            if (player.isAdminMode()) {

                player.sendMessage(Core.translate(Core.getTranslatableComponent("missilewars.message.movement.playerjoin.failed")));
                player.sendMessage(Core.translate(Core.getTranslatableComponent("missilewars.message.movement.playerjoin.aborted")));

                return;
            }

            game.teamer.add(player);

            MissileWars.broadcast("missilewars.message.movement.playerjoin", player.getName());

            player.sendMessage(Core.getTranslatableComponent("missilewars.message.teaming.joined",
                    Component.text(game.teamer.getTeam(player).getColor().name()).color(game.teamer.getTeam(player).getColor().getTextColor().adventure)));

          //  MissileWars.broadcast("missilewars.message.debug", game.teamer.getTeamMap().toString());


        } else if (game.getGameStatus() == GameStatus.PAUSED) {
            player.sendMessage(Core.translate(Core.getTranslatableComponent("missilewars.message.movement.playerjoin.failed")));
            player.connectToFallback();
        } else

            player.sendMessage((Core.getTranslatableComponent("missilewars.message.movement.playerjoin.alreadyrunning")));

    }


}



