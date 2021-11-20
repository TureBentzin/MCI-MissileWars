package de.mcimpact.core.utils;

import de.mcimpact.core.Core;
import de.mcimpact.core.players.NetPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TestSelector extends Selector {

    NetPlayer player;

    public TestSelector() {
        super("Testselector", InventoryUtils.setName(new ItemStack(Material.RED_STAINED_GLASS_PANE), ""), true);
    }

    public void start(NetPlayer player) {
        setEntries(makeEntrys());
        open(player);
        this.player = player;
    }

    @Override
    protected void handle(SelectorEntry entry) {
        player.sendMessage(Core.getMessageManager().getTranslatableComponent("message.selector.test.handle.something"));
    }

    @Override
    protected void handle1(SelectorEntry entry) {
        player.sendMessage(Core.getMessageManager().getTranslatableComponent("message.selector.test.handle", Core.getMessageManager().getTranslatableComponent("message.selector.test.1").toString()));
    }

    @Override
    protected void handle2(SelectorEntry entry) {
        player.sendMessage(Core.getMessageManager().getTranslatableComponent("message.selector.test.handle", Core.getMessageManager().getTranslatableComponent("message.selector.test.2").toString()));
    }

    @Override
    protected void handle3(SelectorEntry entry) {
        player.sendMessage(Core.getMessageManager().getTranslatableComponent("message.selector.test.handle", Core.getMessageManager().getTranslatableComponent("message.selector.test.3").toString()));
    }

    @Override
    protected void handle4(SelectorEntry entry) {
        player.sendMessage(Core.getMessageManager().getTranslatableComponent("message.selector.test.handle", Core.getMessageManager().getTranslatableComponent("message.selector.test.4").toString()));
    }

    @Override
    protected void handle5(SelectorEntry entry) {
        player.sendMessage(Core.getMessageManager().getTranslatableComponent("message.selector.test.handle", Core.getMessageManager().getTranslatableComponent("message.selector.test.5").toString()));
    }

    public List<SelectorEntry> makeEntrys() {
        List<SelectorEntry> entries = new ArrayList<>();
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.TNT), Core.getMessageManager().getTranslatableComponent("message.selector.test.1").toString())));
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.NETHER_STAR), Core.getMessageManager().getTranslatableComponent("message.selector.test.2").toString())));
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.DRAGON_HEAD), Core.getMessageManager().getTranslatableComponent("message.selector.test.3").toString())));
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.OBSERVER), Core.getMessageManager().getTranslatableComponent("message.selector.test.4").toString())));
        entries.add(new SelectorEntry(InventoryUtils.setName(new ItemStack(Material.WARPED_FUNGUS), Core.getMessageManager().getTranslatableComponent("message.selector.test.5").toString())));
        return entries;
    }

}
