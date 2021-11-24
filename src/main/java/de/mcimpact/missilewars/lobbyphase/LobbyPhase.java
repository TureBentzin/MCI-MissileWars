package de.mcimpact.missilewars.lobbyphase;

import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import de.mcimpact.missilewars.util.Timer;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class LobbyPhase {

    private static StartTimer startTimer = new StartTimer(20);

    public static StartTimer getStartTimer() {
        return startTimer;
    }

    public static void onSpawn(NetPlayer netPlayer) {

        Player bukkitPlayer = Bukkit.getPlayer(netPlayer.getUniqueId());
        Location spawnLocation = MissileWars.getLobby().getSpawnLocation();

        spawnLocation.getBlock().setType(Material.AIR);
        Location secondLocation = spawnLocation.clone();
        secondLocation.add(0, 1, 0);
        secondLocation.getBlock().setType(Material.AIR);

        bukkitPlayer.getInventory().clear();
        bukkitPlayer.setGameMode(GameMode.ADVENTURE);

        bukkitPlayer.teleport(spawnLocation);

        bukkitPlayer.setAllowFlight(true);
        bukkitPlayer.sendMessage("Lobbyphase completed on you!");
    }

    public static void onLobbyPhase(World lobby) {
        lobby.setAutoSave(false);
        lobby.setStorm(false);
        lobby.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        lobby.setTime(0);
        lobby.setPVP(false);
        lobby.setSpawnFlags(false, false);
        MissileWars.getLevelManager().scanForLevels();
        MissileWars.getMWL().info("All levels are scanned: " + MissileWars.getLevelManager().getMissileWarsLevelMap().size());

    }

    public  static boolean checkForStart() {
        if(MissileWars.GAME.getGameStatus() == GameStatus.LOBBY)
            System.out.println("DEBUG: " + MissileWars.GAME.teamer.getPlayers().toString());
       if( MissileWars.GAME.teamer.getPlayers().size() > 1) {

           MissileWars.getMWL().info("Ready for start!");
           getStartTimer().start();
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
            MissileWars.broadcast("missilewars.message.game.autostart.timer", getValue());
        }

        @Override
        protected void finish() {
            MissileWars.GAME.start();
        }
    }
}
