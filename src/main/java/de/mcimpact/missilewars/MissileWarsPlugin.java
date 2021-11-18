package de.mcimpact.missilewars;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class MissileWarsPlugin extends JavaPlugin {

    private static final Logger MISSILEWARS_LOGGER = Bukkit.getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic
        MISSILEWARS_LOGGER.warning("Running MissileWars v." + getDescription().getVersion());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
