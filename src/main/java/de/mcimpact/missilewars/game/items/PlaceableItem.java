package de.mcimpact.missilewars.game.items;

import org.bukkit.event.Cancellable;
import org.bukkit.event.block.BlockPlaceEvent;

public interface PlaceableItem extends Item{

    /**
     * @implNote Called when an BPE is called with the Item and <code>{@link BlockPlaceEvent#canBuild()}</code > returns <code>true</code>.
     * The Event <code>{@link Cancellable#isCancelled()}</code> by default!
     *
     * @param blockPlaceEvent
     * @return if the block was placed as a block in the world!
     */
    boolean place(BlockPlaceEvent blockPlaceEvent);
}
