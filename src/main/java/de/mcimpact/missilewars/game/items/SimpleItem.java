package de.mcimpact.missilewars.game.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SimpleItem implements Item{

    private final Material material;
    private Component name;
    private List<Component> lore;

    public SimpleItem(Material material, Component name, List<Component> components) {
        this.material = material;
        this.name = name;
        lore = components;
    }

    public SimpleItem(Material material, Component name) {
        this.material = material;
        this.name = name;
        lore = new ArrayList<>();
    }

    public SimpleItem(ItemStack itemStack) {
        this.material = itemStack.getType();
        this.name = itemStack.displayName();
        this.lore = itemStack.lore();
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public Component getName() {
        return name;
    }

    @Override
    public List<Component> getLore() {
        return lore;
    }

    @Override
    public void appendLore(Component component) {
        lore.add(component);
    }

    @Override
    public void setLore(List<Component> components) {
        lore = components;
    }
}
