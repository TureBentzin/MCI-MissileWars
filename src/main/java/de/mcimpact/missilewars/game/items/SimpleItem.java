package de.mcimpact.missilewars.game.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SimpleItem implements Item {

    private final Material material;
    private final int amount;
    private final Component name;
    private List<Component> lore;

    public SimpleItem(Material material, Component name, List<Component> components, int amount) {
        this.material = material;
        this.name = name;
        lore = components;
        this.amount = amount;
    }

    public SimpleItem(Material material, Component name, Component oneLineLore, int amount) {
        this.material = material;
        this.name = name;
        lore = new ArrayList<>();
        lore.add(oneLineLore);
        this.amount = amount;
    }

    public SimpleItem(Material material, Component name, int amount) {
        this.material = material;
        this.name = name;
        this.amount = amount;
        lore = new ArrayList<>();
    }

    public SimpleItem(ItemStack itemStack, int amount) {
        this.material = itemStack.getType();
        this.name = itemStack.displayName();
        this.lore = itemStack.lore();
        this.amount = amount;
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
    public void setLore(List<Component> components) {
        lore = components;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void appendLore(Component component) {
        lore.add(component);
    }
}
