package de.mcimpact.missilewars;

import com.onarandombox.MultiverseCore.MultiverseCore;
import de.mcimpact.core.Core;
import de.mcimpact.missilewars.commands.MissileWarsCommand;
import de.mcimpact.missilewars.commands.bukkit.DebugteamCommand;
import de.mcimpact.missilewars.game.GameStatus;
import de.mcimpact.missilewars.game.MissileWarsGame;
import de.mcimpact.missilewars.listeners.*;
import de.mcimpact.missilewars.lobbyphase.LobbyPhase;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;


public final class MissileWars extends JavaPlugin {


    public static MissileWarsGame GAME = MissileWarsGame.getInstance();
    @Nullable
    private static MissileWars missileWars;
    private static Logger MISSILEWARS_LOGGER = Logger.getLogger("missilewars-nullman");
    private static MultiverseCore multiverse = null;
    @UnknownInitialization
    private static World lobby;

    @Nullable
    public static MissileWars getMissileWars() {
        return missileWars;
    }

    public static Logger getMissilewarsLogger() {
        return MISSILEWARS_LOGGER;
    }

    public static Logger getMWL() {
        return MISSILEWARS_LOGGER;
    }

    public static MultiverseCore getMultiverse() {
        return multiverse;
    }

    public static void broadcast(String key, String... args) {
        Bukkit.broadcast(Core.translate(Core.getTranslatableComponent(key, Translations.toObjectArray(args))));
    }

    public static void broadcast(String key, String arg) {
        Bukkit.broadcast(Core.translate(Core.getTranslatableComponent(key, arg)));
    }

    public static void broadcast(String key) {
        Bukkit.broadcast(Core.translate(Core.getTranslatableComponent(key)));
    }

    public static World getLobby() {
        return lobby;
    }

    @Override
    public void onEnable() {

        missileWars = this;

        // Plugin startup logic
        MISSILEWARS_LOGGER = getLogger();
        lobby = Bukkit.getWorld("world");

        MISSILEWARS_LOGGER.warning("Running MissileWars v." + getDescription().getVersion());
        MISSILEWARS_LOGGER.info("registering Translations");
        Translations.run();
        multiverse = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MISSILEWARS_LOGGER.info("stating listening to events!");
        registerListeners();
        MISSILEWARS_LOGGER.info("all events are registered now!");

        MISSILEWARS_LOGGER.info("starting commandregisteration : Bukkit");
        registerBukkitCommands();
        MISSILEWARS_LOGGER.info("finished commandregisteration : Bukkit");

        MISSILEWARS_LOGGER.info("starting commandregisteration : Core");
        registerCommands();
        MISSILEWARS_LOGGER.info("finished commandregisteration : Core");

        MISSILEWARS_LOGGER.info("detected lobby world: " + getLobby().getName());


        MISSILEWARS_LOGGER.info("The Game is now open!");
        GAME.setGameStatus(GameStatus.LOBBY);
        LobbyPhase.onLobbyPhase(getLobby());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        MISSILEWARS_LOGGER.info("The Game is closing...");
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.kick(Core.translate(Core.getTranslatableComponent("missilewars.message.game.aborted")));
        }
        GAME.setGameStatus(GameStatus.GAME_END);
    }


    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new WeatherChange(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamage(), this);
        Bukkit.getPluginManager().registerEvents(new FlightAttempt(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
    }

    public void registerBukkitCommands() {
        getCommand("debugteam").setExecutor(new DebugteamCommand());
    }

    public void registerCommands() {
        Core.registerCommand(new MissileWarsCommand(), this);
    }


}
