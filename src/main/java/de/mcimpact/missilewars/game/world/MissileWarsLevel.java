package de.mcimpact.missilewars.game.world;

import com.onarandombox.MultiverseCore.api.MultiversePlugin;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import com.onarandombox.MultiverseCore.enums.AllowedPortalType;
import de.mcimpact.missilewars.MissileWars;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.StringJoiner;
import java.util.function.Function;

public class MissileWarsLevel {

    private World world;
    private MissileWarsLevelData data;

    private MultiverseWorld multiverseWorld;

    protected MissileWarsLevel(World world, MissileWarsLevelData data) {
        this.world = world;
        this.data = data;
        this.multiverseWorld = MissileWars.getMultiverse().getMVWorldManager().getMVWorld( world.getName());
        setupMVWorld();
    }

    public File worldFolder() {
        return world.getWorldFolder();
    }

    public MissileWarsLevelData getData() {
        return data;
    }

    public void setData(MissileWarsLevelData data) {
        this.data = data;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public MultiverseWorld getMultiverseWorld() {
        return multiverseWorld;
    }

    public void sendPlayers() {
      /*  MissileWars.GAME.teamer.getPlayers().forEach((uuid, teamColor) -> {
          Bukkit.getPlayer(uuid).teleport(completeLocation());
        });
       */

        MissileWars.GAME.getTeams()[0].getUuids().forEach(uuid -> Bukkit.getPlayer(uuid).teleport(completeLocation(ELocation.FIRST, LocationType.SPAWN)));
        MissileWars.GAME.getTeams()[1].getUuids().forEach(uuid -> Bukkit.getPlayer(uuid).teleport(completeLocation(ELocation.SECOND, LocationType.SPAWN)));

    }

    /**
     *
     * @param player that will be a
     */
    public void sendIndividualPlayer(Player player) {
       if( MissileWars.GAME.teamer.getTeam(player.getUniqueId()) == MissileWars.GAME.getTeams()[0]) {
           player.sendMessage("DEBUG: You are in Team 0");
           player.teleport(completeLocation(ELocation.FIRST, LocationType.SPAWN));
       }else if(MissileWars.GAME.teamer.getTeam(player.getUniqueId()) == MissileWars.GAME.getTeams()[1]){

           player.sendMessage("DEBUG: You are in Team 1");
           player.teleport(completeLocation(ELocation.SECOND, LocationType.SPAWN));
       }else {
           player.sendMessage("DEBUG: You are not part of a Team?!");
       }
    }

    protected Location completeLocation(ELocation elocation, LocationType locationType) {
        return elocation.function.apply(locationType.function.apply(data)).setDirection(getLocationVector(elocation));
    }

    protected Vector getLocationVector(ELocation eLocation) {
        Location location= eLocation.function.apply(getData().getSpawnLocationPair());
        Location secondLocation = null;
        for (ELocation value : ELocation.values()) {
            if(value != eLocation) {
                secondLocation = value.function.apply(getData().getSpawnLocationPair());
            }
        }
        Vector firstVector = location.toVector();
        Vector vector = secondLocation.toVector().subtract(firstVector);


        return vector;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MissileWarsLevel.class.getSimpleName() + "[", "]")
                .add("world=" + world.getName())
                .add("data=" + data)
                .toString();
    }

    private enum ELocation {

        FIRST(LocationPair::getFist),
        SECOND(LocationPair::getSecond);

        Function<LocationPair, Location> function;

        ELocation(Function<LocationPair, Location> locationFunction) {
            this.function = locationFunction;
        }


    }

    private enum LocationType {

        SPAWN(MissileWarsLevelData::getSpawnLocationPair),
        PORTAL(MissileWarsLevelData::getSpawnLocationPair);

        public final Function<MissileWarsLevelData, LocationPair> function;

        LocationType(Function<MissileWarsLevelData, LocationPair> function) {
            this.function = function;
        }
    }

    protected void setupMVWorld() {
        getMultiverseWorld().allowPortalMaking( AllowedPortalType.NONE);
        getMultiverseWorld().setAllowFlight(false);
        getMultiverseWorld().setAutoHeal(true);
        getMultiverseWorld().setBedRespawn(false);
        getMultiverseWorld().setDifficulty(Difficulty.PEACEFUL);
        getMultiverseWorld().setEnableWeather(false);
        getMultiverseWorld().setGameMode(GameMode.SURVIVAL);
        getMultiverseWorld().setPVPMode(true);
        System.out.println(getMultiverseWorld().getName() + " was setup as a MissileWarsLevel!");
    }
}
