package de.mcimpact.missilewars.game.world;

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

    private final MultiverseWorld multiverseWorld;
    private World world;
    private MissileWarsLevelData data;

    protected MissileWarsLevel(World world, MissileWarsLevelData data) {
        this.world = world;
        this.data = data;
        this.multiverseWorld = MissileWars.getMultiverse().getMVWorldManager().getMVWorld(world.getName());
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

        MissileWars.GAME.getTeams()[0].getUuids().forEach(uuid -> {
            Location location = completeLocation(ELocation.FIRST, LocationType.SPAWN);
            location.setWorld(getWorld());
            Bukkit.getPlayer(uuid).teleport(location);
        });
        MissileWars.GAME.getTeams()[1].getUuids().forEach(uuid -> {
            Location location = completeLocation(ELocation.SECOND, LocationType.SPAWN);
            location.setWorld(getWorld());
            Bukkit.getPlayer(uuid).teleport(location);
        });

    }

    /**
     * @param player that will be a
     */
    public void sendIndividualPlayer(Player player) {
        if (MissileWars.GAME.teamer.getTeam(player.getUniqueId()) == MissileWars.GAME.getTeams()[0]) {
            Location location = completeLocation(ELocation.FIRST, LocationType.SPAWN);
            location.setWorld(MissileWars.GAME.getMissileWarsLevel().getWorld());
            player.teleport(location);
        } else if (MissileWars.GAME.teamer.getTeam(player.getUniqueId()) == MissileWars.GAME.getTeams()[1]) {
            player.teleport(completeLocation(ELocation.SECOND, LocationType.SPAWN));
        } else {
            MissileWars.getMWL().info("can´t detect native spawn for: " + player.getName() + "; proceeding with game spawn!");
            player.teleport(MissileWars.GAME.getSpawnOfPlayer(player));
        }
    }

    protected Location completeLocation(ELocation elocation, LocationType locationType) {
        return elocation.function.apply(locationType.function.apply(data)).setDirection(getLocationVector(elocation));
    }

    protected Vector getLocationVector(ELocation eLocation) {
        Location location = eLocation.function.apply(getData().getSpawnLocationPair());
        Location secondLocation = null;
        for (ELocation value : ELocation.values()) {
            if (value != eLocation) {
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

    protected void setupMVWorld() {
        getMultiverseWorld().allowPortalMaking(AllowedPortalType.NONE);
        getMultiverseWorld().setAllowFlight(false);
        getMultiverseWorld().setAutoHeal(true);
        getMultiverseWorld().setBedRespawn(false);
        getMultiverseWorld().setDifficulty(Difficulty.PEACEFUL);
        getMultiverseWorld().setEnableWeather(false);
        getMultiverseWorld().setGameMode(GameMode.SURVIVAL);
        getMultiverseWorld().setPVPMode(true);
        System.out.println(getMultiverseWorld().getName() + " was setup as a MissileWarsLevel!");
    }

    private enum ELocation {

        FIRST(LocationPair::getFirst),
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
}
