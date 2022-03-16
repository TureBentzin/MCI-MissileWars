package de.mcimpact.missilewars.game.items.shop;

import de.mcimpact.core.util.Pair;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public final class ShopManager implements Listener {

    public ShopManager() {

    }

    private Pair<Shop> shopPair;


    @EventHandler
    public void onClick(PlayerInteractEntityEvent interactEvent) {
        if(shopPair.getFirst().getKeeper().equals(interactEvent.getRightClicked())) {
            interactEvent.setCancelled(true);
            shopPair.getFirst().open(interactEvent.getPlayer());
        }
        if(shopPair.getSecond().getKeeper().equals(interactEvent.getRightClicked())) {
            interactEvent.setCancelled(true);
            shopPair.getSecond().open(interactEvent.getPlayer());
        }
    }


}
