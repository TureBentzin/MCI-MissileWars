package de.mcimpact.missilewars.game.items.items;

import de.mcimpact.core.Core;
import de.mcimpact.missilewars.game.items.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Effect;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemBuzerHead extends SimpleSkullItem implements ReceivableItem, InteractableItem {

    public ItemBuzerHead() {
        super(Component.text("§7Buzer Head"), 3);
    }
    /**
     * @return value between 0 and 1
     */
    @Override
    public double getPercentage() {
        return 0.01;
    }

    @Override
    public String getSkullOwner() {
        return "xxxboy1";
    }

    /**
     * @param interactEvent
     * @implNote This will be triggered then the Game is running and a valid player interacts with the Item
     */
    @Override
    public void onInteract(PlayerInteractEvent interactEvent) {
        if(interactEvent.getAction() == Action.RIGHT_CLICK_AIR || interactEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = interactEvent.getPlayer();
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 10, 4));
            Core.getPlayerUtils().getPlayer(player.getUniqueId()).sendMessage(Core.getTranslatableComponent("missilewars.item.buzer.msg"));
        }
        interactEvent.setCancelled(true);
    }
}
