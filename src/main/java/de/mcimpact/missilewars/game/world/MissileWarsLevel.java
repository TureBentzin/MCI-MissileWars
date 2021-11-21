package de.mcimpact.missilewars.game.world;

import org.bukkit.World;

import java.io.File;
import java.util.StringJoiner;

public class MissileWarsLevel {

    private World world;
    private MissileWarsLevelData data;

    protected MissileWarsLevel(World world, MissileWarsLevelData data) {
        this.world = world;
        this.data = data;
    }

    public File worldFolder() {
        return world.getWorldFolder();
    }

    public MissileWarsLevelData getData() {
        return data;
    }

    public void setData(MissileWarsLevelData data) {
        this.data = data;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MissileWarsLevel.class.getSimpleName() + "[", "]")
                .add("world=" + world.getName())
                .add("data=" + data)
                .toString();
    }
}
