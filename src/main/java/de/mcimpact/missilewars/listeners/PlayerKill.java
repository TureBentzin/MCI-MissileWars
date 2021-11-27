package de.mcimpact.missilewars.listeners;

import de.mcimpact.core.Core;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.gamephase.GamePhase;
import de.mcimpact.missilewars.MissileWars;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.Nullable;

public class PlayerKill implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {


        Entity ent = event.getEntity();
        EntityDamageEvent ede = ent.getLastDamageCause();
        EntityDamageEvent.DamageCause cause = ede.getCause();
        event.deathMessage();
        event.setCancelled(true);


        if(MissileWars.GAME.isRunning())
            GamePhase.killPlayer( new KillInformation(Core.getPlayerUtils().getPlayer(ent.getUniqueId()), Component.text(event.getDeathMessage()),
                    Core.getPlayerUtils().get(event.getPlayer().getKiller().getUniqueId()), event.getPlayer().getLocation(), cause));
    }


    public static record KillInformation(
            NetPlayer player, Component deathMessage, @Nullable NetPlayer killer, Location killPosition,
            EntityDamageEvent.DamageCause deathCause)
    {

        /**
         * @param player
         * @param deathCause
         * @return a KillInformation for a simple kill
         */
        public static KillInformation generateEasyKillInformation(NetPlayer player, EntityDamageEvent.DamageCause deathCause) {
            return new KillInformation(player, Component.text(""), null, Bukkit.getPlayer(player.getUniqueId()).getLocation(), deathCause);
        }

        @Override
        public EntityDamageEvent.DamageCause deathCause() {
            return deathCause;
        }

        @Override
        public Component deathMessage() {
            return deathMessage;
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
        public @Nullable NetPlayer killer() {
            return killer;
        }

    }
}
