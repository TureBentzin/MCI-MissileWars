package de.mcimpact.missilewars;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import de.mcimpact.core.Core;
import de.mcimpact.core.internal.Untested;
import de.mcimpact.missilewars.commands.MissileWarsCommand;
import de.mcimpact.missilewars.commands.StartCommand;
import de.mcimpact.missilewars.commands.bukkit.DebugteamCommand;
import de.mcimpact.missilewars.commands.bukkit.TasksCommand;
import de.mcimpact.missilewars.game.GameStatus;
import de.mcimpact.missilewars.game.MissileWarsGame;
import de.mcimpact.missilewars.game.items.ItemManager;
import de.mcimpact.missilewars.game.items.shop.ShopManager;
import de.mcimpact.missilewars.game.world.LevelManager;
import de.mcimpact.missilewars.listeners.*;
import de.mcimpact.missilewars.lobbyphase.LobbyPhase;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.initialization.qual.UnknownInitialization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;


public final class MissileWars extends JavaPlugin {


    private static final LevelManager levelManager = LevelManager.getInstance();
    public static MissileWarsGame GAME = MissileWarsGame.getInstance();
    @Nullable
    private static MissileWars missileWars;
    private static Logger MISSILEWARS_LOGGER = Logger.getLogger("missilewars-nullman");
    private static MultiverseCore multiverse = null;
    @UnknownInitialization
    private static MultiverseWorld lobby;

    private static final ItemManager itemManager = ItemManager.getInstance();
    private static final ShopManager shopManager = new ShopManager();

    @NotNull
    public static ShopManager getShopManager() {
        return shopManager;
    }

    @NotNull
    public static ItemManager getItemManager() {
        return itemManager;
    }

    public static LevelManager getLevelManager() {
        return levelManager;
    }

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

    @Untested
    public static void broadcast(String key, Object... objects) {
        // Bukkit.broadcast(Core.translate(Core.getTranslatableComponent(key, objects)));
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Core.getPlayerUtils().getPlayer(onlinePlayer.getUniqueId()).sendMessage(Core.getTranslatableComponent(key, objects));
        }
        getMWL().info(LegacyComponentSerializer.legacyAmpersand().serialize(Core.translate(Core.getTranslatableComponent(key, objects))));

    }

    public static void broadcast(String key, String... args) {
        // Bukkit.broadcast(Core.translate(Core.getTranslatableComponent(key, Translations.toObjectArray(args))));
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Core.getPlayerUtils().getPlayer(onlinePlayer.getUniqueId()).sendMessage(Core.getTranslatableComponent(key, Translations.toObjectArray(args)));
        }
        getMWL().info(LegacyComponentSerializer.legacyAmpersand().serialize(Core.translate(Core.getTranslatableComponent(key, Translations.toObjectArray(args)))));

    }

    public static void broadcast(String key, String arg) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Core.getPlayerUtils().getPlayer(onlinePlayer.getUniqueId()).sendMessage(Core.getTranslatableComponent(key, arg));
        }
        getMWL().info(LegacyComponentSerializer.legacyAmpersand().serialize(Core.translate(Core.getTranslatableComponent(key, arg))));
    }

    public static void broadcast(String key) {
        Bukkit.broadcast(Core.translate(Core.getTranslatableComponent(key)));
    }

    public static MultiverseWorld getLobby() {
        return lobby;
    }

    @Override
    public void onEnable() {

        missileWars = this;

        // Plugin startup logic
        MISSILEWARS_LOGGER = getLogger();
        // World bukkitlobby = Bukkit.getWorld("world");

        MISSILEWARS_LOGGER.warning("Running MissileWars v." + getDescription().getVersion());
        MISSILEWARS_LOGGER.info("registering Translations");
        Translations.run();
        multiverse = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        lobby = getMultiverse().getMVWorldManager().getMVWorld("world");
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
        GAME.setGameStatus(GameStatus.LOBBY); //Define the GameStatus

        Bukkit.getServer().setMaxPlayers(16);

        LobbyPhase.onLobbyPhase(getLobby());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        GAME.stop();
    }


    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new WeatherChange(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamage(), this);
        Bukkit.getPluginManager().registerEvents(new FlightAttempt(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
        Bukkit.getPluginManager().registerEvents(new TNTExplosion(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerKill(), this);
        Bukkit.getPluginManager().registerEvents(new ClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new UtilityEvent(), this);

        Bukkit.getPluginManager().registerEvents(MissileWars.GAME, this); //register internal GameEvents

    }

    public void registerBukkitCommands() {
        getCommand("debugteam").setExecutor(new DebugteamCommand());
        getCommand("tasks").setExecutor(new TasksCommand());
    }

    public void registerCommands() {
        Core.registerCommand(new MissileWarsCommand(), this);
        Core.registerCommand(new StartCommand(), this);
    }


}
