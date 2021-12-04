package de.mcimpact.missilewars.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickEvent implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent interactEvent) {

    }


    public static record InternalItemClickEvent(){}
}
