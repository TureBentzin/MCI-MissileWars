package de.mcimpact.missilewars.game.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.List;

public class SimpleReceivableItem extends SimpleItem implements ReceivableItem{

    private final double percentage;

    public SimpleReceivableItem(Material material, Component name, double percentage) {
        super(material, name);
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
