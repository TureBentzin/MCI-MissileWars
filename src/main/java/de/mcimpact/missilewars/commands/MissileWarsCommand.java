package de.mcimpact.missilewars.commands;

import de.mcimpact.core.Core;
import de.mcimpact.core.commands.Command;
import de.mcimpact.core.commands.CommandSender;
import de.mcimpact.core.commands.ConstrainedArgument;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import io.github.dseelp.kommon.command.*;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Consumer;

//missilewars status ENUM
//missilewars status
// /missilewars status running
public class MissileWarsCommand extends Command<CommandSender> {
    @NotNull
    @Override
    public NamedCommandNode<CommandSender> getDeclaration() {
        return declaration;
    }

    private final NamedCommandNode<CommandSender> declaration;

    public MissileWarsCommand() {
        Core.registerTranslation("missilewars.message.command.test.status", "The Missilewars Gamestatus is {0}");
        Core.registerTranslation("missilewars.message.command.test.statusset", "The Missilewars Gamestatus is now {0}");

        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> base = JavaUtils.literal("missilewars");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> status = JavaUtils.literal("status");
        System.out.println(Arrays.toString(Arrays.stream(GameStatus.values()).map(GameStatus::toString).toArray()));
        JavaCommandBuilder<CommandSender, ArgumentCommandNode<CommandSender>> statusenum = JavaUtils.argument(new ConstrainedArgument<CommandSender>("statusenum", context -> {
            return Arrays.stream(GameStatus.values()).map(GameStatus::toString).toArray(String[]::new);
        }));
        status.getBuilder().execute(new Consumer<CommandContext<CommandSender>>() {
            @Override
            public void accept(CommandContext<CommandSender> context) {
                System.out.println("Argumente!");
                context.getSender().sendMessage(Core.getTranslatableComponent("missilewars.message.command.test.status", MissileWars.GAME.getGameStatus().toString()));
            }
        });
        statusenum.execute(new Consumer<CommandContext<CommandSender>>() {
            @Override
            public void accept(CommandContext<CommandSender> context) {
                System.out.println("Set!");
                System.out.println(context.get("statusenum", String.class));
                MissileWars.GAME.setGameStatus(GameStatus.valueOf(context.get("statusenum", String.class)));
                context.getSender().sendMessage(Core.getTranslatableComponent("missilewars.message.command.test.statusset", MissileWars.GAME.getGameStatus().toString()));
            }
        });
        status.then(statusenum.build());
        base.then(status);
        declaration = base.build();
    }



}
