package de.mcimpact.missilewars.game.items;

import de.mcimpact.missilewars.game.items.items.ItemFireball;

public class Items {

    public static final void registerItems(ItemManager manager) {
        manager.addItem(new ItemFireball());
    }
}
