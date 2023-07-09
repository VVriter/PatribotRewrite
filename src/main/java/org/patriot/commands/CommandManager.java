package org.patriot.commands;

import lombok.Getter;
import org.patriot.Logger;
import org.patriot.commands.message.*;
import org.patriot.commands.message.autoroles.GamesRolePickCommand;
import org.patriot.commands.message.autoroles.ReactionRolesCallerCommand;
import org.patriot.commands.message.autoroles.TwichStreamRolePickCommand;
import org.patriot.commands.message.vip.TelegramVipCommand;
import org.patriot.commands.slash.Profile;

import java.util.stream.Stream;

public class CommandManager {


    @Getter
    private ChatCommand[] CHAT_COMMANDS = {
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
            new IssuesModuleCallerCommand(),
            //new Point()
    };

    @Getter
    private SlashCommand[] SLASH_COMMANDS = {
            new Profile()
    };

    public CommandManager() {
        Logger.log("command-manager", "Command manager started, loading commands...");
        Stream.of(CHAT_COMMANDS).forEach(listener -> Logger.log("command-manager", "Loaded" + " " + listener.getName() + " " + "command."));
    }

}
