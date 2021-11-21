package de.mcimpact.missilewars.game.world;

import de.mcimpact.core.util.Pair;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class LocationPair extends Pair<Location> {
    public LocationPair(Location first, Location second) {
        super(first, second);
    }

    public double getDistance() {
        return getFist().distance(getSecond());
    }

    public Pair<Block> getBlocks() {
        return new Pair<>(getFist().getBlock(), getSecond().getBlock());
    }

   public Pair<Vector> getToVector() {
        return new Pair<>(getFist().toVector(), getSecond().toVector());
   }

   public boolean isLoaded(){
        return getFist().isChunkLoaded() && getSecond().isChunkLoaded();
   }


}
