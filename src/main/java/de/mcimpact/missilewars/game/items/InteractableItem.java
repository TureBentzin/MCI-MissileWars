package de.mcimpact.missilewars.game.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface InteractableItem extends Item {


    /**
     * @param interactEvent
     * @implNote This will be triggered then the Game is running and a valid player interacts with the Item
     */
    void onInteract(PlayerInteractEvent interactEvent);

    default void consumeItem(PlayerInteractEvent interactEvent) {
        Player player = interactEvent.getPlayer();
        if (interactEvent.getItem().getAmount() > 1)
            player.getInventory().getItem(interactEvent.getHand()).setAmount(interactEvent.getItem().getAmount() - 1);
        else
            player.getInventory().setItem(interactEvent.getHand(), new ItemStack(Material.AIR));
    }

}
