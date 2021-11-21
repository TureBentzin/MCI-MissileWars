package de.mcimpact.missilewars.game.world;

import de.mcimpact.core.util.Utils;
import de.mcimpact.missilewars.game.world.definition.LevelMetaManager;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LevelManager {

    private final Map<String, MissileWarsLevel> missileWarsLevelMap = new HashMap<>();

    public Map<String, MissileWarsLevel> getMissileWarsLevelMap() {
        return missileWarsLevelMap;
    }

    public MissileWarsLevel getRandomLevel() {

        String[] strings = new String[missileWarsLevelMap.keySet().size()];

        for (int i = 0; i < missileWarsLevelMap.keySet().size(); i++) {
            strings[i] = (String) missileWarsLevelMap.keySet().toArray()[i];
        }

        Random r = new Random();
        int randomNumber = r.nextInt(strings.length);

        return missileWarsLevelMap.get(strings[randomNumber]);
    }


    public void scanForLevels() {
        File folder = Bukkit.getServer().getWorldContainer();

        File[] potentialLevels = Utils.stringArrayToFolders(folder.getPath(), Utils.getSubdirectories(folder.getPath()));

        for (File potentialLevel : potentialLevels) {

                if(LevelMetaManager.isLevel(potentialLevel)) {
                    LevelMetaManager metaManager = new LevelMetaManager(potentialLevel);

                    System.out.println("Found Level: " + potentialLevel);

                   getMissileWarsLevelMap().put(potentialLevel.getName(),
                            new MissileWarsLevel(Bukkit.getWorld(potentialLevel.getName()),
                                    metaManager.toMissileWarsLevelData()));

                    System.out.println("Mapped Level: " + getMissileWarsLevelMap().get(potentialLevel.getName()));
                }

        }
    }

    public static LevelManager getInstance() {
        return manager;
    }
    private static LevelManager manager = new LevelManager();
}
