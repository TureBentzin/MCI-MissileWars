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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class Selector<R, E extends Selector.SelectorEntry> {
    boolean opened = false;
    List<SelectorEntry> entries;
    String name;
    ItemStack background;

    public Selector(@NotNull String name, @Nullable ItemStack background) {
        this.name = name;
        this.background = background;
    }

    public void open(NetPlayer netPlayer){
        if (entries == null) {
            throw new NullPointerException("No SelectorEntries");
        }
        if (entries.size() > 9) {
            throw new IllegalArgumentException("SelectorEntries cant be more than 9");
        }
        opened = true;

        if (!(netPlayer instanceof LocalPlayer)) return;
        Player player = ((LocalPlayer) netPlayer).getBukkit();

        ChestGui gui = new ChestGui(1, name);

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        if (background != null) {
            OutlinePane background = new OutlinePane(0, 0, 9, 1, Pane.Priority.LOWEST);
            background.addItem(new GuiItem(this.background));
            background.setRepeat(true);

            gui.addPane(background);
        }

        OutlinePane navigationPane = null;
        if (entries.size() == 1) {
            navigationPane = new OutlinePane(4, 0, 1, 1);
        }
        if (entries.size() == 3 || entries.size() == 2) {
            navigationPane = new OutlinePane(3, 0, 3, 1);
        }
        if (entries.size() == 5 || entries.size() == 4) {
            navigationPane = new OutlinePane(2, 0, 5, 1);
        }
        if (entries.size() == 7 || entries.size() == 6) {
            navigationPane = new OutlinePane(1, 0, 7, 1);
        }
        if (entries.size() == 9 || entries.size() == 8) {
            navigationPane = new OutlinePane(0, 0, 9, 1);
        }

        for (SelectorEntry entry : entries) {

            navigationPane.addItem(new GuiItem(entry.getItem(), event -> {
                //todo execute
            }));
        }

        gui.addPane(navigationPane);

        gui.show(player);
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

        public ItemStack getItem() {
            return item;
        }
    }
}
