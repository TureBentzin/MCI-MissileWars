package de.mcimpact.missilewars.listeners;

import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import java.net.http.WebSocket;

public class EntityDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){


        if(MissileWars.GAME.getGameStatus() != GameStatus.GAME)
       if(event.getEntity() instanceof Player) {
               Player player = (Player) event.getEntity();
               if(MissileWars.GAME.getGameStatus() != GameStatus.GAME) event.setCancelled(true);
       }

    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        event.getEntity().setFoodLevel(20);
    }
}
