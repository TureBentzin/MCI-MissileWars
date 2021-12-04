package de.mcimpact.missilewars.listeners;

import com.destroystokyo.paper.event.block.TNTPrimeEvent;
import de.mcimpact.core.util.Utils;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.lang.management.BufferPoolMXBean;

public class TNTExplosion implements Listener {


    @EventHandler
    public void onPrime(TNTPrimeEvent event) {
        MissileWars.broadcast("missilewars.message.debug", "Primed at:" + Utils.getCoordsFromBlock(event.getBlock()));
        if (MissileWars.GAME.getGameStatus() == GameStatus.GAME) {


        } else {

            event.setCancelled(true);

        }
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {
        event.setYield(0);
        if (MissileWars.GAME.getGameStatus() == GameStatus.GAME) {
            MissileWars.broadcast("missilewars.message.debug", "Explosion (" + event.getEntityType().name() + ") at:" + Utils.getCoordsFromBlock(event.getEntity().getLocation().getBlock()));
            if(event.getEntityType() == EntityType.FIREBALL) {
                MissileWars.GAME.getMissileWarsLevel().getWorld().createExplosion(event.getLocation(), 3);
            }

        } else {
            event.setCancelled(true);
        }
    }
}
