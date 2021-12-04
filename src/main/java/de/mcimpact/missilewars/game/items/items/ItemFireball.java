package de.mcimpact.missilewars.game.items.items;

import de.mcimpact.core.Core;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.game.items.*;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemFireball extends SimpleItem implements InteractableItem, BuyableItem, ReceivableItem {

    public ItemFireball() {
        super(Material.FIRE_CHARGE, Core.translate(Core.getTranslatableComponent("missilewars.item.fireball.name")), Core.translate(Core.getTranslatableComponent("missilewars.item.fireball.lore")), 1);
    }

    @Override
    public ItemWorth buyWorth() {
        return new ItemWorth(40);
    }

    /**
     * @param buyer - The NetPlayer who is trying to buy to Item
     * @return <code>true</code> is the Item was purchased and <code>false</code> if not!
     */
    @Override
    public boolean buy(NetPlayer buyer) {
        return false;
    }

    /**
     * @param interactEvent
     * @implNote This will be triggered then the Game is running and a valid player interacts with the Item
     */
    @Override
    public void onInteract(PlayerInteractEvent interactEvent) {
        if(interactEvent.getAction() == Action.RIGHT_CLICK_AIR) {
            Player player = interactEvent.getPlayer();
            player.launchProjectile(Fireball.class);
            if(interactEvent.getItem().getAmount() > 1)
            player.getInventory().getItem(interactEvent.getHand()).setAmount(interactEvent.getItem().getAmount() - 1);
            else
            player.getInventory().setItem(interactEvent.getHand(), new ItemStack(Material.AIR));
        }

        interactEvent.setCancelled(true);
    }

    /**
     * @return value between 0 and 1
     */
    @Override
    public double getPercentage() {
        return 0.4;
    }
}
