package de.mcimpact.missilewars.game.world;

import de.mcimpact.missilewars.MissileWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.StringJoiner;
import java.util.function.Function;

public class MissileWarsLevel {

    private World world;
    private MissileWarsLevelData data;

    protected MissileWarsLevel(World world, MissileWarsLevelData data) {
        this.world = world;
        this.data = data;
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
        return elocation.function.apply(locationType.function.apply(data));
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
}
