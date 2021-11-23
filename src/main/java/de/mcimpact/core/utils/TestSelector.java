package de.mcimpact.core.utils;

import de.mcimpact.core.Core;
import de.mcimpact.core.players.NetPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TestSelector extends Selector {

    NetPlayer player;

    public TestSelector() {
        super("Testselector", InventoryUtils.setName(new ItemStack(Material.RED_STAINED_GLASS_PANE), " "), true);
    }

    public void start(NetPlayer player) {
        setEntries(makeEntrys());
        open(player);
        this.player = player;
    }

    @Override
    protected void handle(SelectorEntry entry, int entryindex) {
        player.sendMessage(Core.getMessageManager().getTranslatableComponent("missilewars.message.selector.test.handle.something"));
    }

    @Override
    protected void handle1(SelectorEntry entry) {
        player.sendMessage(Core.getMessageManager().getTranslatableComponent("missilewars.message.selector.test.handle", Core.getMessageManager().getTranslatableComponent("missilewars.message.selector.test.1")));
    }

    @Override
    protected void handle2(SelectorEntry entry) {
        player.sendMessage(Core.getMessageManager().getTranslatableComponent("missilewars.message.selector.test.handle", Core.getMessageManager().getTranslatableComponent("missilewars.message.selector.test.2")));
    }

    @Override
    protected void handle3(SelectorEntry entry) {
        player.sendMessage(Core.getMessageManager().getTranslatableComponent("missilewars.message.selector.test.handle", Core.getMessageManager().getTranslatableComponent("missilewars.message.selector.test.3")));
    }

    @Override
    protected void handle4(SelectorEntry entry) {
        player.sendMessage(Core.getMessageManager().getTranslatableComponent("missilewars.message.selector.test.handle", Core.getMessageManager().getTranslatableComponent("missilewars.message.selector.test.4")));
    }

    @Override
    protected void handle5(SelectorEntry entry) {
        player.sendMessage(Core.getMessageManager().getTranslatableComponent("missilewars.message.selector.test.handle", Core.getMessageManager().getTranslatableComponent("missilewars.message.selector.test.5")));
    }

    public List<SelectorEntry> makeEntrys() {
        List<SelectorEntry> entries = new ArrayList<>();
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.TNT), Core.getMessageManager().getTranslatableComponent("missilewars.inventory.selector.test.1"))));
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.NETHER_STAR), Core.getMessageManager().getTranslatableComponent("missilewars.inventory.selector.test.2"))));
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.DRAGON_HEAD), Core.getMessageManager().getTranslatableComponent("missilewars.inventory.selector.test.3"))));
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.OBSERVER), Core.getMessageManager().getTranslatableComponent("missilewars.inventory.selector.test.4"))));
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.WARPED_FUNGUS), Core.getMessageManager().getTranslatableComponent("missilewars.inventory.selector.test.5"))));
        return entries;
    }

}
