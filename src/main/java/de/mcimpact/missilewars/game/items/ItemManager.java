package de.mcimpact.missilewars.game.items;

import de.mcimpact.missilewars.MissileWars;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    public boolean addItems(Item... items) {
        boolean b = true;
        for (Item item : items) {
            if(!getItems().add(item)){
                b = false;
            }
        }
        return b;
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
    
    public ReceivableItem getRandomItem() {
        Set<ReceivableItem> localReceivableItems = new HashSet<>();
        for (Item item : items) {
            if(item instanceof ReceivableItem)
                localReceivableItems.add((ReceivableItem) item);
        }

        //Random

        double all = localReceivableItems.stream().mapToDouble(ReceivableItem::getPercentage).sum();
        double r = Math.random() * all;

        double c = 0;
        for (ReceivableItem localReceivableItem : localReceivableItems) {
            c = c + localReceivableItem.getPercentage();
            if(c >= r) {
                return  localReceivableItem;
            }
        }

        throw new RuntimeException("FATAL!");
    }

}
