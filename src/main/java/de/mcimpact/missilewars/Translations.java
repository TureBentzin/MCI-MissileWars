package de.mcimpact.missilewars;

import de.mcimpact.core.Core;

public class Translations {

    private static final String KEY = "missilewars.";

    public static void run() {

        Core.registerTranslation(KEY + "message.movment.playerjoin", "<system#prefix><system#accent>{0} <system#color>joined the game!");
        Core.registerTranslation(KEY + "message.debug", "<system#prefix> [DEBUG] <system#color>{0}");
        Core.registerTranslation(KEY + "message.movement.adminmodejoin", "<system#prefix><system#color>You tried to join a game with AdminMode.");

        Core.registerTranslation(KEY + "message.movement.playerjoin.failed", "<fatal#prefix><fatal#color>Your join to MissileWars failed!");
        Core.registerTranslation(KEY + "message.movement.playerjoin.info", "<system#preifx><system#color>You joind a <system#accent>MissileWars <system#color>game!");
        Core.registerTranslation(KEY + "message.movement.playerjoin.aborted", "<error#prefix><error#color>Your join to MissileWars was aborted!");
        Core.registerTranslation(KEY + "message.movement.playerjoin.alreadyrunning", "<system#prefix><system#color>This game is already running.");
        Core.registerTranslation(KEY + "message.teaming.joined", "<system#prefix><system#color>You joined team: {0}");




    }


    public static Object[] toObjectArray(String[] array) {
        Object[] finalArray = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            finalArray[i] = array[i];
        }
        return finalArray;
    }
}
