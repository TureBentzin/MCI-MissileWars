package de.mcimpact.missilewars.game.world;

import java.io.Serializable;

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
    private final int size;

    public MissileWarsLevelData(String levelname, LocationPair spawnLocationPair, LocationPair portalCheckLocations, int size) {
        this.levelname = levelname;
        this.spawnLocationPair = spawnLocationPair;
        this.portalCheckLocations = portalCheckLocations;
        this.size = size;
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
}
