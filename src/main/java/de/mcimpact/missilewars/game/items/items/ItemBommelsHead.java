package de.mcimpact.missilewars.game.items.items;

import de.mcimpact.missilewars.game.items.InteractableItem;
import de.mcimpact.missilewars.game.items.ReceivableItem;
import de.mcimpact.missilewars.game.items.SimpleItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBommelsHead extends SimpleItem implements InteractableItem, ReceivableItem {
    public ItemBommelsHead() {
        super(Material.PLAYER_HEAD, Component.text("§3Bommels"), 1);
    }

    /**
     * @param interactEvent
     * @implNote This will be triggered then the Game is running and a valid player interacts with the Item
     */
    @Override
    public void onInteract(PlayerInteractEvent interactEvent) {
        if (interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK || interactEvent.getAction() == Action.RIGHT_CLICK_AIR) {
            interactEvent.getPlayer().sendMessage("§3Bruuuuu");
            consumeItem(interactEvent);
        }
        interactEvent.setCancelled(true);
    }


    @Override
    public ItemStack toStack() {
        ItemStack stack = skull("Bommels05");
        stack.editMeta(meta -> meta.displayName(getName()));
        stack.lore(getLore());
        return stack;
    }

    protected ItemStack skull(String owner) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(owner);
        skull.setItemMeta(meta);
        return skull;
    }

    /**
     * @return value between 0 and 1
     */
    @Override
    public double getPercentage() {
        return 0.1;
    }
}
