package de.mcimpact.missilewars.game;

import de.mcimpact.core.game.Game;
import de.mcimpact.game.team.Team;
import de.mcimpact.game.team.Teamer;
import de.mcimpact.missilewars.game.world.MissileWarsLevel;

import javax.annotation.Nullable;

public class MissileWarsGame extends Game {


    public static MissileWarsGame instance = new MissileWarsGame();
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

    public Teamer getTeamer() {
        return teamer;
    }

    public void setTeamer(Teamer teamer) {
        this.teamer = teamer;
    }

    @Override
    public void start() {
        setGameStatus(GameStatus.LOBBY);

        internalStart("servername");
    }

    @Override
    protected <T> void internalStart(T startArgument) {

        setRunning(true);
    }

    @Override
    public void stop() {


        setRunning(false);
    }
}
