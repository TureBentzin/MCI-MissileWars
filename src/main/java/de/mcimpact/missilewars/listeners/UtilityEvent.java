package de.mcimpact.missilewars.listeners;

import de.mcimpact.core.Core;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.net.http.WebSocket;

public class UtilityEvent implements Listener {

    //@EventHandler
    public void onServerListPing(ServerListPingEvent event) {
       if(MissileWars.GAME.getGameStatus() == GameStatus.LOBBY) {
           event.motd(Core.translate(Core.getTranslatableComponent("missilewars.motd.lobby")));
       }else if(MissileWars.GAME.isRunning()) {
           event.motd(Core.translate(Core.getTranslatableComponent("missilewars.motd.game")));
       }else if(MissileWars.GAME.getGameStatus() == GameStatus.GAME_END) {
           event.motd(Core.translate(Core.getTranslatableComponent("missilewars.motd.end")));
       }else if(MissileWars.GAME.getGameStatus() == GameStatus.PAUSED) {
           event.motd(Core.translate(Core.getTranslatableComponent("missilewars.motd.paused")));
       }
    }
}
