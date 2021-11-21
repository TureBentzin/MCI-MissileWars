package de.mcimpact.missilewars;

import de.mcimpact.core.Core;

public class Translations {

    private static final String KEY = "missilewars.";

    public static void run() {

        Core.registerTranslation(KEY + "message.movement.playerjoin", "<system#prefix><error#accent>{0} <system#color>joined the game!");
        Core.registerTranslation(KEY + "message.debug", "<system#prefix> [DEBUG] <system#color>{0}");
        Core.registerTranslation(KEY + "message.movement.adminmodejoin", "<system#prefix><system#color>You tried to join a game with AdminMode.");

        Core.registerTranslation(KEY + "message.movement.playerjoin.failed", "<fatal#prefix><fatal#color>Your join to MissileWars failed!");
        Core.registerTranslation(KEY + "message.movement.playerjoin.info", "<system#preifx><system#color>You joind a <system#accent>MissileWars <system#color>game!");
        Core.registerTranslation(KEY + "message.movement.playerjoin.aborted", "<error#prefix><error#color>Your join to MissileWars was aborted!");
        Core.registerTranslation(KEY + "message.movement.playerjoin.alreadyrunning", "<system#prefix><system#color>This game is already running.");
        Core.registerTranslation(KEY + "message.teaming.joined", "<system#prefix><system#color>You joined team: {0}");
        Core.registerTranslation(KEY + "message.movement.playerquit", "<system#preifx><system#color>{0} <system#color>left the game!");

        Core.registerTranslation(KEY + "message.cmd.start", "<system#preifx><error#accent>{0} <system#color>has started the game!");
        Core.registerTranslation(KEY + "message.cmd.start.alreadystarted", "<error#preifx><error#color>The game is already running!");

        Core.registerTranslation(KEY + "message.cmd.missilewars.levelmap", "<system#prefix><system#color>Current Map: <system#accent>{0}");

        Core.registerTranslation(KEY + "message.game.aborted", "<fatal#prefix><fatal#color>Your<fatal#accent> MissileWars <fatal#color>game failed!");


    }


    public static Object[] toObjectArray(Object[] array) {
        Object[] finalArray = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            finalArray[i] = array[i];
        }
        return finalArray;
    }
}
