package de.mcimpact.missilewars.game;

import de.mcimpact.core.Core;
import de.mcimpact.core.game.Game;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.game.team.Team;
import de.mcimpact.game.team.Teamer;
import de.mcimpact.gamephase.GamePhase;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.items.ItemManager;
import de.mcimpact.missilewars.game.items.Items;
import de.mcimpact.missilewars.game.items.items.ItemFireball;
import de.mcimpact.missilewars.game.world.MissileWarsLevel;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

public class MissileWarsGame extends Game implements Listener {

    public static MissileWarsGame instance = new MissileWarsGame();
    /**
     * DONT USE:
     * teamer.add();
     * teamer.remove();
     *
     * @see MissileWarsGame
     */
    public Teamer teamer = new Teamer(2);
    public Set<Player> onlinePlayers = new HashSet<>();
    private int players = 0;
    @Nullable
    private MissileWarsLevel missileWarsLevel;
    private GameStatus gameStatus = GameStatus.PAUSED;
    @Nullable
    private Team[] teams;

    public static void staticstart() {
        getInstance().start();
    }

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

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        if (gameStatus != GameStatus.GAME)
            return;
        NetPlayer player = Core.getPlayerUtils().getPlayer(event.getPlayer().getUniqueId());
        if (onlinePlayers.contains(event.getPlayer())) {
            onlinePlayers.remove(event.getPlayer());
            teamer.getTeam(event.getPlayer().getUniqueId()).removeMember(player);

            MissileWars.broadcast("missilewars.message.movement.disconnect", player.getName(),
                    Component.text(teamer.getTeam(player).getColor().name()).color(teamer.getTeam(player).getColor().getTextColor().adventure));
        }
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        NetPlayer player = Core.getPlayerUtils().getPlayer(event.getPlayer().getUniqueId());
        if (isRunning())
            if (!onlinePlayers.contains(event.getPlayer())) {

                getMissileWarsLevel().sendIndividualPlayer(event.getPlayer());
                if (teamer.getTeam(event.getPlayer().getUniqueId()) != null) {
                    onlinePlayers.add(event.getPlayer());

                    teamer.getTeam(event.getPlayer().getUniqueId()).addMember(event.getPlayer().getUniqueId());
                    GamePhase.phasePlayer(event.getPlayer());
                    MissileWars.broadcast("missilewars.message.movement.rejoin",
                            event.getPlayer().getName(),
                            Component.text(teamer.getTeam(player).getColor().name()).color(teamer.getTeam(player).getColor().getTextColor().adventure));

                } else {
                    GamePhase.phaseSpectator(event.getPlayer());
                }
            } else {
                player.sendMessage(Core.getTranslatableComponent("missilewars.message.fatal"));
            }
    }

    public Set<Player> getOnlinePlayers() {
        return onlinePlayers;
    }

    public boolean isPlayingPlayer(NetPlayer netPlayer) {
        return isPlayingPlayer(netPlayer.getUniqueId());
    }

    public boolean isPlayingPlayer(UUID uuid) {
        return isPlayingPlayer(Bukkit.getPlayer(uuid));
    }

    public boolean isPlayingPlayer(Player player) {
        return getOnlinePlayers().contains(player);
    }

    /**
     * @param player
     * @return is the player is a spectator
     * @see MissileWarsGame#isPlayingPlayer(Player)
     */
    public boolean isSpectatingPlayer(Player player) {
        return !isPlayingPlayer(player);
    }

    /**
     * @param netplayer
     * @return is the player is a spectator
     * @see MissileWarsGame#isPlayingPlayer(NetPlayer)
     */
    public boolean isSpectatingPlayer(NetPlayer netplayer) {
        return !isPlayingPlayer(netplayer.getUniqueId());
    }

    /**
     * @param uuid
     * @return is the player is a spectator
     * @see MissileWarsGame#isPlayingPlayer(UUID)
     */
    public boolean isSpectatingPlayer(UUID uuid) {
        return !isPlayingPlayer(uuid);
    }

    public Location getSpwanOfPlayer(Player player) {
        if (isSpectatingPlayer(player)) return getMissileWarsLevel().getWorld().getSpawnLocation();
        if (isPlayingPlayer(player)) {
            int number = getPlayersTeamNumber(player.getUniqueId());
            if (number != 2) {
                if (number == 0) return getMissileWarsLevel().getData().getSpawnLocationPair().getFirst();
                if (number == 1) return getMissileWarsLevel().getData().getSpawnLocationPair().getSecond();
            } else {
                MissileWars.broadcast("missilewars.message.fatal");
                return getMissileWarsLevel().getWorld().getSpawnLocation();
            }
        }
        MissileWars.broadcast("missilewars.message.fatal");
        return getMissileWarsLevel().getWorld().getSpawnLocation();
    }

    /**
     * @param player
     * @return 0 or 1 for the Teams and 2 if there is no team!
     */
    public int getPlayersTeamNumber(NetPlayer player) {
        if (teamer.getTeam(player) == teamer.getTeams()[0]) return 0;
        if (teamer.getTeam(player) == teamer.getTeams()[1]) return 1;
        return 2;
    }

    /**
     * @param uuid
     * @return 0 or 1 for the Teams and 2 if there is no team!
     */
    public int getPlayersTeamNumber(UUID uuid) {
        return getPlayersTeamNumber(Core.getPlayerUtils().getPlayer(uuid));
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
        Bukkit.getServer().setMaxPlayers(30);

        getOnlinePlayers().forEach(player -> GamePhase.phasePlayer(player));
        Items.registerItems(MissileWars.getItemManager());

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
