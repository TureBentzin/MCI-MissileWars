package de.mcimpact.missilewars;

import com.onarandombox.MultiverseCore.MultiverseCore;
import de.mcimpact.core.Core;
import de.mcimpact.core.cloud.CloudCore;
import de.mcimpact.missilewars.game.GameStatus;
import de.mcimpact.missilewars.game.MissileWarsGame;
import de.mcimpact.missilewars.listeners.JoinEvent;
import de.mcimpact.missilewars.listeners.QuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public final class MissileWars extends JavaPlugin {




    @NotNull
    private World lobby = Bukkit.getWorld("world");
    @NotNull
    public World getLobby() {
        return lobby;
    }

    private static Logger MISSILEWARS_LOGGER = Logger.getLogger("missilewars-nullman");
    private static MultiverseCore multiverse = null;
    public static MissileWarsGame GAME = MissileWarsGame.getInstance();

    public static Logger getMissilewarsLogger() {
        return MISSILEWARS_LOGGER;
    }
    public static Logger getMWL() {
        return MISSILEWARS_LOGGER;
    }
    public static MultiverseCore getMultiverse() {
        return multiverse;
    }

    public static void broadcast(String key, String... args){
        Bukkit.broadcast(Core.translate(Core.getTranslatableComponent(key, Translations.toObjectArray(args))));
    }
    public static void broadcast(String key){
        Bukkit.broadcast(Core.translate(Core.getTranslatableComponent(key)));
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        MISSILEWARS_LOGGER = getLogger();


        MISSILEWARS_LOGGER.warning("Running MissileWars v." + getDescription().getVersion());
        MISSILEWARS_LOGGER.info("registering Translations");
        Translations.run();
        multiverse = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MISSILEWARS_LOGGER.info("stating listening to events!");
        registerListeners();
        MISSILEWARS_LOGGER.info("all events are registered now!");
        MISSILEWARS_LOGGER.info("detected lobby world: "+ getLobby().getName());




        MISSILEWARS_LOGGER.info("The Game is now open!");
        GAME.setGameStatus(GameStatus.LOBBY);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
    }


}
