package org.Kyoee01.pixelmon.server.manager.server.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import org.Kyoee01.pixelmon.server.manager.user.dex.PokeDex;
import org.bukkit.Bukkit;

public class PokeDexCommand {
    public PokeDexCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("pokedex").executes((command) -> {

            return execute(command.getSource());
        }));
    }

    private int execute(CommandSource source) throws CommandSyntaxException {
        PokeDex.openDex(Bukkit.getPlayer(source.asPlayer().getUniqueID()));
        return 1;
    }
}
