package de.mcimpact.core.game;

public abstract class Game {

    public abstract void start();

    protected abstract <T> void internalStart(T startArgument);

    public abstract void stop();
}
