package de.mcimpact.missilewars;

import de.mcimpact.core.Core;

public class Translations {

    private static final String KEY = "missilewars.";

    public static void run() {

        Core.registerTranslation(KEY + "message.movment.playerjoin", "<system#prefix><system#accent>{0} <system#color>joined the game!");
        Core.registerTranslation(KEY + "message.debug", "<system#prefix> [DEBUG] <system#accent>{0}");


    }
}
