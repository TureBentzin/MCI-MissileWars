package de.mcimpact.missilewars.game.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public interface SkullItem extends Item{

    default ItemStack skull(String owner) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(owner);
        skull.setItemMeta(meta);
        return skull;
    }

    String getSkullOwner();

    @Override
     default ItemStack toStack() {
        ItemStack stack = skull(getSkullOwner());
        stack.editMeta(meta -> meta.displayName(getName()));
        stack.lore(getLore());
        return stack;
    }
}
