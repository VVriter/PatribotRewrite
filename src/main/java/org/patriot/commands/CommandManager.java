package org.patriot.commands;

import lombok.Getter;
import org.patriot.Logger;
import org.patriot.commands.impl.*;
import org.patriot.commands.impl.autoroles.GamesRolePickCommand;
import org.patriot.commands.impl.autoroles.ReactionRolesCallerCommand;
import org.patriot.commands.impl.autoroles.TwichStreamRolePickCommand;
import org.patriot.commands.impl.vip.TelegramVipCommand;

import java.util.stream.Stream;

public class CommandManager {


    @Getter
    private PatriotCommand[] COMMANDS = {
            new HelpCommand(),
            new ExitCommand(),
            new SupportModuleCallerCommand(),
            new ReactionRolesCallerCommand(),
            new TelegramVipCommand(),
            new BoostsEmbedCommand(),
            new TwichStreamRolePickCommand(),
            new StuffLookingCallerCommand(),
            new GamesRolePickCommand(),
            new PurchasePrivilegeModuleCallerCommand(),
            new IssuesModuleCallerCommand()
    };



    public CommandManager() {
        Logger.log("command-manager", "Command manager started, loading commands...");
        Stream.of(COMMANDS).forEach(listener -> Logger.log("command-manager", "Loaded" + " " + listener.getName() + " " + "command."));
    }

}
