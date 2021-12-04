package de.mcimpact.missilewars.listeners;

import de.mcimpact.missilewars.MissileWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent clickEvent) {
        MissileWars.getItemManager().handle(clickEvent);
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent interactEvent) {
        MissileWars.getItemManager().handle(interactEvent);
    }


    public static record InternalItemClickEvent(){}
}
