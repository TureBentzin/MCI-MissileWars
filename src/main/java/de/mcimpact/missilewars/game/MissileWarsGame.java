package de.mcimpact.missilewars.game;

import de.mcimpact.core.Core;
import de.mcimpact.core.game.Game;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.game.team.Team;
import de.mcimpact.game.team.Teamer;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.world.MissileWarsLevel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;

import javax.annotation.Nullable;
import javax.imageio.stream.MemoryCacheImageInputStream;

public class MissileWarsGame extends Game {


    private int players = 0;

    public int getPlayers() {
        return players;
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


    @Nullable
    private MissileWarsLevel missileWarsLevel;
    private boolean running;
    private GameStatus gameStatus = GameStatus.PAUSED;
    @Nullable
    private Team[] teams;

    public static MissileWarsGame getInstance() {
        return instance;
    }

    @Nullable
    public MissileWarsLevel getMissileWarsLevel() {
        return missileWarsLevel;
    }

    public void setMissileWarsLevel(@Nullable MissileWarsLevel missileWarsLevel) {
        this.missileWarsLevel = missileWarsLevel;
    }

    public boolean isRunning() {
        return running;
    }

    private void setRunning(boolean running) {
        this.running = running;
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
        players ++;
    }

    public void removePlayer(NetPlayer netPlayer) {
        getTeamer().remove(netPlayer);
        players --;
    }



    @Override
    public void start() {

        teamer.finalize();
        setTeams(teamer.getTeams());
        MissileWars.broadcast("missilewars.message.start");
        MissileWarsLevel level = MissileWars.getLevelManager().selectRandomLevel();
        MissileWars.broadcast("missilewars.message.start.level", level.getData().getLevelname());
        getMissileWarsLevel().sendPlayers();


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
        setRunning(false);
    }
}
