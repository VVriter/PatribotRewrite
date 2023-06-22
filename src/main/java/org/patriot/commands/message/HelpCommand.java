package org.patriot.commands.message;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.patriot.Main;
import org.patriot.commands.ChatCommand;

import java.util.stream.Stream;

public class HelpCommand implements ChatCommand {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Это команда помощи, которая вызывает данное меню.";
    }

    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        Stream.of(Main.getCommandManager().getCHAT_COMMANDS()).forEach(command -> {
            stringBuffer.append("`")
                    .append(command.getName())
                    .append("`")
                    .append(" **<<===>>** ")
                    .append("`")
                    .append(command.getDescription())
                    .append("`")
                    .append("\n");
        });

        final EmbedBuilder builder = new EmbedBuilder()
                .setTitle("Окно помощи бота " + "patriot".toUpperCase() + ":" + "csgo".toUpperCase())
                .setDescription(stringBuffer);
        event.getMessage().replyEmbeds(builder.build()).queue();
    }

}
