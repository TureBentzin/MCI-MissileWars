package de.mcimpact.gamephase;

import de.mcimpact.missilewars.MissileWars;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

        player.setGameMode(GameMode.SURVIVAL);


        Bukkit.getServer().setMaxPlayers(30);


       player.sendMessage("Gamephase completed on you!");
    }

    public static void phaseSpectator(Player player) {
        player.setFlying(false);
        player.setAllowFlight(false);
        player.setGameMode(GameMode.SPECTATOR);
        player.playSound(Sound.sound(org.bukkit.Sound.UI_BUTTON_CLICK.getKey(), Sound.Source.BLOCK, 20, 1));
        player.closeInventory();
        player.getInventory().clear();
        player.setHealthScale(20);

        player.sendMessage("Gamephase [spectator] completed on you!");
    }
}
