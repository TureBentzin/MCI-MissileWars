package de.mcimpact.core.utils;

import de.mcimpact.core.players.NetPlayer;
import org.bukkit.inventory.ItemStack;

public abstract class Selector<R, E extends Selector.SelectorEntry> {

    public void open(NetPlayer netPlayer){
        //TODO: open
    }

    protected abstract R handle(E entry);



    public static class SelectorEntry{
        ItemStack entryStack;

        public void onClick() {
            
        }
    }
}
