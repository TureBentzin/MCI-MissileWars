package de.mcimpact.missilewars.game.items;

import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.items.items.ItemBommelsHead;
import de.mcimpact.missilewars.game.items.items.ItemBuzerHead;
import de.mcimpact.missilewars.game.items.items.ItemFireball;

public class Items {

    public static final void registerItems(ItemManager manager) {
        MissileWars.getMWL().info("registering Items!");
        manager.addItem(new ItemFireball());
        manager.addItem(new ItemBommelsHead());
        manager.addItem(new ItemBuzerHead());
    }
}
