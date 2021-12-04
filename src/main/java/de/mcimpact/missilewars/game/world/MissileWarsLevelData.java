package de.mcimpact.missilewars.game.world;

import de.mcimpact.core.util.Pair;

import java.io.Serializable;
import java.util.StringJoiner;

public class MissileWarsLevelData implements Serializable {

    private final String levelname;
    /**
     * spawnLocation of team 1 and 2
     */
    private final LocationPair spawnLocationPair;
    /**
     * Where is portal is checked for each team
     */
    private final LocationPair portalCheckLocations;
    /**
     * Where the walls on the x coordinate are
     */
    private final Pair<Double> wallsX;
    /**
     * Where the walls on the x coordinate are
     */
    private final Pair<Double> wallsZ;
    /**
     * Where the wall on the y coordinate are
     * = Max height
     */
    private final Double wallY;

    private final int size;

    public MissileWarsLevelData(String levelname, LocationPair spawnLocationPair, LocationPair portalCheckLocations, int size, Pair<Double> wallsX, Pair<Double> wallsZ, Double wallY) {
        this.levelname = levelname;
        this.spawnLocationPair = spawnLocationPair;
        this.portalCheckLocations = portalCheckLocations;
        this.size = size;
        this.wallsX = wallsX;
        this.wallsZ = wallsZ;
        this.wallY = wallY;
    }

    public int getSize() {
        return size;
    }


    public LocationPair getPortalCheckLocations() {
        return portalCheckLocations;
    }

    public LocationPair getSpawnLocationPair() {
        return spawnLocationPair;
    }

    public String getLevelname() {
        return levelname;
    }

    public Pair<Double> getWallsX() {
        return wallsX;
    }

    public Pair<Double> getWallsZ() {
        return wallsZ;
    }

    public Double getWallY() {
        return wallY;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MissileWarsLevelData.class.getSimpleName() + "[", "]")
                .add("levelname='" + levelname + "'")
                .add("spawnLocationPair=" + spawnLocationPair)
                .add("portalCheckLocations=" + portalCheckLocations)
                .add("size=" + size)
                .toString();
    }
}
