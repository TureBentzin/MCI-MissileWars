package de.mcimpact.missilewars.listeners;

import de.mcimpact.core.Core;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.gamephase.GamePhase;
import de.mcimpact.missilewars.MissileWars;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
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


        if (MissileWars.GAME.isRunning())
            if (event.getEntity().getKiller() != null) {
                GamePhase.killPlayer(new KillInformation(Core.getPlayerUtils().getPlayer(ent.getUniqueId()),
                        Core.getPlayerUtils().get(event.getEntity().getKiller().getUniqueId()), event.getEntity().getLocation(), cause));
            } else {
                GamePhase.killPlayer(new KillInformation(Core.getPlayerUtils().getPlayer(ent.getUniqueId()),
                        null, event.getEntity().getLocation(), cause));
            }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if(entity instanceof Player){
            Player player = (Player) entity;
          /* String message = "Damage: " + event.getCause().name() + "*" + event.getDamage() + " | " + (player.getHealth() - event.getDamage())+ "/" + player.getHealthScale();
            player.sendMessage(Core.translate(Core.getTranslatableComponent("missilewars.message.debug", message)));
           */
            if(MissileWars.GAME.isRunning())
                if(MissileWars.GAME.isPlayingPlayer(player)) {
                    if( (player.getHealth() - event.getDamage()) <= 0) {
                        player.teleport(MissileWars.GAME.getSpwanOfPlayer(player));
                    }
                }
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
         * @return <code>missilewars.message.explode</code> when the player blew up!
         * @return <code>missilewars.message.died</code> when the player died!
         * @return <code>missilewars.message.killed</code> when there is a <code>killer()</code>!
         */
        public Component generateDeathMessage() {
            if (deathCause == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || deathCause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                return Core.getTranslatableComponent("missilewars.message.explode", Component.text(player.getName()).color(MissileWars.GAME.teamer.getTeam(player).getColor().getTextColor().adventure));
            } else if (killer != null) {
                return Core.getTranslatableComponent("missilewars.message.killed", Component.text(player.getName()).color(MissileWars.GAME.teamer.getTeam(player).getColor().getTextColor().adventure), Component.text(killer.getName()).color(MissileWars.GAME.teamer.getTeam(killer).getColor().getTextColor().adventure));
            } else
                return Core.getTranslatableComponent("missilewars.message.died", Component.text(player.getName()).color(MissileWars.GAME.teamer.getTeam(player).getColor().getTextColor().adventure));
        }

    }
}
