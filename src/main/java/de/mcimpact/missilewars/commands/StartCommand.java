package de.mcimpact.missilewars.commands;

import de.mcimpact.core.commands.Command;
import de.mcimpact.core.commands.PermissionPredicate;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.world.MissileWarsLevel;
import io.github.dseelp.kommon.command.CommandContext;
import io.github.dseelp.kommon.command.JavaCommandBuilder;
import io.github.dseelp.kommon.command.JavaUtils;
import io.github.dseelp.kommon.command.NamedCommandNode;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class StartCommand extends Command<NetPlayer> {

    public StartCommand() {
        JavaCommandBuilder<NetPlayer, NamedCommandNode<NetPlayer>> javaCommandBuilder = JavaUtils.literal("start");
        javaCommandBuilder.checkAccess(PermissionPredicate.create("missilewars.cmd.start"));
        javaCommandBuilder.execute(netPlayerCommandContext -> {
            MissileWars.broadcast("missilewars.message.cmd.start", netPlayerCommandContext.getSender().getName());
            MissileWars.GAME.start();
        });


        commandNode = javaCommandBuilder.build();

    }

    private final NamedCommandNode<NetPlayer> commandNode;


    @NotNull
    @Override
    public NamedCommandNode<NetPlayer> getDeclaration() {
        return null;
    }
}
