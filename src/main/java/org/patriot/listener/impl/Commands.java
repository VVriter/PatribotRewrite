package org.patriot.listener.impl;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.patriot.Constants;
import org.patriot.Main;
import org.patriot.listener.PatriotListener;

import java.util.Objects;
import java.util.stream.Stream;

public class Commands extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "Command listener module";
    }

    @Override
    public String getModuleDescription() {
        return "I just processing all text commands here";
    }


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        final String content = event.getMessage().getContentRaw();

        //Check for message starts with prefix (will be more optimized xd)
        if (!content.startsWith(PREFIX)) return;

        //Check for owner of message is not bot or discord system
        if (event.getMessage().getAuthor().isBot() || event.getMessage().getAuthor().isSystem()) return;

        //Streaming all commands and trying to execute
        Stream.of(Main.getCommandManager().getCOMMANDS())
                .filter(command -> content.startsWith(PREFIX + command.getName()))
                .filter(command -> Objects.requireNonNull(event.getMember()).hasPermission(command.getGrantedPermissions()))
                .forEach(command -> {
                    try {
                        command.invoke(event, content.split("\\s+"));
                    } catch (Exception e) {
                        command.onException(e, event);
                    }
                });

    }

}
