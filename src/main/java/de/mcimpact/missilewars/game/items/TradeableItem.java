package de.mcimpact.missilewars.game.items;

import de.mcimpact.core.players.NetPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TradeableItem extends SimpleItem implements SellableItem, BuyableItem {

    private final ItemWorth sellWorth;
    private final ItemWorth buyWorth;

    public TradeableItem(int sellWorth, int buyWorth, Material material, Component name, List<Component> lore, int amount) {
        super(material, name, lore, amount);

        this.sellWorth = new ItemWorth(sellWorth);
        this.buyWorth = new ItemWorth(buyWorth);
    }

    public TradeableItem(int sellWorth, int buyWorth, ItemStack itemStack, int amount) {
        super(itemStack, amount);
        this.sellWorth = new ItemWorth(sellWorth);
        this.buyWorth = new ItemWorth(buyWorth);
    }


    @Override
    public ItemWorth buyWorth() {
        return buyWorth;
    }

    /**
     * @param buyer - The NetPlayer who is trying to buy to Item
     * @return <code>true</code> is the Item was purchased and <code>false</code> if not!
     */
    @Override
    public boolean buy(NetPlayer buyer) {
        //TODO: Not yet implemented
        return false;
    }


    @Override
    public ItemWorth sellWorth() {
        return sellWorth;
    }

    /**
     * @param seller the NetPlayer who sold the Item
     * @return the worth the item was sold for
     */
    @Override
    public int sell(NetPlayer seller) {
        //TODO: Not yet implemented
        return 0;
    }
}
