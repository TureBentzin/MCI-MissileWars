package de.mcimpact.missilewars;

import com.onarandombox.MultiverseCore.MultiverseCore;
import de.mcimpact.core.Core;
import de.mcimpact.missilewars.game.MissileWarsGame;
import de.mcimpact.missilewars.listeners.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class MissileWars extends JavaPlugin {

    private static final Logger MISSILEWARS_LOGGER = Bukkit.getLogger();
    private static MultiverseCore multiverse = null;
    public static MissileWarsGame GAME = MissileWarsGame.getInstance();

    public static void broadcast(String key, String... args){
        Bukkit.broadcast(Core.translate(Core.getTranslatableComponent(key, args)));
    }
    public static void broadcast(String key){
        Bukkit.broadcast(Core.translate(Core.getTranslatableComponent(key)));
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        MISSILEWARS_LOGGER.warning("Running MissileWars v." + getDescription().getVersion());
        MISSILEWARS_LOGGER.info("registering Translations");
        Translations.run();
        multiverse = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MISSILEWARS_LOGGER.info("stating listening to events!");
        registerListeners();
        MISSILEWARS_LOGGER.info("all events are registered now!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
    }


}
