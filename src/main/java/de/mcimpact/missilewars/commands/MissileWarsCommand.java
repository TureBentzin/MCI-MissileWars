package de.mcimpact.missilewars.commands;

import de.mcimpact.core.Core;
import de.mcimpact.core.commands.Command;
import de.mcimpact.core.commands.CommandSender;
import de.mcimpact.core.commands.ConstrainedArgument;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.core.utils.TestSelector;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import io.github.dseelp.kommon.command.*;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Consumer;

public class MissileWarsCommand extends Command<CommandSender> {
    @NotNull
    @Override
    public NamedCommandNode<CommandSender> getDeclaration() {
        return declaration;
    }

    private final NamedCommandNode<CommandSender> declaration;

    public MissileWarsCommand() {
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> base = JavaUtils.literal("missilewars");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> status = JavaUtils.literal("status");
        JavaCommandBuilder<CommandSender, NamedCommandNode<CommandSender>> selector = JavaUtils.literal("selector");
        JavaCommandBuilder<CommandSender, ArgumentCommandNode<CommandSender>> statusenum = JavaUtils.argument(new ConstrainedArgument<CommandSender>("statusenum", context -> {
            return Arrays.stream(GameStatus.values()).map(GameStatus::toString).toArray(String[]::new);
        }));
        status.getBuilder().execute(new Consumer<CommandContext<CommandSender>>() {
            @Override
            public void accept(CommandContext<CommandSender> context) {
                context.getSender().sendMessage(Core.getTranslatableComponent("missilewars.message.cmd.test.status", MissileWars.GAME.getGameStatus().toString()));
            }
        });
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
        statusenum.execute(new Consumer<CommandContext<CommandSender>>() {
            @Override
            public void accept(CommandContext<CommandSender> context) {
                MissileWars.GAME.setGameStatus(GameStatus.valueOf(context.get("statusenum", String.class)));
                context.getSender().sendMessage(Core.getTranslatableComponent("missilewars.message.cmd.test.statusset", MissileWars.GAME.getGameStatus().toString()));
            }
        });
        status.then(statusenum.build());
        base.then(status);
        base.then(selector);
        declaration = base.build();
    }



}
