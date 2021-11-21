package de.mcimpact.missilewars.game.world;

import org.bukkit.World;

import java.io.File;

public class MissileWarsLevel {
    private World world;

    public File worldFolder() {return world.getWorldFolder();}

    private MissileWarsLevelData data;

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
}
