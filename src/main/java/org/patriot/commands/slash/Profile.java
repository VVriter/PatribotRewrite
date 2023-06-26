package org.patriot.commands.slash;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.FileUpload;
import org.apache.commons.io.FileUtils;
import org.patriot.Main;
import org.patriot.commands.SlashCommand;
import org.patriot.repository.PatriotUser;

import java.io.File;
import java.io.IOException;
import java.net.URL;

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
        PatriotUser user = Main.getUsersRepository().getUser(event.getUser().getId());
        sendProfileEmbed(event, user);
    }

    private final File file = new File("./cache");

    private void sendProfileEmbed(SlashCommandInteractionEvent event, PatriotUser user) {

        try {
            File fileToSave = new File(file, user.getId() + ".png");
            FileUtils.copyURLToFile(new URL(event.getUser().getAvatarUrl()), fileToSave);

            final EmbedBuilder builder = new EmbedBuilder()
                    .setDescription("Профиль игрока " + event.getUser().getAsMention() + "\n" +
                            "\n" +
                            "`Количество монет:` " + user.getMoney() +
                            "`Отправлено сообщений:` " + user.getMessagesSented())
                    .setThumbnail("attachment://" + "avatar" + ".png");
            event.replyFiles(FileUpload.fromData(fileToSave, "avatar.png")).setEmbeds(builder.build()).setEphemeral(true).queue();
        } catch (IOException e) {
            e.printStackTrace();
            event.reply("Что-то пошло не так, обратитесь к администрации").setEphemeral(true).queue();
        }

    }


}
