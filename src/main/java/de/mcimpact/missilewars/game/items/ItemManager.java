package de.mcimpact.missilewars.game.items;

import de.mcimpact.core.Core;
import de.mcimpact.core.util.Pair;
import de.mcimpact.game.team.Team;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.util.Timer;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    public Item[] toArray() {
        Item[] items = new Item[getItems().size()];
        int i = 0;

        for (Item item : getItems()) {
            boolean complete = false;
            while (!complete && i <= items.length -1)
                if (items[i] != null) {
                    items[i] = item;
                    complete = true;
                } else
                    i++;
        }
        return items;
    }

    @UnknownInitialization
    private Pair<ReceiverTimer> receiverTimerPair;

    @UnknownInitialization
    @Nullable
    public Pair<ReceiverTimer> getReceiverTimerPair() {
        return receiverTimerPair;
    }

    public void startReceiver() {
        final int time = 20;
        if (MissileWars.GAME.getOnlinePlayers().size() == 0) {
            return;
        }
        if (MissileWars.GAME.getOnlinePlayers().size() == 1) {
            MissileWars.broadcast("missilewars.message.game.single");
            MissileWars.GAME.getOnlinePlayers().forEach(player -> Bukkit.getScheduler().runTaskAsynchronously(MissileWars.getMissileWars(), new ReceiverTimer(10, MissileWars.GAME.teamer.getTeam(player.getUniqueId()))));
            return;
        }
        if (MissileWars.GAME.getTeams()[0].getUuids().size() > MissileWars.GAME.getTeams()[1].getUuids().size()) {
            int ratio = MissileWars.GAME.getTeams()[0].getUuids().size() / MissileWars.GAME.getTeams()[1].getUuids().size();
            MissileWars.broadcast("missilewars.message.debug", "ratio: " + ratio);
            receiverTimerPair = new Pair<>(new ReceiverTimer(time, MissileWars.GAME.getTeams()[0]), new ReceiverTimer(20 / ratio, MissileWars.GAME.getTeams()[1]));
        } else if (MissileWars.GAME.getTeams()[1].getUuids().size() > MissileWars.GAME.getTeams()[0].getUuids().size()) {
            int ratio = MissileWars.GAME.getTeams()[1].getUuids().size() / MissileWars.GAME.getTeams()[0].getUuids().size();
            MissileWars.broadcast("missilewars.message.debug", "ratio: " + ratio);

            receiverTimerPair = new Pair<>(new ReceiverTimer(time, MissileWars.GAME.getTeams()[1]), new ReceiverTimer(20 / ratio, MissileWars.GAME.getTeams()[0]));
        } else {
            receiverTimerPair = new Pair<>(new ReceiverTimer(time, MissileWars.GAME.getTeams()[1]), new ReceiverTimer(20, MissileWars.GAME.getTeams()[0]));

        }
        getReceiverTimerPair().forBoth(receiverTimer -> Bukkit.getScheduler().runTaskAsynchronously(MissileWars.getMissileWars(), () -> receiverTimer.start()));

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
        if (event instanceof BlockPlaceEvent) {
            BlockPlaceEvent blockPlaceEvent = (BlockPlaceEvent) event;
            if(!MissileWars.GAME.isPlayingPlayer(((BlockPlaceEvent) event).getPlayer().getUniqueId())) return;
            items.forEach(item -> {
                if (item instanceof PlaceableItem) {
                    blockPlaceEvent.setCancelled(true);
                    ((PlaceableItem) item).place(blockPlaceEvent);
                }
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
                player.getInventory().addItem(item.toStack(player));
                player.sendMessage(Core.translate(Core.getTranslatableComponent("missilewars.message.itemreceived", item.getAmount(), item.getName())));
            }
        }
    }

    public Item giveItem(Item item, Player... players) {
        for (Player player : players) {
            if(MissileWars.GAME.isPlayingPlayer(player)) {
                player.getInventory().addItem(item.toStack(player));
                player.sendMessage(Core.translate(Core.getTranslatableComponent("missilewars.message.itemreceived", item.getAmount(), item.getName())));
            }
        }
        return item;
    }

    public static class ReceiverTimer extends Timer {

        private final Team team;
        private final int initialValue;

        public ReceiverTimer(int seconds, Team team) {
            super(seconds);
            this.team = team;
            this.initialValue = seconds;

            for (UUID uuid : team.getUuids()) {
                Player player = Bukkit.getPlayer(uuid);
                player.setLevel(0);
                player.setExp(0);
            }
        }

        @Override
        public void update(int value) {
            if (MissileWars.GAME.isRunning())
                for (UUID uuid : team.getUuids()) {
                    Player player = Bukkit.getPlayer(uuid);
                    player.setLevel(value);
                    player.setExp(value / initialValue);
                }
            else
                abort();
        }

        @Override
        protected void finish() {
            for (UUID uuid : team.getUuids()) {
                Player player = Bukkit.getPlayer(uuid);
                player.setLevel(0);
                player.setExp(0);
                MissileWars.getItemManager().giveItem(player);
            }
            setValue(initialValue);
            start();
        }

        @Override
        protected void abort(int finalvalue) {
            System.out.println("Aborted Timer at: " + finalvalue);
            for (UUID uuid : team.getUuids()) {
                Player player = Bukkit.getPlayer(uuid);
                player.setLevel(0);
                player.setExp(0);
            }
        }
    }

}
