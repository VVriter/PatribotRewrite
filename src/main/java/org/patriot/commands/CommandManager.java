package org.patriot.commands;

import lombok.Getter;
import org.patriot.Logger;
import org.patriot.commands.impl.ExitCommand;
import org.patriot.commands.impl.HelpCommand;
import org.patriot.commands.impl.ReactionRolesCallerCommand;
import org.patriot.commands.impl.SupportModuleCallerCommand;

public class CommandManager {


    @Getter
    private PatriotCommand[] COMMANDS = {
            new HelpCommand(),
            new ExitCommand(),

            new SupportModuleCallerCommand(),
            new ReactionRolesCallerCommand()
    };



    public CommandManager() {
        Logger.log("command-manager", "Command manager started, loading commands...");
    }

}
