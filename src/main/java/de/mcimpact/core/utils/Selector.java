package de.mcimpact.core.utils;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import de.mcimpact.core.paper.LocalPlayer;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.errors.SelectorOpenedException;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class Selector<R, E extends Selector.SelectorEntry> {
    boolean opened = false;
    List<SelectorEntry> entries;

    public void open(NetPlayer netPlayer){
        if (entries == null) {
            throw new NullPointerException("No SelectorEntries");
        }
        opened = true;

        if (!(netPlayer instanceof LocalPlayer)) return;
        Player player = ((LocalPlayer) netPlayer).getBukkit();

        ChestGui gui = new ChestGui(1, "Navigator");

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 1, Pane.Priority.LOWEST);
        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        gui.addPane(background);

        OutlinePane navigationPane = new OutlinePane(3, 1, 3, 1);

        ItemStack shop = new ItemStack(Material.CHEST);
        ItemMeta shopMeta = shop.getItemMeta();
        shopMeta.setDisplayName("Shop");
        shop.setItemMeta(shopMeta);

        navigationPane.addItem(new GuiItem(shop, event -> {
            //navigate to the shop
        }));

        ItemStack beacon = new ItemStack(Material.BEACON);
        ItemMeta beaconMeta = beacon.getItemMeta();
        beaconMeta.setDisplayName("Spawn");
        beacon.setItemMeta(beaconMeta);

        navigationPane.addItem(new GuiItem(beacon, event -> {
            //navigate to spawn
        }));

        ItemStack bed = new ItemStack(Material.RED_BED);
        ItemMeta bedMeta = bed.getItemMeta();
        bedMeta.setDisplayName("Home");
        bed.setItemMeta(bedMeta);

        navigationPane.addItem(new GuiItem(bed, event -> {
            //navigate to home
        }));

        gui.addPane(navigationPane);

        gui.show(player);

        //TODO: open
    }

    protected abstract void handle(E entry);

    public void setEntries(List<SelectorEntry> entries) {
        if (opened) {
            try {
                throw new SelectorOpenedException();
            } catch (SelectorOpenedException e) {
                e.printStackTrace();
            }
        }
        this.entries = entries;
    }

    public static class SelectorEntry{
        ItemStack item;

        public SelectorEntry(ItemStack item) {
            this.item = item;
        }

        public void onClick() {
            
        }
    }
}
