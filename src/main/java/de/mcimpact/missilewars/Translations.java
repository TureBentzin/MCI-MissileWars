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
        Core.registerTranslation(KEY + "message.movement.playerquit", "<system#preifx><system#color>{0} <system#color>left the game!");

        Core.registerTranslation(KEY + "message.game.aborted", "<fatal#prefix><fatal#color>Your<fatal#accent> MissileWars <fatal#color>game failed!");

        Core.registerTranslation("missilewars.message.cmd.test.status", "The Missilewars Gamestatus is {0}");
        Core.registerTranslation("missilewars.message.cmd.test.statusset", "The Missilewars Gamestatus is now {0}");

        Core.registerTranslation(KEY + "inventory.cmd.test.selector", "<system#prefix><system#color>You can´t open the <system#accent>selector");
        Core.registerTranslation(KEY + "inventory.selector.test.handle.something", "<system#prefix><system#color>You have clicked<system#accent> something");
        Core.registerTranslation(KEY + "inventory.selector.test.handle", "<system#prefix><system#color>You have clicked<system#accent> {0}");
        Core.registerTranslation(KEY + "inventory.selector.test.1", "<system#accent>Missilewars");
        Core.registerTranslation(KEY + "inventory.selector.test.2", "<system#accent>Lobby");
        Core.registerTranslation(KEY + "inventory.selector.test.3", "<system#accent>Playground");
        Core.registerTranslation(KEY + "inventory.selector.test.4", "<system#accent>Debugserver");
        Core.registerTranslation(KEY + "inventory.selector.test.5", "<system#accent>NoKotlin!");



    }


    public static Object[] toObjectArray(Object[] array) {
        Object[] finalArray = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            finalArray[i] = array[i];
        }
        return finalArray;
    }
}
