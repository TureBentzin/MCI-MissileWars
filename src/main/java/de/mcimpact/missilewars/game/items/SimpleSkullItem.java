package de.mcimpact.missilewars.game.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.List;

public abstract class SimpleSkullItem extends SimpleItem implements SkullItem{
    public SimpleSkullItem(Component name, List<Component> components, int amount) {
        super(Material.PLAYER_HEAD, name, components, amount);
    }

    public SimpleSkullItem(Component name, Component oneLineLore, int amount) {
        super(Material.PLAYER_HEAD, name, oneLineLore, amount);
    }

    public SimpleSkullItem(Component name, int amount) {
        super(Material.PLAYER_HEAD, name, amount);
    }

}
