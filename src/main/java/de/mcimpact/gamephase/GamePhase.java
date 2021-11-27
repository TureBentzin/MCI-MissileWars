package de.mcimpact.gamephase;

import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.listeners.PlayerKill;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

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

    }

    public static void phaseSpectator(Player player) {
        player.setFlying(false);
        player.setAllowFlight(false);
        player.setGameMode(GameMode.SPECTATOR);
        player.playSound(Sound.sound(org.bukkit.Sound.UI_BUTTON_CLICK.getKey(), Sound.Source.BLOCK, 20, 1));
        player.closeInventory();
        player.getInventory().clear();
        player.setHealthScale(20);

    }


    public static boolean hasContact(NetPlayer player, Material material) {
        Player bukkitPlayer = Bukkit.getPlayer(player.getUniqueId());
        return bukkitPlayer.getLocation().add(0, -1, 0).getBlock().getType() == material;
    }

    public static boolean killPlayer(PlayerKill.KillInformation information) {
        if (MissileWars.GAME.isSpectatingPlayer(information.player().getUniqueId())) {
            return false;
        }
        Player player = Bukkit.getPlayer(information.player().getUniqueId());

        player.setHealth(0);
        if (information.killer() != null)
            player.setKiller(Bukkit.getPlayer(information.killer().getUniqueId()));

        Bukkit.broadcast(information.generateDeathMessage());

        return true;
    }
}
