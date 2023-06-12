package org.patriot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface PatriotCommand {
    String getName();
    default String getDescription() {
        return "Эта команда не имеет описания.";
    }
    void invoke(MessageReceivedEvent event, String[] args);
    default void onException(Exception e) {

    }
    default Permission[] getGrantedPermissions() {
        return new Permission[] {
                Permission.MESSAGE_SEND
        };
    }
}
