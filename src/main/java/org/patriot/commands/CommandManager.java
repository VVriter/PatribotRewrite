package org.patriot.commands;

import lombok.Getter;
import org.patriot.Logger;
import org.patriot.commands.impl.ExitCommand;
import org.patriot.commands.impl.HelpCommand;

public class CommandManager {


    @Getter
    private PatriotCommand[] COMMANDS = {
            new HelpCommand(),
            new ExitCommand()
    };



    public CommandManager() {
        Logger.log("command-manager", "Command manager started, loading commands...");
    }

}
