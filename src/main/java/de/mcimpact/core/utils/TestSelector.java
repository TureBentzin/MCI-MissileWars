package de.mcimpact.core.utils;

import de.mcimpact.core.players.NetPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TestSelector extends Selector {

    public void start(NetPlayer player) {
        setEntries(makeEntrys());
        open(player);
    }

    @Override
    protected void handle(SelectorEntry entry) {

    }

    public List<SelectorEntry> makeEntrys() {
        List<SelectorEntry> entries = new ArrayList<>();
        entries.add(new SelectorEntry(new ItemStack(Material.BLACK_WOOL)));
        return entries;
    }

}
