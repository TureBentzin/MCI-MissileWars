package de.mcimpact.missilewars.game.items;

import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @implNote An Item that can react to a click in the Inventory
 */
public interface UsableItem extends Item {

    /**
     * @param event
     * @implNote This will be triggered then the Game is running and a valid player clicks on the Item
     */
    void onClick(InventoryClickEvent event);
}
