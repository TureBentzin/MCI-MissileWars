package de.mcimpact.missilewars.game.world;

import org.bukkit.World;

public class MissileWarsLevel {
    private World world;
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
