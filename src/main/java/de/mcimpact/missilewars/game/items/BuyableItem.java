package de.mcimpact.missilewars.game.items;

import de.mcimpact.core.players.NetPlayer;

public interface BuyableItem extends Item {

    ItemWorth buyWorth();

    /**
     * @param buyer - The NetPlayer who is trying to buy to Item
     * @return <code>true</code> is the Item was purchased and <code>false</code> if not!
     */
    boolean buy(NetPlayer buyer);
}
