package de.mcimpact.missilewars.lobbyphase;

import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import de.mcimpact.missilewars.util.Timer;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class LobbyPhase {

    private static int secs = 20;
    private static final StartTimer startTimer = new StartTimer(secs);

    public static StartTimer getStartTimer() {
        return startTimer;
    }

    public static void onSpawn(NetPlayer netPlayer) {

        Player bukkitPlayer = Bukkit.getPlayer(netPlayer.getUniqueId());
        Location spawnLocation = MissileWars.getLobby().getCBWorld().getSpawnLocation();

        spawnLocation.getBlock().setType(Material.AIR);
        Location secondLocation = spawnLocation.clone();
        secondLocation.add(0, 1, 0);
        secondLocation.getBlock().setType(Material.AIR);

        bukkitPlayer.getInventory().clear();
        bukkitPlayer.setGameMode(GameMode.ADVENTURE);

        bukkitPlayer.teleport(spawnLocation);

        bukkitPlayer.setAllowFlight(true);
    }

    public static void onLobbyPhase(MultiverseWorld mvLobby) {

        World lobby = mvLobby.getCBWorld();
        lobby.setAutoSave(false);
        lobby.setStorm(false);
        lobby.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        lobby.setTime(0);
        lobby.setSpawnFlags(false, false);

        mvLobby.setPVPMode(false);
        mvLobby.setGameMode(GameMode.ADVENTURE);
        mvLobby.setAutoHeal(true);
        mvLobby.setAllowFlight(false);

        MissileWars.getLevelManager().scanForLevels();
        MissileWars.getMWL().info("All levels are scanned: " + MissileWars.getLevelManager().getMissileWarsLevelMap().size());
        Bukkit.getServer().setMaxPlayers(16);
    }

    public static boolean checkForStart() {
        if (MissileWars.GAME.getGameStatus() == GameStatus.LOBBY)
            System.out.println("DEBUG: " + MissileWars.GAME.teamer.getPlayers());
        if (MissileWars.GAME.teamer.getPlayers().size() > 1) {
            if(startTimer.getValue() !=  secs)
                return false;

            MissileWars.getMWL().info("Ready for start!");
            Bukkit.getScheduler().runTaskAsynchronously(MissileWars.getMissileWars(), () -> LobbyPhase.getStartTimer().start());

            return true;
        }
        return false;
    }


    public static class StartTimer extends Timer {

        public StartTimer(int seconds) {
            super(seconds);
            MissileWars.broadcast("missilewars.message.game.autostart");
        }

        @Override
        public void update(int value) {
            if (!MissileWars.GAME.isRunning())
                MissileWars.broadcast("missilewars.message.game.autostart.timer", getValue());
            else
                abort();
        }

        @Override
        protected void finish() {
            startGame();

        }


        private synchronized void startGame() {
            Future future = Bukkit.getScheduler().callSyncMethod(MissileWars.getMissileWars(), new Callable<>() {

                /**
                 * Computes a result, or throws an exception if unable to do so.
                 *
                 * @return computed result
                 * @throws Exception if unable to compute a result
                 */
                @Override
                public Object call() throws Exception {
                    if (!MissileWars.GAME.isRunning())
                        MissileWars.GAME.start();
                    else
                        System.err.println("game was started with a time and was already running!");
                    return null;
                }
            });
        }
    }
}
