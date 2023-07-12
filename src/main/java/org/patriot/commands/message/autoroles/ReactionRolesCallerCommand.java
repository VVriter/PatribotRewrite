package org.patriot.commands.message.autoroles;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.entities.emoji.EmojiUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.patriot.Constants;
import org.patriot.commands.ChatCommand;
import org.patriot.lib.discohook.DiscohookUtil;

import java.util.Arrays;
import java.util.List;

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


    private final List<MessageEmbed> messageEmbeds = DiscohookUtil.getEmbedsFromUrl("https://discohook.org/?data=eyJtZXNzYWdlcyI6W3siZGF0YSI6eyJjb250ZW50IjpudWxsLCJlbWJlZHMiOlt7ImNvbG9yIjoxNjc1OTYxNywiaW1hZ2UiOnsidXJsIjoiaHR0cHM6Ly9tZWRpYS5kaXNjb3JkYXBwLm5ldC9hdHRhY2htZW50cy85NjM2OTk5MTA1MDEzNjc4NDgvMTEyNzIyMDg1ODQ2NTgyODkzNi8zLmdpZj93aWR0aD04MTAmaGVpZ2h0PTI1MyJ9fSx7ImRlc2NyaXB0aW9uIjoiPDpiYl93aGl0ZV9kb3QyMjoxMDQwOTUxODU3NTI4NDQ2OTc2PiDQotC4INC30LDQudGI0L7QsiDQvdCwICoq0L7RhNGW0YbRltC50L3QuNC5INC_0YDQvtGU0LrRgioqINCj0LrRgNCw0ZfQvdC4IFwi0J_QsNGC0YDRltC-0YJcIi5cbl9f0KDQsNC00ZYg0LHQsNGH0LjRgtC4INGC0LXQsdC1INGB0LXRgNC10LQg0L3QsNGBIV9fINCQ0LvQtSDQvNC4INC30L3QsNGU0LzQviwg0Y7QvdC40Lkg0YXQsNC70Y_QstGJ0LjQutGDOlxu0YnQviDRgtC4INGC0YPRgiDQvNC-0LbQu9C40LLQviAqKtGH0LXRgNC10Lcg0YXQsNC70Y_QstGDINGDINCy0LjQs9C70Y_QtNGWINC_0YDQuNCy0ZbQu9C10Y4uKioiLCJjb2xvciI6MzAyNjQ3OCwiZmllbGRzIjpbeyJuYW1lIjoiPDpwcmljZV9taW5pOjEwOTQ5ODY3NTk5Njk5NzYzODI-INCi0Lgg0L7RgtGA0LjQvNCw0ZTRiCDRhdCw0LvRj9Cy0YMg0LfQsCDQv9GW0LTQv9C40YHQutGDINC90LAgX1_QtNC40YHQutC-0YDQtCDRliDRgtC10LvQtdCz0YDQsNC8X18hIiwidmFsdWUiOiLQkNC70LUg0L_QtdGA0LXQtCDRhtC40Lwg0LLQuNCx0LXRgNC4INGB0LXRgNCy0LXRgCDQtyDRj9C60L7Qs9C-INGC0Lgg0LfQsNC50YjQvtCyISJ9XSwiYXV0aG9yIjp7Im5hbWUiOiLQl9C00YDQsNGB0YLRg9C5LCDQtNC-0YDQvtCz0LjQuSDQk9GA0LDQstC10YbRjCEifSwiZm9vdGVyIjp7InRleHQiOiLihrYg0J3QsNGC0LjRgdC90Lgg0L3QsCDQvNC10L3RjiDQvdC40LbRh9C1IOKGtyJ9LCJpbWFnZSI6eyJ1cmwiOiJodHRwczovL2Nkbi5kaXNjb3JkYXBwLmNvbS9hdHRhY2htZW50cy85NjM2OTk5MTA1MDEzNjc4NDgvMTEwNDQ5ODQwODcxMjA2MTAxOS8xMTExLnBuZyJ9fV0sImF0dGFjaG1lbnRzIjpbXX19XX0");


    /*
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
     */


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
        event.getMessage().delete().queue(action -> event.getChannel().sendMessageEmbeds(messageEmbeds).addActionRow(selectMenu.build()).queue());
    }

}
