package de.mcimpact.missilewars.game;

import de.mcimpact.core.game.Game;

public class MissileWarsGame extends Game {

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


    @Override
    public  void start() {
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
