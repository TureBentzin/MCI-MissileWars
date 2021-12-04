package de.mcimpact.missilewars.game.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public interface Item {

    default ItemStack toStack() {
        ItemStack itemStack = new ItemStack(getMaterial());
        itemStack.lore(getLore());
        if (itemStack.hasItemMeta()) {
            ItemMeta meta = itemStack.getItemMeta();
            meta.displayName(getName());
            itemStack.setItemMeta(meta);
        }

        return new ItemStack(itemStack);
    }

    Material getMaterial();

    Component getName();

    List<Component> getLore();

    void setLore(List<Component> components);

    int getAmount();

    void appendLore(Component component);

    default String getLegacyName() {
        return LegacyComponentSerializer.legacyAmpersand().serialize(getName());
    }

}
