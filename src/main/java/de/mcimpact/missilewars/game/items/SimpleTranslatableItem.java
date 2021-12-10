package de.mcimpact.missilewars.game.items;

import de.mcimpact.core.Core;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * This will receive a TranslationKey instead of Components
 */
public class SimpleTranslatableItem extends SimpleItem {
    public SimpleTranslatableItem(Material material, String key, int amount) {
        super(material, getName(key), amount);
        getLore(key).forEach(component -> appendLore(component));
    }

    public SimpleTranslatableItem(ItemStack itemStack, int amount) {
        super(itemStack, amount);
    }

    public static ItemStack generateTranslatableStack(Material material, String key, int amount) {
        ItemStack itemStack = new ItemStack(material);
        itemStack.lore(getLore(key));
        itemStack.setAmount(amount);
        if (itemStack.hasItemMeta()) {
            ItemMeta meta = itemStack.getItemMeta();
            meta.displayName(getName(key));
            itemStack.setItemMeta(meta);
        }

        return new ItemStack(itemStack);
    }

    public static List<Component> getLore(String key) {
        List<Component> components = new ArrayList<>();
        String loreKey = key + ".lore";
        
        int lore = 0;
        while (true) {
            if (Core.getTranslatableComponent(loreKey + "." + lore) != null) {
                components.add(Core.translate(Core.getTranslatableComponent(loreKey)));
                lore++;
            } else
                break;
        }
        return components;
    }

    public static Component getName(String key) {
        return Core.getTranslatableComponent(key + ".name");
    }
}
