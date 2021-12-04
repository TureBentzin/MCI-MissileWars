package de.mcimpact.missilewars.commands;

import de.mcimpact.core.Core;
import de.mcimpact.core.commands.Command;
import de.mcimpact.core.commands.CommandSender;
import de.mcimpact.core.commands.ConstrainedArgument;
import de.mcimpact.core.commands.PermissionPredicate;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.core.util.Utils;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import de.mcimpact.missilewars.game.items.ReceivableItem;
import de.mcimpact.missilewars.game.items.SimpleReceivableItem;
import de.mcimpact.missilewars.game.world.LevelManager;
import de.mcimpact.missilewars.lobbyphase.LobbyPhase;
import io.github.dseelp.kommon.command.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MissileWarsCommand extends Command<CommandSender> {
    private final NamedCommandNode<CommandSender> declaration;

    public MissileWarsCommand() {
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> base = JavaUtils.literal("missilewars");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> status = JavaUtils.literal("status");

        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> selector = JavaUtils.literal("selector");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> indexFolder = JavaUtils.literal("indexFolder");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> scanLevel = JavaUtils.literal("scanLevels");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> levelMap = JavaUtils.literal("levelMap");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> async = JavaUtils.literal("startTimer");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> players = JavaUtils.literal("players");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> reset = JavaUtils.literal("reset");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> giveItem = JavaUtils.literal("giveItem");

        JavaCommandBuilder<CommandSender, ArgumentCommandNode<CommandSender>> statusenum = JavaUtils.argument(
                new ConstrainedArgument<>("statusenum",
                        context -> Arrays.stream(GameStatus.values()).map(GameStatus::toString).toArray(String[]::new)));


        /*
        selector.getBuilder().execute(new Consumer<CommandContext<CommandSender>>() {
            @Override
            public void accept(CommandContext<CommandSender> context) {
                if (context.getSender() instanceof NetPlayer) {
                    new TestSelector().start((NetPlayer) context.getSender());
                }
                else {
                    context.getSender().sendMessage(Core.getTranslatableComponent("message.cmd.test.selector"));
                }
            }
        });
         */

        reset.checkAccess(PermissionPredicate.create("missilewars.cmd.missilewars.reset")).execute(commandContext -> {
            MissileWars.broadcast("missilewars.message.cmd.missilewars.reset", commandContext.getSender().getName(), Bukkit.getServer().getName());
            Bukkit.shutdown();
        });

        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> force = JavaUtils.literal("force");
        force.execute(commandContext -> {
            MissileWars.broadcast("missilewars.message.cmd.missilewars.reset.force", commandContext.getSender().getName(), Bukkit.getServer().getName());
            System.exit(130);
        });

        reset.then(force);

        indexFolder.execute(consoleSenderCommandContext -> {
            CommandContext<? extends CommandSender> context = consoleSenderCommandContext;

            context.getSender().sendMessage(Arrays.toString(Utils.stringArrayToFolders(Utils.ROOT.getPath(), Utils.getSubdirectories(Utils.ROOT.getPath()))));

        });

        giveItem.execute(commandContext -> {
            if(commandContext.getSender() instanceof NetPlayer) {
                NetPlayer netPlayer = (NetPlayer) commandContext.getSender();

                Player player = Bukkit.getPlayer(netPlayer.getUniqueId());
                ReceivableItem receivableItem = new SimpleReceivableItem(Material.MAGENTA_BANNER, Component.text("Hallo"), 0.2);
                ReceivableItem receivableItem1 = new SimpleReceivableItem(Material.BRICK, Component.text("!=$"), 0.4);
                ReceivableItem receivableItem2 = new SimpleReceivableItem(Material.DISPENSER, Component.text("bRE"), 0.32);
                ReceivableItem receivableItem3 = new SimpleReceivableItem(Material.STICK, Component.text("Yeas"), 0.19);

                MissileWars.getItemManager().addItems(receivableItem, receivableItem1, receivableItem2, receivableItem3);

                player.getInventory().addItem(MissileWars.getItemManager().getRandomItem().toStack());
            }
        });

        players.execute(commandContext -> commandContext.getSender().sendMessage(Core.getTranslatableComponent("missilewars.message.debug", "OnlinePlayers: " + MissileWars.GAME.getOnlinePlayers())));

        status.getBuilder().execute(context -> {
            context.getSender().sendMessage(Core.getTranslatableComponent("missilewars.message.command.test.status", MissileWars.GAME.getGameStatus().toString()));
        });

        async.execute(commandContext -> {
            System.out.println(Bukkit.getScheduler().runTaskAsynchronously(MissileWars.getMissileWars(), () -> {
                LobbyPhase.getStartTimer().start();
            }));
        });

        scanLevel.execute(commandContext -> {
            commandContext.getSender().sendMessage(Core.getTranslatableComponent("missilewars.message.debug", "Scanning for Levels -> Look console!"));
            MissileWars.getLevelManager().scanForLevels();
        });

        statusenum.execute(context -> {
            System.out.println(context.get("statusenum", String.class));
            MissileWars.GAME.setGameStatus(GameStatus.valueOf(context.get("statusenum", String.class)));
            context.getSender().sendMessage(Core.getTranslatableComponent("missilewars.message.command.test.statusset", MissileWars.GAME.getGameStatus().toString()));
        });

        levelMap.execute(commandContext -> {
            commandContext.getSender().sendMessage(Core.getTranslatableComponent("missilewars.message.cmd.missilewars.levelmap", LevelManager.getInstance().getMissileWarsLevelMap().toString()));
        });

        status.then(statusenum.build());
        base.then(status);
        base.then(selector);
        base.then(indexFolder.build());
        base.then(scanLevel);
        base.then(players);
        base.then(levelMap);
        base.then(async);
        base.then(giveItem);
        base.then(reset);

        declaration = base.build();

    }

    @NotNull
    @Override
    public NamedCommandNode<CommandSender> getDeclaration() {
        return declaration;
    }


}
