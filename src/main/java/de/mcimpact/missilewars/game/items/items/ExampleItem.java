package de.mcimpact.missilewars.game.items.items;

import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.game.items.ItemWorth;
import de.mcimpact.missilewars.game.items.ReceivableItem;
import de.mcimpact.missilewars.game.items.SellableItem;
import de.mcimpact.missilewars.game.items.SimpleItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import javax.management.monitor.StringMonitorMBean;
import java.util.List;

public class ExampleItem extends SimpleItem implements ReceivableItem {
    public ExampleItem(Material material, Component name, List<Component> components, int amount) {
        super(material, name, components, amount);
    }


    /**
     * @return value between 0 and 1
     */
    @Override
    public double getPercentage() {
        return 0;
    }

}
