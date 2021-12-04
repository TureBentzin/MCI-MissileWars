package de.mcimpact.missilewars.game.items;

import de.mcimpact.core.players.NetPlayer;

public interface SellableItem extends Item {

    ItemWorth sellWorth();

    /**
     * @param seller the NetPlayer who sold the Item
     * @return the worth the item was sold for
     */
    int sell(NetPlayer seller);
}
