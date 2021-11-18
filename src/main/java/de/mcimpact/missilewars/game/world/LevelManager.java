package de.mcimpact.missilewars.game.world;

import java.util.HashMap;
import java.util.Map;

public class LevelManager {

    private Map<String, MissileWarsLevel> missileWarsLevelMap = new HashMap<>();
    public Map<String, MissileWarsLevel> getMissileWarsLevelMap() {
        return missileWarsLevelMap;
    }


}
