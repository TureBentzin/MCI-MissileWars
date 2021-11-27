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
            if(event.getEntity().getKiller() != null)
            GamePhase.killPlayer( new KillInformation(Core.getPlayerUtils().getPlayer(ent.getUniqueId()),
                    Core.getPlayerUtils().get(event.getEntity().getKiller().getUniqueId()), event.getPlayer().getLocation(), cause));
            else {
                GamePhase.killPlayer( new KillInformation(Core.getPlayerUtils().getPlayer(ent.getUniqueId()),
                        null, event.getPlayer().getLocation(), cause));
            }
    }


    public static record KillInformation( 
            NetPlayer player, @Nullable NetPlayer killer, Location killPosition,
            EntityDamageEvent.DamageCause deathCause)
    {

        /**
         * @param player
         * @param deathCause
         * @return a KillInformation for a simple kill
         */
        public static KillInformation generateEasyKillInformation(NetPlayer player, EntityDamageEvent.DamageCause deathCause) {
            return new KillInformation(player, null, Bukkit.getPlayer(player.getUniqueId()).getLocation(), deathCause);
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
        public @Nullable NetPlayer killer() {
            return killer;
        }

        /**
         *
         * @return <code>missilewars.message.explode</code> when the player blew up!
         * @return <code>missilewars.message.died</code> when the player died!
         * @return <code>missilewars.message.killed</code> when there is a <code>killer()</code>!
         *
         */
        public Component generateDeathMessage() {
            if(deathCause == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || deathCause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                return Core.getTranslatableComponent("missilewars.message.explode", player.getName());
            }else if(killer != null) {
                return Core.getTranslatableComponent("missilewars.message.killed", player.getName(), killer);
            } else
                return Core.getTranslatableComponent("missilewars.message.died",player.getName());
        }

    }
}
