package de.mcimpact.missilewars.game.items;

import org.bukkit.event.player.PlayerInteractEvent;

public interface InteractableItem extends Item{


    /**
     * @implNote This will be triggered then the Game is running and a valid player interacts with the Item
     * @param interactEvent
     */
    void onInteract(PlayerInteractEvent interactEvent);

}
