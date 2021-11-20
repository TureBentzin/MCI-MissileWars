package de.mcimpact.core.utils;

import de.mcimpact.core.players.NetPlayer;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Selector<R, E extends Selector.SelectorEntry> {
    List<SelectorEntry> entries;

    public Selector(List<SelectorEntry> entries) {
        this.entries = entries;
    }

    public void open(NetPlayer netPlayer){
        //TODO: open
    }

    protected abstract void handle(E entry);



    public static class SelectorEntry{
        ItemStack item;

        public SelectorEntry(ItemStack item) {
            this.item = item;
        }

        public void onClick() {
            
        }
    }
}
