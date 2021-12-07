package de.mcimpact.missilewars.listeners;
import de.mcimpact.missilewars.MissileWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvent implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent placeEvent) {
        if(MissileWars.GAME.isRunning())
        if(placeEvent.canBuild()) {
            MissileWars.getItemManager().handle(placeEvent);
        }
    }
}
