package de.mcimpact.missilewars.listeners;

import com.destroystokyo.paper.event.block.TNTPrimeEvent;
import de.mcimpact.core.util.Utils;
import de.mcimpact.missilewars.MissileWars;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TNTExplosion  implements Listener {


    @EventHandler
    public void onExplode(TNTPrimeEvent event) {
        MissileWars.broadcast("missilewars.message.debug","at:" + Utils.getCoordsFromBlock(event.getBlock()));
    }
}
