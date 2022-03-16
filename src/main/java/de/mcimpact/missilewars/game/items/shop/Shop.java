package de.mcimpact.missilewars.game.items.shop;

import de.mcimpact.core.Core;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.core.utils.Selector;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Shop {

    private Entity keeper;
    private Selector shop;

    public Entity getKeeper() {
        return keeper;
    }


    protected boolean open(Player player) {
        return open(Core.getPlayerUtils().getPlayer(player.getUniqueId()));
    }
    protected boolean open(NetPlayer player) {
        shop.open(player);
        return true;
    }
}
