package de.mcimpact.missilewars.game.world.definition;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import de.mcimpact.core.util.Pair;
import de.mcimpact.missilewars.game.world.LocationPair;
import de.mcimpact.missilewars.game.world.MissileWarsLevelData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;


public class LevelMetaManager {

    public static final String NAME = "levelmeta.conf";


    private final File file;

    private Config config;
    private ConfigFactory factory;

    public LevelMetaManager(File level) {
        file = new File(level, NAME);
        if (!file.exists()) return;

        config = ConfigFactory.parseFile(file);
    }

    public static boolean isLevel(File folder) {
        return new File(folder, NAME).exists();
    }

    public void manage() {

    }

    public Config getConfig() {
        return config;
    }

    public MissileWarsLevelData toMissileWarsLevelData() {

        return new MissileWarsLevelData(config.getString("levelname"), getLocationPair("spawn"), getLocationPair("portal"), config.getInt("size"), getWalls("walls.x"), getWalls("walls.z"), config.getDouble("walls.y"));
    }

    public LocationPair getLocationPair(String key) {
        return new LocationPair(getLocation(key + ".0"), getLocation(key + ".1"));
    }

    public Location getLocation(String key) {
        double x, y, z;

        x = config.getDouble(key + ".X");
        y = config.getDouble(key + ".Y");
        z = config.getDouble(key + ".Z");

        return new Location(getWorld(), x, y, z);
    }

    public Pair<Double> getWalls(String key) {
        return new Pair<>(config.getDouble(key + ".highest"), config.getDouble(key + ".lowest"));
    }

    public World getWorld() {
        return Bukkit.getWorld(file.getParentFile().getName());
    }
}
