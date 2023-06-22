package org.patriot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface ChatCommand {
    String getName();
    default String getDescription() {
        return "Эта команда не имеет описания.";
    }
    default Permission[] getGrantedPermissions() {
        return new Permission[] {
                Permission.MESSAGE_SEND
        };
    }
    void invoke(MessageReceivedEvent event, String[] args);
    default void onException(Exception e, MessageReceivedEvent event) {

    }
}
