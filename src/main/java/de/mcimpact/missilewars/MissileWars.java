package de.mcimpact.missilewars;

import com.onarandombox.MultiverseCore.MultiverseCore;
import de.mcimpact.core.Core;
import de.mcimpact.missilewars.commands.MissileWarsCommand;
import de.mcimpact.missilewars.game.MissileWarsGame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class MissileWars extends JavaPlugin {

    private static final Logger MISSILEWARS_LOGGER = Bukkit.getLogger();
    private static MultiverseCore multiverse = null;
    public static MissileWarsGame GAME = MissileWarsGame.getInstance();
    public static void broadcast(String... keysAndArgs){
        String[] args = new String[keysAndArgs.length -1];

        for (int i = 1; i < keysAndArgs.length; i++) {
          args[i] = keysAndArgs[i];
        }

        Bukkit.broadcast(Core.getTranslatableComponent(keysAndArgs[0], args));
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        MISSILEWARS_LOGGER.warning("Running MissileWars v." + getDescription().getVersion());
        multiverse = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        Core.registerCommand(new MissileWarsCommand(), this);



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}
