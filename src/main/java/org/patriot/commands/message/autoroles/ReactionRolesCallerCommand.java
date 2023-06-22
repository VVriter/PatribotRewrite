package org.patriot.commands.message.autoroles;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.entities.emoji.EmojiUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.patriot.Constants;
import org.patriot.commands.ChatCommand;

import java.util.Arrays;

public class ReactionRolesCallerCommand implements ChatCommand, Constants {

    @Override
    public String getName() {
        return "serverChooser";
    }

    @Override
    public String getDescription() {
        return "Вызывает ембедку там где выбрать с какого сервера ты зашел";
    }

    @Override
    public Permission[] getGrantedPermissions() {
        return new Permission[] {Permission.ADMINISTRATOR};
    }



    private final MessageEmbed[] embeds = {
            new EmbedBuilder()
                    .setColor(3553599)
                    .setAuthor("Добро пожаловать на проект \"Патриот\"",null,"https://cdn3.emoji.gg/emojis/6601-black-world.gif")
                    .setImage("https://cdn.discordapp.com/attachments/963699910501367848/1037695725473104002/Typography_31.gif").build(),
            new EmbedBuilder()
                    .setDescription("**Здравствуй, дорогой Игрок!** <a:994809525326589952:1038790352179580938>\n\n• Ты зашел на **Официальный канал** проекта \"Патриот\". \n__Рады видеть тебя среди нас!__ Но мы знаем, юный халявщик<:994816784203006033:1040960249701605376> <:994816784203006033:1040960249701605376>\n что ты тут возможно **из-за халявы в виде привилегии**.\n\n> Ты получишь халяву за подписку на  __дискорд и телеграмм__!\n\n **<a:1007608454929715260:1038791420720459826>Но перед этим выбери сервер с которого ты зашел !** <a:1007608454929715260:1038791420720459826>")
                    .setColor(3553599)
                    .setFooter("↶ Нажми на меню ниже ↷").build()
    };


    private final StringSelectMenu.Builder selectMenu = StringSelectMenu.create("role_switcher");

    public ReactionRolesCallerCommand() {
        final EmojiUnion emoji = Emoji.fromFormatted("<:9gameicon2:1041414091643834419>");
        selectMenu.addOption("Мираж #1", MIRAGE_1, emoji);
        selectMenu.addOption("Мираж #2", MIRAGE_2, emoji);
        selectMenu.addOption("Мираж #3", MIRAGE_3, emoji);
        selectMenu.addOption("Мираж #4", MIRAGE_4, emoji);
        selectMenu.addOption("Мираж #5", MIRAGE_5, emoji);
        selectMenu.addOption("Авп #1", AWP_1, emoji);
        selectMenu.addOption("Авп #2", AWP_2, emoji);
        selectMenu.addOption("Авп #3", AWP_3, emoji);
        selectMenu.addOption("Авп #4", AWP_4, emoji);
        selectMenu.addOption("Авп #5", AWP_5, emoji);
        selectMenu.addOption("Паблик #1", PUBLIC_1, emoji);
    }



    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        event.getMessage().delete().queue(action -> event.getChannel().sendMessageEmbeds(Arrays.asList(embeds)).addActionRow(selectMenu.build()).queue());
    }

}
