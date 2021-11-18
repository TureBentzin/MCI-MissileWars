package de.mcimpact.missilewars;

import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class MissileWarsPlugin extends JavaPlugin {

    private static final Logger MISSILEWARS_LOGGER = Bukkit.getLogger();

    private static MultiverseCore multiverse = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        MISSILEWARS_LOGGER.warning("Running MissileWars v." + getDescription().getVersion());
        multiverse = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}
