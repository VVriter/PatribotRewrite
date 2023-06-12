package org.patriot.commands.impl;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.patriot.Logger;
import org.patriot.commands.PatriotCommand;

public class ExitCommand implements PatriotCommand {

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Выключает бота нахер, с этим осторожней пж";
    }

    @Override
    public Permission[] getGrantedPermissions() {
        return new Permission[] { Permission.ADMINISTRATOR };
    }

    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        event.getMessage().reply("Бот выключен...").queue(response -> {
            Logger.ds("command-dispatcher", "Бота офнул: " + event.getMember().getUser().getAsMention());
            System.exit(1);
        });
    }

}
