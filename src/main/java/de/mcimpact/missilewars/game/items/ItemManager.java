package de.mcimpact.missilewars.game.items;

import de.mcimpact.core.Core;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.util.Timer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;
import java.util.Set;

public final class ItemManager {

    private static final ItemManager INSTANCE = new ItemManager();
    private final Set<Item> items = new HashSet<>();

    public static ItemManager getInstance() {
        return INSTANCE;
    }

    public Set<Item> getItems() {
        return items;
    }

    public boolean addItem(Item item) {
        return getItems().add(item);
    }

    public boolean addItems(Item... items) {
        boolean b = true;
        for (Item item : items) {
            if (!getItems().add(item)) {
                b = false;
            }
        }
        return b;
    }

    public static void startReceiver() {

    }


    public void handle(Event event) {


        if (event instanceof PlayerInteractEvent) {
            PlayerInteractEvent interactEvent = (PlayerInteractEvent) event;
            if (!MissileWars.GAME.isPlayingPlayer(((PlayerEvent) event).getPlayer().getUniqueId())) return;
            items.forEach(item -> {
                if (item instanceof InteractableItem && item.toStack().isSimilar(interactEvent.getItem())) {

                    ((InteractableItem) item).onInteract(interactEvent);
                }
            });
        }
        if (event instanceof InventoryClickEvent) {
            InventoryClickEvent inventoryClickEvent = (InventoryClickEvent) event;
            if (!MissileWars.GAME.isPlayingPlayer(((InventoryClickEvent) event).getWhoClicked().getUniqueId())) ;
            items.forEach(item -> {
                if (item instanceof UsableItem)
                    ((UsableItem) item).onClick(inventoryClickEvent);
            });
        }
    }

    public ReceivableItem getRandomItem() {
        Set<ReceivableItem> localReceivableItems = new HashSet<>();
        for (Item item : items) {
            if (item instanceof ReceivableItem)
                localReceivableItems.add((ReceivableItem) item);
        }

        //Random

        double all = localReceivableItems.stream().mapToDouble(ReceivableItem::getPercentage).sum();
        double r = Math.random() * all;

        double c = 0;
        for (ReceivableItem localReceivableItem : localReceivableItems) {
            c = c + localReceivableItem.getPercentage();
            if (c >= r) {
                return localReceivableItem;
            }
        }

        throw new RuntimeException("Error, the size is: " + localReceivableItems.size());
    }

    public void giveItem(Player... players) {
        for (Player player : players) {
            if (MissileWars.GAME.isPlayingPlayer(player.getUniqueId())) {
                ReceivableItem item = getRandomItem();
                player.getInventory().addItem(item.toStack());
                player.sendMessage(Core.translate(Core.getTranslatableComponent("missilewars.message.itemreceived", item.getAmount(), item.getName())));
            }
        }
    }

    public static class ReceiverTimer extends Timer {

        private final Player player;
        private final int initialValue;

        public ReceiverTimer(int seconds, Player player) {
            super(seconds);
            this.player = player;
            this.initialValue = seconds;
        }

        @Override
        public void update(int value) {
            player.setLevel(value);
            player.setExp(value / initialValue);
        }

        @Override
        protected void finish() {
            player.setLevel(0);
            player.setExp(0);
        }
    }

}
