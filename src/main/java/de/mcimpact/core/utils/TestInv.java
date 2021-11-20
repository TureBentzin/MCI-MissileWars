package de.mcimpact.core.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TestInv extends Selector {

    public void start() {
        setEntries(makeEntrys());
    }

    @Override
    protected void handle(SelectorEntry entry) {

    }

    public List<SelectorEntry> makeEntrys() {
        List<SelectorEntry> entries = new ArrayList<>();
        entries.add(new SelectorEntry(new ItemStack(Material.BLACK_WOOL)));
        return entries
    }

}
