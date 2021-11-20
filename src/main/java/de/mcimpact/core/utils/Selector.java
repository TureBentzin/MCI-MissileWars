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
                if (closeinv) {
                    player.closeInventory();
                }
                handle(entry);
                if (getNumber(entry) == 1) {
                    handle1(entry);
                }
                if (getNumber(entry) == 2) {
                    handle2(entry);
                }
                if (getNumber(entry) == 3) {
                    handle3(entry);
                }
                if (getNumber(entry) == 4) {
                    handle4(entry);
                }
                if (getNumber(entry) == 5) {
                    handle5(entry);
                }
                if (getNumber(entry) == 6) {
                    handle6(entry);
                }
                if (getNumber(entry) == 7) {
                    handle7(entry);
                }
                if (getNumber(entry) == 8) {
                    handle8(entry);
                }
                if (getNumber(entry) == 9) {
                    handle9(entry);
                }
            }));
        }

        gui.addPane(navigationPane);

        gui.show(player);
    }

    protected void handle(SelectorEntry entry) {

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

    public int getNumber(SelectorEntry entry) {
        if (entries == null) {
            throw new NullPointerException("No SelectorEntries");
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

    public static class SelectorEntry{
        ItemStack item;

        public SelectorEntry(ItemStack item) {
            this.item = item;
        }

        public ItemStack getItem() {
            return item;
        }
    }
}
