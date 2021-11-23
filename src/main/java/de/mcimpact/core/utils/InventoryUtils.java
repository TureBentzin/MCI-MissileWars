package de.mcimpact.core.utils;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import de.mcimpact.core.Core;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryUtils {

    public static ItemStack setName(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack setName(ItemStack item, Component name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ComponentHolder.of(Core.translate(name)).asLegacyString());
        item.setItemMeta(meta);
        return item;
    }

}
