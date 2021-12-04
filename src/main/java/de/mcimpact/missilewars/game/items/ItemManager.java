package de.mcimpact.missilewars.game.items;

import de.mcimpact.missilewars.MissileWars;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;
import java.util.Set;

public final class ItemManager {

    private static final ItemManager INSTANCE = new ItemManager();
    public static ItemManager getInstance() {
        return INSTANCE;
    }

    private Set<Item> items = new HashSet<>();

    public Set<Item> getItems() {
        return items;
    }

    public boolean addItem(Item item) {
        return getItems().add(item);
    }

    private void handle(Event event) {


        if(event instanceof PlayerInteractEvent) {
            if(!MissileWars.GAME.isPlayingPlayer(((PlayerEvent) event).getPlayer().getUniqueId())) return;
            items.forEach(item -> { if(item instanceof InteractableItem)
                ((InteractableItem) item).onInteract((PlayerInteractEvent) event); });
        }
        if(event instanceof InventoryClickEvent) {
            InventoryClickEvent inventoryClickEvent = (InventoryClickEvent) event;
             if(!MissileWars.GAME.isPlayingPlayer(((InventoryClickEvent) event).getWhoClicked().getUniqueId()));
            items.forEach(item -> { if(item instanceof UsableItem)
                ((UsableItem) item).onClick(inventoryClickEvent); });
        }
    }

}
