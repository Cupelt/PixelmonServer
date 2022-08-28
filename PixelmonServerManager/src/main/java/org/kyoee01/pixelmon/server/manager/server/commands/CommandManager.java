package org.Kyoee01.pixelmon.server.manager.server.commands;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.server.command.ConfigCommand;

public class CommandManager {
    public static void registerCommands(RegisterCommandsEvent event){
        /*

            ※ do not staging this class

            Forge Event register here -
            Ex) new ExampleCommand(event.getDispatcher());

            Configuration within the ExampleCommand Class -

            public ExampleCommand(CommandDispatcher<CommandSource> dispatcher){
                dispatcher.register(Commands.literal("example").then(Commands.literal("is").executes((command) -> {

                    return Example(command.getSource());
                })));
            }

            private int Example(CommandSource source) throws CommandSyntaxException {
                Your Code..
                return 1;
            }
         */
        new PokeDexCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}
