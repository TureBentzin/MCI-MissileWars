package de.mcimpact.missilewars.listeners;

import de.mcimpact.missilewars.game.control.Weathercontrol;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;


public class WeatherChange implements Listener {


    @EventHandler
    public void onChange(WeatherChangeEvent event){
        if(event.toWeatherState() && !Weathercontrol.allow_storm)
        event.setCancelled(true);
    }
}
