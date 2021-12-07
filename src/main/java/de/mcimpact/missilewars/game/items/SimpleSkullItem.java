package de.mcimpact.missilewars.game.items;

import de.mcimpact.core.internal.Untested;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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

    /**
     * @param key valid Translationkey
     * @implNote Use <code>{@link SimpleSkullItem#SimpleSkullItem(Component, List, int)}, {@link SimpleSkullItem#SimpleSkullItem(Component, Component, int)} or {@link SimpleSkullItem#SimpleSkullItem(Component, int)} instead!</code>
     */
    public SimpleSkullItem(String key, int amount) {
        super(SimpleTranslatableItem.generateTranslatableStack(Material.PLAYER_HEAD,key, amount), amount);

    }

}
