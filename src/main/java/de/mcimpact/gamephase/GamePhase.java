package de.mcimpact.gamephase;

import net.kyori.adventure.sound.Sound;
import org.bukkit.entity.Player;

public class GamePhase {

    public static void phasePlayer(Player player) {
        player.setFlying(false);
        player.setAllowFlight(false);
        player.playSound(Sound.sound(org.bukkit.Sound.UI_BUTTON_CLICK.getKey(), Sound.Source.BLOCK, 20, 1));
        player.closeInventory();
        player.getInventory().clear();
        player.setAllowFlight(false);
        player.setHealthScale(20);
    }
}
