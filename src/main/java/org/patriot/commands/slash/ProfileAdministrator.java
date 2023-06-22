package org.patriot.commands.slash;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.patriot.Main;
import org.patriot.commands.SlashCommand;
import org.patriot.repository.PatriotUser;
import org.patriot.repository.exception.UserNotFoundException;

public class ProfileAdministrator implements SlashCommand {

    @Override
    public String getName() {
        return "профиль_юзера";
    }

    @Override
    public String getDescription() {
        return "Команда для администрации, вы сможете посмотреть чужой профиль.";
    }

    @Override
    public Permission[] getGrantedPermissions() {
        return new Permission[] {Permission.ADMINISTRATOR};
    }

    @Override
    public void invoke(SlashCommandInteractionEvent event) {
        Member member = event.getOption("юзер").getAsMember();
        try {
            PatriotUser user = Main.getUsersRepository().getUser(member.getUser().getId());
            sendProfileEmbed(event, user, member);
        } catch (UserNotFoundException e) {
            PatriotUser patriotUser = new PatriotUser(member.getUser().getId(), 0);
            Main.getUsersRepository().createUser(patriotUser);
            sendProfileEmbed(event, patriotUser, member);
        }
    }

    private void sendProfileEmbed(SlashCommandInteractionEvent event, PatriotUser user, Member member) {
        final EmbedBuilder builder = new EmbedBuilder()
                .setDescription("Профиль игрока " + member.getUser().getAsMention() + "\n" +
                        "\n" +
                        "`Количество монет:` " + user.getMoney())
                .setThumbnail(member.getUser().getAvatarUrl());
        event.replyEmbeds(builder.build()).setEphemeral(true).queue();
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[] {
            new OptionData(OptionType.USER, "юзер", "Укажите юзера инвентарь которого вы хотите увидеть", true)
        };
    }
}
