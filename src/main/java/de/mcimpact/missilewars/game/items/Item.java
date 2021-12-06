package de.mcimpact.missilewars.game.items;

import de.mcimpact.core.Core;
import de.mcimpact.core.util.Identifiable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.persistence.metamodel.IdentifiableType;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public interface Item extends Identifiable {
    @Override
    default String getID() {
        return Identifiable.generateID(ChatColor.stripColor(getLegacyName()));
    }

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

    default ItemStack toStack(Player player) {
        ItemStack itemStack = new ItemStack(getMaterial());
        itemStack.lore(translateLore(player.locale()));
        if (itemStack.hasItemMeta()) {
            ItemMeta meta = itemStack.getItemMeta();
            meta.displayName(Core.translate(getName(), player.locale()));
            itemStack.setItemMeta(meta);
        }

        return new ItemStack(itemStack);
    }

    default List<Component> translateLore(Locale locale) {
        ArrayList<Component> arrayList = new ArrayList<>();
        getLore().forEach(component -> arrayList.add(Core.translate(component,locale)));
        return arrayList;
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
