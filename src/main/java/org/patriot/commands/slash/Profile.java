package org.patriot.commands.slash;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.patriot.Main;
import org.patriot.commands.SlashCommand;
import org.patriot.repository.PatriotUser;
import org.patriot.repository.exception.UserNotFoundException;

public class Profile implements SlashCommand {

    @Override
    public String getName() {
        return "профиль";
    }

    @Override
    public String getDescription() {
        return "Показывает ваш профиль на нашем сервре!";
    }

    @Override
    public void invoke(SlashCommandInteractionEvent event) {
        try {
            PatriotUser user = Main.getUsersRepository().getUser(event.getUser().getId());
            sendProfileEmbed(event, user);
        } catch (UserNotFoundException e) {
            PatriotUser patriotUser = new PatriotUser(event.getUser().getId(), 0);
            Main.getUsersRepository().createUser(patriotUser);
            sendProfileEmbed(event, patriotUser);
        }
    }

    private void sendProfileEmbed(SlashCommandInteractionEvent event, PatriotUser user) {
        final EmbedBuilder builder = new EmbedBuilder()
                .setDescription("Профиль игрока " + event.getUser().getAsMention() + "\n" +
                        "\n" +
                        "`Количество монет:` " + user.getMoney())
                .setThumbnail(event.getUser().getAvatarUrl());
        event.replyEmbeds(builder.build()).setEphemeral(true).queue();
    }


}
