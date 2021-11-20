package de.mcimpact.missilewars.game.control;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class Weathercontrol {

    public void setRain(boolean b) {
        for (World world : Bukkit.getWorlds()) {
            world.setStorm(b);
        }
    }

}
