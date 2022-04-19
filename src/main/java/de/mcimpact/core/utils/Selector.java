package de.mcimpact.core.utils;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import de.mcimpact.core.paper.LocalPlayer;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.errors.SelectorOpenedException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.InputMismatchException;
import java.util.List;

public class Selector {
    boolean opened = false;
    List<SelectorEntry> entries;
    String name;
    ItemStack background;
    boolean closeinv;

    public Selector(@NotNull String name, @Nullable ItemStack background, boolean closeInvOnClick) {
        this.name = name;
        this.background = background;
        closeinv = closeInvOnClick;
    }

    public void open(NetPlayer netPlayer) {
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
        OutlinePane navigationPane2 = null;
        if (entries.size() == 1) {
            navigationPane = new OutlinePane(4, 0, 1, 1);
            navigationPane.addItem(createGuiItem(entries.get(0), player));
        }
        if (entries.size() == 2) {
            navigationPane = new OutlinePane(3, 0, 1, 1);
            navigationPane.addItem(createGuiItem(entries.get(0), player));
            navigationPane2 = new OutlinePane(5, 0, 1, 1);
            navigationPane2.addItem(createGuiItem(entries.get(1), player));
        }
        if (entries.size() == 3) {
            navigationPane = new OutlinePane(3, 0, 3, 1);
            navigationPane.addItem(createGuiItem(entries.get(0), player));
            navigationPane.addItem(createGuiItem(entries.get(1), player));
            navigationPane.addItem(createGuiItem(entries.get(2), player));
        }
        if (entries.size() == 4) {
            navigationPane = new OutlinePane(2, 0, 2, 1);
            navigationPane.addItem(createGuiItem(entries.get(0), player));
            navigationPane.addItem(createGuiItem(entries.get(1), player));
            navigationPane2 = new OutlinePane(5, 0, 2, 1);
            navigationPane2.addItem(createGuiItem(entries.get(2), player));
            navigationPane2.addItem(createGuiItem(entries.get(3), player));

        }
        if (entries.size() == 5) {
            navigationPane = new OutlinePane(2, 0, 5, 1);
            navigationPane.addItem(createGuiItem(entries.get(0), player));
            navigationPane.addItem(createGuiItem(entries.get(1), player));
            navigationPane.addItem(createGuiItem(entries.get(2), player));
            navigationPane.addItem(createGuiItem(entries.get(3), player));
            navigationPane.addItem(createGuiItem(entries.get(4), player));
        }
        if (entries.size() == 6) {
            navigationPane = new OutlinePane(1, 0, 3, 1);
            navigationPane.addItem(createGuiItem(entries.get(0), player));
            navigationPane.addItem(createGuiItem(entries.get(1), player));
            navigationPane.addItem(createGuiItem(entries.get(2), player));
            navigationPane2 = new OutlinePane(5, 0, 3, 1);
            navigationPane2.addItem(createGuiItem(entries.get(3), player));
            navigationPane2.addItem(createGuiItem(entries.get(4), player));
            navigationPane2.addItem(createGuiItem(entries.get(5), player));

        }
        if (entries.size() == 7) {
            navigationPane = new OutlinePane(1, 0, 7, 1);
            navigationPane.addItem(createGuiItem(entries.get(0), player));
            navigationPane.addItem(createGuiItem(entries.get(1), player));
            navigationPane.addItem(createGuiItem(entries.get(2), player));
            navigationPane.addItem(createGuiItem(entries.get(3), player));
            navigationPane.addItem(createGuiItem(entries.get(4), player));
            navigationPane.addItem(createGuiItem(entries.get(5), player));
            navigationPane.addItem(createGuiItem(entries.get(6), player));
        }
        if (entries.size() == 8) {
            navigationPane = new OutlinePane(0, 0, 4, 1);
            navigationPane.addItem(createGuiItem(entries.get(0), player));
            navigationPane.addItem(createGuiItem(entries.get(1), player));
            navigationPane.addItem(createGuiItem(entries.get(2), player));
            navigationPane.addItem(createGuiItem(entries.get(3), player));
            navigationPane2 = new OutlinePane(5, 0, 4, 1);
            navigationPane2.addItem(createGuiItem(entries.get(4), player));
            navigationPane2.addItem(createGuiItem(entries.get(5), player));
            navigationPane2.addItem(createGuiItem(entries.get(6), player));
            navigationPane2.addItem(createGuiItem(entries.get(7), player));
        }
        if (entries.size() == 9) {
            navigationPane = new OutlinePane(0, 0, 9, 1);
            navigationPane.addItem(createGuiItem(entries.get(0), player));
            navigationPane.addItem(createGuiItem(entries.get(1), player));
            navigationPane.addItem(createGuiItem(entries.get(2), player));
            navigationPane.addItem(createGuiItem(entries.get(3), player));
            navigationPane.addItem(createGuiItem(entries.get(4), player));
            navigationPane.addItem(createGuiItem(entries.get(5), player));
            navigationPane.addItem(createGuiItem(entries.get(6), player));
            navigationPane.addItem(createGuiItem(entries.get(7), player));
            navigationPane.addItem(createGuiItem(entries.get(8), player));
        }

        gui.addPane(navigationPane);

        if (navigationPane2 != null) {
            gui.addPane(navigationPane2);
        }

        gui.show(player);
    }

    protected void handle(SelectorEntry entry, int entryIndex) {

    }

    protected void handle1(SelectorEntry entry) {

    }

    protected void handle2(SelectorEntry entry) {

    }

    protected void handle3(SelectorEntry entry) {

    }

    protected void handle4(SelectorEntry entry) {

    }

    protected void handle5(SelectorEntry entry) {

    }

    protected void handle6(SelectorEntry entry) {

    }

    protected void handle7(SelectorEntry entry) {

    }

    protected void handle8(SelectorEntry entry) {

    }

    protected void handle9(SelectorEntry entry) {

    }

    private GuiItem createGuiItem(SelectorEntry entry, Player player) {
        return new GuiItem(entry.getItem(), event -> {
            if (closeinv) {
                player.closeInventory();
            }
            int i = getNumber(entry);
            if(i > 0 && i < 10) {
                handle(entry,i);
                switch (getNumber(entry)) {
                    case 1 : handle1(entry);
                    case 2 : handle2(entry);
                    case 3 : handle3(entry);
                    case 4 : handle4(entry);
                    case 5 : handle5(entry);
                    case 6 : handle6(entry);
                    case 7 : handle7(entry);
                    case 8 : handle8(entry);
                    case 9 : handle8(entry);
                    default: throw new InputMismatchException("The EntrySlot is not natural! { 1 - 9 }");
                }
            }

        });
    }

    public int getNumber(SelectorEntry entry) {
        if (entries == null) {
            throw new NullPointerException("No SelectorEntries!");
        }
        int number = 1;
        for (SelectorEntry listentry : entries) {
            if (listentry == entry) {
                return number;
            }
            number++;
        }
        return -1;
    }

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

    public static class SelectorEntry {
        ItemStack item;

        public SelectorEntry(ItemStack item) {
            this.item = item;
        }

        public ItemStack getItem() {
            return item;
        }
    }
}
