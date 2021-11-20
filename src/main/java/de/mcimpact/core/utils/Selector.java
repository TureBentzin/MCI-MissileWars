package de.mcimpact.core.utils;

import de.mcimpact.core.players.NetPlayer;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class Selector<R, E extends Selector.SelectorEntry> {
    List<SelectorEntry> entries;

    public void open(NetPlayer netPlayer){
        if (entries == null) {
            throw new NullPointerException("No SelectorEntries");
        }
        
        //TODO: open
    }

    protected abstract void handle(E entry);

    public void setEntries(List<SelectorEntry> entries) {
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
