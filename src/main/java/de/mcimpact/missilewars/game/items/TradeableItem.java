package de.mcimpact.missilewars.game.items;

import de.mcimpact.core.players.NetPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.List;

public class TradeableItem implements SellableItem, BuyableItem{

    private final ItemWorth sellWorth;
    private final ItemWorth buyWorth;
    private final Material material;
    private Component name;
    private List<Component> lore;

    public TradeableItem(int sellWorth, int buyWorth, Material material, Component name, List<Component> lore) {

        this.sellWorth = new ItemWorth(sellWorth);
        this.buyWorth = new ItemWorth(buyWorth);
        this.name = name;
        this.lore = lore;
        this.material = material;

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
    public Material getMaterial() {
        return material;
    }

    @Override
    public Component getName() {
        return name;
    }

    @Override
    public List<Component> getLore() {
        return lore;
    }

    @Override
    public void appendLore(Component component) {
            lore.add(component);
    }

    @Override
    public void setLore(List<Component> components) {
        lore = components;
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
