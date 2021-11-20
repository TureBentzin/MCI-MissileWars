package de.mcimpact.core.utils;

import de.mcimpact.core.players.NetPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TestSelector extends Selector {

    public TestSelector() {
        super("Testselector", new ItemStack(Material.RED_STAINED_GLASS_PANE));
    }

    public void start(NetPlayer player) {
        setEntries(makeEntrys());
        open(player);
    }

    @Override
    protected void handle(SelectorEntry entry) {

    }

    public List<SelectorEntry> makeEntrys() {
        List<SelectorEntry> entries = new ArrayList<>();
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.TNT), "Missilewars")));
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.NETHER_STAR), "Lobby")));
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.DRAGON_HEAD), "Playground")));
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.OBSERVER), "Debugserver")));
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.WARPED_FUNGUS), "Nokotlin!")));
        return entries;
    }

}
