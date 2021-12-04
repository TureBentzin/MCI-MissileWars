package de.mcimpact.missilewars.game.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;

public class SimpleReceivableItem extends SimpleItem implements ReceivableItem {

    private final double percentage;

    public SimpleReceivableItem(Material material, Component name, double percentage, int amount) {
        super(material, name, amount);
        this.percentage = percentage;
    }

    public SimpleReceivableItem(Material material, Component name, double percentage) {
        super(material, name, 1);
        this.percentage = percentage;
    }

    /**
     * @return value between 0 and 1
     */
    @Override
    public double getPercentage() {
        return percentage;
    }
}
