package org.patriot.commands.slash;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.patriot.commands.SlashCommand;

public class Profile implements SlashCommand {

    @Override
    public String getName() {
        return "profile";
    }

    @Override
    public String getDescription() {
        return "Показывает ваш профиль на нашем сервре!";
    }

    @Override
    public void invoke(SlashCommandInteractionEvent event) {

    }


}
