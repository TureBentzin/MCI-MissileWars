package de.mcimpact.missilewars.commands;

import de.mcimpact.core.Core;
import de.mcimpact.core.commands.Command;
import de.mcimpact.core.commands.CommandSender;
import de.mcimpact.core.commands.ConstrainedArgument;
import de.mcimpact.core.util.Utils;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import io.github.dseelp.kommon.command.*;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class MissileWarsCommand extends Command<CommandSender> {
    private final NamedCommandNode<CommandSender> declaration;

    public MissileWarsCommand() {
        Core.registerTranslation("missilewars.message.command.test.status", "The Missilewars Gamestatus is {0}");
        Core.registerTranslation("missilewars.message.command.test.statusset", "The Missilewars Gamestatus is now {0}");

        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> base = JavaUtils.literal("missilewars");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> status = JavaUtils.literal("status");

        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> indexFolder = JavaUtils.literal("indexFolder");

        System.out.println(Arrays.toString(Arrays.stream(GameStatus.values()).map(GameStatus::toString).toArray()));
        JavaCommandBuilder<CommandSender, ArgumentCommandNode<CommandSender>> statusenum = JavaUtils.argument(
                new ConstrainedArgument<CommandSender>("statusenum",
                        context -> Arrays.stream(GameStatus.values()).map(GameStatus::toString).toArray(String[]::new)));

        indexFolder.execute(consoleSenderCommandContext -> {
            CommandContext<CommandSender> context = consoleSenderCommandContext;

            context.getSender().sendMessage(Arrays.toString(Utils.stringArrayToFolders(Utils.ROOT.getPath(), Utils.getSubdirectories(Utils.ROOT.getPath()))));

        });

        status.getBuilder().execute(context -> {
            System.out.println("Argumente!");
            context.getSender().sendMessage(Core.getTranslatableComponent("missilewars.message.command.test.status", MissileWars.GAME.getGameStatus().toString()));
        });

        statusenum.execute(context -> {
            System.out.println("Set!");
            System.out.println(context.get("statusenum", String.class));
            MissileWars.GAME.setGameStatus(GameStatus.valueOf(context.get("statusenum", String.class)));
            context.getSender().sendMessage(Core.getTranslatableComponent("missilewars.message.command.test.statusset", MissileWars.GAME.getGameStatus().toString()));
        });

        status.then(statusenum.build());
        base.then(status);
        base.then(indexFolder.build());
        declaration = base.build();
    }

    @NotNull
    @Override
    public NamedCommandNode<CommandSender> getDeclaration() {
        return declaration;
    }


}
