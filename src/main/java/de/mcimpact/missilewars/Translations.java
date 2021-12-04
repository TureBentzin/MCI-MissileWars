package de.mcimpact.missilewars;

import de.mcimpact.core.Core;

public class Translations {

    private static final String KEY = "missilewars.";

    public static void run() {

        Core.registerTranslation(KEY + "message.movement.playerjoin", "<system#prefix><error#accent>{0} <system#color>joined the game!");
        Core.registerTranslation(KEY + "message.debug", "<system#prefix> [DEBUG] <system#color>{0}");
        Core.registerTranslation(KEY + "message.movement.adminmodejoin", "<system#prefix><system#color>You tried to join a game with AdminMode.");

        Core.registerTranslation(KEY + "message.movement.playerjoin.failed", "<fatal#prefix><fatal#color>Your join to MissileWars failed!");
        Core.registerTranslation(KEY + "message.movement.playerjoin.info", "<system#prefix><system#color>You joind a <system#accent>MissileWars <system#color>game!");
        Core.registerTranslation(KEY + "message.movement.playerjoin.aborted", "<error#prefix><error#color>Your join to MissileWars was aborted!");
        Core.registerTranslation(KEY + "message.movement.playerjoin.alreadyrunning", "<system#prefix><system#color>This game is already running.");
        Core.registerTranslation(KEY + "message.teaming.joined", "<system#prefix><system#color>You joined team: {0}");
        Core.registerTranslation(KEY + "message.movement.playerquit", "<system#prefix><system#color>{0} <system#color>left the game!");

        Core.registerTranslation(KEY + "message.cmd.start", "<system#prefix><error#accent>{0} <system#color>has started the game!");

        Core.registerTranslation(KEY + "message.cmd.start.alreadystarted", "<error#prefix><error#color>The game is already running!");

        Core.registerTranslation(KEY + "message.cmd.missilewars.levelmap", "<system#prefix><system#color>Current Map: <system#accent>{0}");


        Core.registerTranslation(KEY + "message.game.autostart", "<system#prefix><system#color>The game is <system#accent>starting<system#color>!");
        Core.registerTranslation(KEY + "message.game.autostart.timer", "<system#prefix><system#color>The game is starting in <error#accent>{0}<system#color>s!");

        Core.registerTranslation(KEY + "message.game.aborted", "<fatal#prefix><fatal#color>Your<fatal#accent> MissileWars <fatal#color>game failed!");
        Core.registerTranslation(KEY + "message.movement.disconnect", "<system#prefix><system#accent>{0} <system#color>out of Team <system#accent>{1}<system#color> has quit!");
        Core.registerTranslation(KEY + "message.movement.rejoin", "<system#prefix><system#accent>{0} <system#color>out of Team <system#accent>{1}<system#color> rejoined");


        Core.registerTranslation("missilewars.message.cmd.test.status", "The Missilewars Gamestatus is {0}");
        Core.registerTranslation("missilewars.message.cmd.test.statusset", "The Missilewars Gamestatus is now {0}");
        Core.registerTranslation("missilewars.message.cmd.tasks", "<system#preifx><system#color>Current Tasks: <system#accent> {0}");

        Core.registerTranslation(KEY + "message.cmd.missilewars.reset", "<error#prefix><error#accent>{0} <error#color>initiated a <error#accent>reset <error#color>of this {1} server!");
        Core.registerTranslation(KEY + "message.cmd.missilewars.reset.force", "<fatal#prefix><fatal#color>The {1} server was<fatal#accent> FORCED<fatal#color> to reset by <fatal#accent>{0}<fatal#color>!");


        Core.registerTranslation(KEY + "message.cmd.test.selector", "<system#prefix><system#color>You can´t open the <system#accent>selector");
        Core.registerTranslation(KEY + "message.selector.test.handle.something", "<system#prefix><system#color>You have clicked<system#accent> something");
        Core.registerTranslation(KEY + "message.selector.test.handle", "<system#prefix><system#color>You have clicked<system#accent> {0}");
        Core.registerTranslation(KEY + "item.selector.test.1", "<system#accent>Missilewars");
        Core.registerTranslation(KEY + "item.selector.test.2", "<system#accent>Lobby");
        Core.registerTranslation(KEY + "item.selector.test.3", "<system#accent>Playground");
        Core.registerTranslation(KEY + "item.selector.test.4", "<system#accent>Debugserver");
        Core.registerTranslation(KEY + "item.selector.test.5", "<system#accent> :comNoKotlin: !");

        Core.registerTranslation(KEY + "message.start", "<system#prefix><system#color>The game is <system#accent>starting<system#color>!");
        Core.registerTranslation(KEY + "message.start.level", "<system#prefix><system#color>You are going to play on <system#accent>{0}<system#color>!");

        Core.registerTranslation(KEY + "message.fatal", "<fatal#prefix>Something really went wrong! <fatal#accent> - please report this<fatal#color>!");


        Core.registerTranslation(KEY + "message.killed", "<system#prefix><system#accent>{0} <system#color>was killed by <system#accent>{1}<system#color>!");
        Core.registerTranslation(KEY + "message.died", "<system#prefix><system#accent>{0} <system#color>died!");
        Core.registerTranslation(KEY + "message.explode", "<system#prefix><system#accent>{0} <system#color>was blown up!");

        Core.registerTranslation(KEY + "item.value.formatting", "<gold><bold>{0} <yellow>:<gold>{1}");
        Core.registerTranslation(KEY + "message.itemreceived", "<system#prefix><system#color>You received {0}* <system#accent>{1}<system#color>!");

        Core.registerTranslation(KEY + "motd.lobby", "<bold><gradient:#8a2527:#ff0004> MissileWars </bold><gray>|</gray> <yellow>Waiting for players...");
        Core.registerTranslation(KEY + "motd.game", "<bold><gradient:#8a2527:#ff0004> MissileWars </bold><gray>|</gray> <orange>Game is running!");
        Core.registerTranslation(KEY + "motd.end", "<bold><gradient:#8a2527:#ff0004> MissileWars </bold><gray>|</gray> <red>Game has ended! Server will restart soon!");
        Core.registerTranslation(KEY + "motd.paused", "<bold><gradient:#8a2527:#ff0004> MissileWars </bold><gray>|</gray> <dark_red>MissileWars is not responding...");

        Core.registerTranslation(KEY + "item.fireball.name", "<red>FIREBALL");
        Core.registerTranslation(KEY + "item.fireball.lore", "<gray>Throw it like its hot!");


        Core.registerTranslation(KEY + "display.title.death", "<bold><gradient:#8a2527:#ff0004> You died!");


    }


    public static Object[] toObjectArray(Object[] array) {
        Object[] finalArray = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            finalArray[i] = array[i];
        }
        return finalArray;
    }
}
