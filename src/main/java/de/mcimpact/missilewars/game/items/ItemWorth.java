package de.mcimpact.missilewars.game.items;

import de.mcimpact.core.Core;

public record ItemWorth(int value) {

    private static final String NAME = "Gold";

    /**
     *
     * @return a formatted Lore for the value
     */
    public String generateValueLore() {
        return Core.translate(Core.getTranslatableComponent("missilewars.item.value.formatting" , NAME, value())).toString();
    }

}
