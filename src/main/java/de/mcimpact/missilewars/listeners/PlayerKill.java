package de.mcimpact.missilewars.listeners;

import com.destroystokyo.paper.event.profile.ProfileWhitelistVerifyEvent;
import de.mcimpact.core.players.NetPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.net.http.WebSocket;
import java.util.StringJoiner;

public class PlayerKill implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){

        Entity ent = event.getEntity();
        EntityDamageEvent ede = ent.getLastDamageCause();
        EntityDamageEvent.DamageCause cause = ede.getCause();
        event.deathMessage();
        event.setCancelled(true);
    }



    public static record KillInformation(
            NetPlayer player, TextComponent deathMessage, @Nullable NetPlayer killer, Location killPosition, EntityDamageEvent.DamageCause deathCause)
    {

        /**
         *
         * @param player
         * @param deathCause
         * @return a KillInformation for a simple kill
         */
        public static KillInformation generateEasyKillInformation(NetPlayer player, EntityDamageEvent.DamageCause deathCause) {
            return new KillInformation(player, new TextComponent(""), null, Bukkit.getPlayer(player.getUniqueId()).getLocation(), deathCause);
        }

        @Override
        public TextComponent deathMessage() {
            return deathMessage;
        }

        @Override
        public EntityDamageEvent.DamageCause deathCause() {
            return deathCause;
        }

        @Override
        public NetPlayer player() {
            return player;
        }

        @Override
        public Location killPosition() {
            return killPosition;
        }

        @Override
        public NetPlayer killer() {
            return killer;
        }

    }
}
