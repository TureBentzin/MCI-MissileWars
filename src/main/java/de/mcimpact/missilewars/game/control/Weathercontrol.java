package de.mcimpact.missilewars.game.control;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class Weathercontrol {

    public static boolean allow_storm = false;

    public static boolean isAllow_storm() {
        return allow_storm;
    }

    public void setRain(boolean b) {
        for (World world : Bukkit.getWorlds()) {
            world.setStorm(b);
        }
    }

}
