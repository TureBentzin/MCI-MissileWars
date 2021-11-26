package de.mcimpact.missilewars.game;

import de.mcimpact.core.Core;
import de.mcimpact.core.game.Game;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.game.team.Team;
import de.mcimpact.game.team.Teamer;
import de.mcimpact.gamephase.GamePhase;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.world.MissileWarsLevel;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.net.http.WebSocket;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

public class MissileWarsGame extends Game implements Listener {

    public static void staticstart() {
        getInstance().start();
    }

    public static MissileWarsGame instance = new MissileWarsGame();
    /**
     * DONT USE:
     * teamer.add();
     * teamer.remove();
     *
     * @see MissileWarsGame
     */
    public Teamer teamer = new Teamer(2);
    private int players = 0;
    @Nullable
    private MissileWarsLevel missileWarsLevel;
    private GameStatus gameStatus = GameStatus.PAUSED;
    @Nullable
    private Team[] teams;

    public static MissileWarsGame getInstance() {
        return instance;
    }

    public int getPlayers() {
        return players;
    }

    @Nullable
    public MissileWarsLevel getMissileWarsLevel() {
        return missileWarsLevel;
    }

    public void setMissileWarsLevel(@Nullable MissileWarsLevel missileWarsLevel) {
        this.missileWarsLevel = missileWarsLevel;
    }

    public boolean isRunning() {
        return gameStatus == GameStatus.GAME;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Nullable
    public Team[] getTeams() {
        return teams;
    }

    public void setTeams(@Nullable Team[] teams) {
        this.teams = teams;
    }

    private Teamer getTeamer() {
        return teamer;
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion = "1.0")
    private void setTeamer(Teamer teamer) {
        this.teamer = teamer;
    }

    public void addPlayer(NetPlayer netPlayer) {
        getTeamer().add(netPlayer);
        players++;
    }

    public void removePlayer(NetPlayer netPlayer) {
        getTeamer().remove(netPlayer);
        players--;
    }

    public void forPlayers(Consumer<? super Player> consumer) {
        onlinePlayers.forEach(consumer);
    }

    public Set<Player> onlinePlayers = new HashSet<>();

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        if(gameStatus != GameStatus.GAME)
            return;
        NetPlayer player = Core.getPlayerUtils().getPlayer(event.getPlayer().getUniqueId());
        if(onlinePlayers.contains(event.getPlayer())) {
            onlinePlayers.remove(event.getPlayer());
            teamer.getTeam(event.getPlayer().getUniqueId()).removeMember(player);

            MissileWars.broadcast("missilewars.message.movement.disconnect", player.getName(),
                    Component.text(teamer.getTeam(player).getColor().name()).color(teamer.getTeam(player).getColor().getTextColor().adventure));
        }
    }
    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        NetPlayer player = Core.getPlayerUtils().getPlayer(event.getPlayer().getUniqueId());
        if(isRunning())
        if(!onlinePlayers.contains(event.getPlayer())) {

            getMissileWarsLevel().sendIndividualPlayer(event.getPlayer());
            if (teamer.getTeam(event.getPlayer().getUniqueId()) != null) {
                onlinePlayers.add(event.getPlayer());
                
                teamer.getTeam(event.getPlayer().getUniqueId()).addMember(event.getPlayer().getUniqueId());
                GamePhase.phasePlayer(event.getPlayer());
                MissileWars.broadcast("missilewars.message.movement.rejoined",
                        event.getPlayer().getName(),
                        Component.text(teamer.getTeam(player).getColor().name()).color(teamer.getTeam(player).getColor().getTextColor().adventure));

            }else{
                player.sendMessage("You are a spectator?!");
                GamePhase.phaseSpectator(event.getPlayer());
            }
        }else {
            player.sendMessage("something realy went wrong - please report this!");
        }
    }

    public Set<Player> getOnlinePlayers() {
        return onlinePlayers;
    }

    @Override
    public void start() {

        teamer.finalize();
        setTeams(teamer.getTeams());
        teamer.getPlayers().forEach((uuid, teamColor) -> onlinePlayers.add(Bukkit.getPlayer(uuid)));
        MissileWars.broadcast("missilewars.message.start");
        MissileWarsLevel level = MissileWars.getLevelManager().selectRandomLevel();
        MissileWars.broadcast("missilewars.message.start.level", level.getData().getLevelname());
        getMissileWarsLevel().sendPlayers();
        MissileWars.GAME.setGameStatus(GameStatus.GAME);

        level.getWorld().setTime(2000);
        level.getWorld().setViewDistance(32);

        getOnlinePlayers().forEach( player -> GamePhase.phasePlayer(player));


    }

    @Override
    protected <T> void internalStart(T startArgument) {

    }

    @Override
    public void stop() {
        MissileWars.getMWL().info("The Game is closing...");
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.kick(Core.translate(Core.getTranslatableComponent("missilewars.message.game.aborted")));
        }
        setGameStatus(GameStatus.GAME_END);
    }
}
