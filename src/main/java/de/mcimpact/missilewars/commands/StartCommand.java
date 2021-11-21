package de.mcimpact.missilewars.commands;

import de.mcimpact.core.Core;
import de.mcimpact.core.commands.Command;
import de.mcimpact.core.players.NetPlayer;
import de.mcimpact.missilewars.MissileWars;
import de.mcimpact.missilewars.game.GameStatus;
import io.github.dseelp.kommon.command.JavaCommandBuilder;
import io.github.dseelp.kommon.command.JavaUtils;
import io.github.dseelp.kommon.command.NamedCommandNode;
import org.jetbrains.annotations.NotNull;

public class StartCommand extends Command<NetPlayer> {

    private final NamedCommandNode<NetPlayer> commandNode;

    public StartCommand() {
        JavaCommandBuilder<NetPlayer, NamedCommandNode<NetPlayer>> javaCommandBuilder = JavaUtils.literal("start");
        // javaCommandBuilder.checkAccess(PermissionPredicate.create("missilewars.cmd.start"));
        javaCommandBuilder.execute(netPlayerCommandContext -> {
            if (MissileWars.GAME.getGameStatus() == GameStatus.GAME) {
                netPlayerCommandContext.getSender().sendMessage(Core.getTranslatableComponent("missilewars.message.cmd.start.alreadystarted"));
                return;
            }
            MissileWars.broadcast("missilewars.message.cmd.start", netPlayerCommandContext.getSender().getName());
            MissileWars.GAME.start();
        });


        commandNode = javaCommandBuilder.build();

    }

    @NotNull
    @Override
    public NamedCommandNode<NetPlayer> getDeclaration() {
        return commandNode;
    }
}
