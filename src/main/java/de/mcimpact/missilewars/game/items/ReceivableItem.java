package de.mcimpact.missilewars.game.items;

public interface ReceivableItem extends Item{

    /**
     * @return value between 0 and 1
     */
    double getPercentage();
}
