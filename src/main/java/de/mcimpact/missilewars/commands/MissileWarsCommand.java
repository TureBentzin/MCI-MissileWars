package de.mcimpact.missilewars.commands;

import de.mcimpact.core.Core;
import de.mcimpact.core.commands.Command;
import de.mcimpact.core.commands.CommandSender;
import de.mcimpact.core.commands.ConstrainedArgument;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.core.utils.TestSelector;
import de.mcimpact.core.util.Utils;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import de.mcimpact.missilewars.game.world.LevelManager;
import io.github.dseelp.kommon.command.*;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Consumer;

public class MissileWarsCommand extends Command<CommandSender> {
    private final NamedCommandNode<CommandSender> declaration;

    public MissileWarsCommand() {
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> base = JavaUtils.literal("missilewars");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> status = JavaUtils.literal("status");

        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> selector = JavaUtils.literal("selector");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> indexFolder = JavaUtils.literal("indexFolder");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> scanLevel = JavaUtils.literal("scanLevels");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> levelMap = JavaUtils.literal("levelMap");

        JavaCommandBuilder<CommandSender, ArgumentCommandNode<CommandSender>> statusenum = JavaUtils.argument(
                new ConstrainedArgument<>("statusenum",
                        context -> Arrays.stream(GameStatus.values()).map(GameStatus::toString).toArray(String[]::new)));
        
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
      
        indexFolder.execute(consoleSenderCommandContext -> {
            CommandContext<? extends CommandSender> context = consoleSenderCommandContext;

            context.getSender().sendMessage(Arrays.toString(Utils.stringArrayToFolders(Utils.ROOT.getPath(), Utils.getSubdirectories(Utils.ROOT.getPath()))));

        });

        status.getBuilder().execute(context -> {
            context.getSender().sendMessage(Core.getTranslatableComponent("missilewars.message.command.test.status", MissileWars.GAME.getGameStatus().toString()));
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
        base.then(levelMap);
        declaration = base.build();
    }

    @NotNull
    @Override
    public NamedCommandNode<CommandSender> getDeclaration() {
        return declaration;
    }


}
