package de.mcimpact.missilewars.game;

import de.mcimpact.core.game.Game;
import de.mcimpact.game.team.Team;
import de.mcimpact.game.team.Teamer;
import de.mcimpact.missilewars.game.world.MissileWarsLevel;


import javax.annotation.Nullable;

public class MissileWarsGame extends Game {


    @Nullable
    private MissileWarsLevel missileWarsLevel;
    @Nullable
    public MissileWarsLevel getMissileWarsLevel() {
        return missileWarsLevel;
    }
    public void setMissileWarsLevel(@Nullable MissileWarsLevel missileWarsLevel) {
        this.missileWarsLevel = missileWarsLevel;
    }

    private boolean running;
    public boolean isRunning() {
        return running;
    }
    private void setRunning(boolean running) {
        this.running = running;
    }

    private GameStatus gameStatus = GameStatus.PAUSED;
    public GameStatus getGameStatus() {
        return gameStatus;
    }
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Nullable
    private Team[] teams;
    @Nullable
    public Team[] getTeams() {
        return teams;
    }
    public void setTeams(@Nullable Team[] teams) {
        this.teams = teams;
    }

    public Teamer teamer = new Teamer(2);
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


    public static MissileWarsGame getInstance() {
        return instance;
    }

    public static MissileWarsGame instance = new MissileWarsGame();
}
