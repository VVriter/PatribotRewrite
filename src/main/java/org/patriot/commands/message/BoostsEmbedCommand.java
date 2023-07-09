package org.patriot.commands.message;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.patriot.Constants;
import org.patriot.commands.ChatCommand;
import org.patriot.lib.discohook.DiscohookUtil;

import java.awt.*;
import java.util.List;

public class BoostsEmbedCommand implements ChatCommand, Constants {

    @Override
    public String getName() {
        return "boostsEmbed";
    }

    @Override
    public String getDescription() {
        return "Скидывает ембедку с привилегиями от нитро";
    }

    @Override
    public Permission[] getGrantedPermissions() {
        return new Permission[] {Permission.ADMINISTRATOR};
    }

    List<MessageEmbed> messageEmbeds = DiscohookUtil.getEmbedsFromUrl("https://discohook.org/?data=eyJtZXNzYWdlcyI6W3siZGF0YSI6eyJjb250ZW50IjpudWxsLCJlbWJlZHMiOlt7InRpdGxlIjoi0KXQvtGH0LXRiCDQv9C-0LTRltC70LjRgtC40YHRjyDRgdCy0L7Rl9C8INCx0YPRgdGC0L7QvCDRltC3INC90LDRiNC40Lwg0YHQtdGA0LLQtdGA0L7QvD8iLCJkZXNjcmlwdGlvbiI6ItCd0LXQvNCw0ZQg0LzQvtC20LvQuNCy0L7RgdGC0ZYg0LrRg9C_0LjRgtC4IF9f0LLRltC_0LrRgyDQvdCw0LfQsNCy0LbQtNC4X18_XG7QotC-0LTRliDQt9Cw0LrQuNC90YwgKirQsdGD0YHRgioqINC90LDRiNC-0LzRgyBb0LTQuNGB0LrQvtGA0LQg0YHQtdGA0LLQtdGA0YNdKGh0dHBzOi8vZGlzY29yZC5nZy9wYXRyaW90LWNzZ28pIVxuXG4qKl9f0Jgg0L_QvtC70YPRh9C4INC_0LvRjtGI0LrQuCDQsiDQstC40LTQtTpfX1xu4qCA4oCiINCg0L7Qu9GMIDxAJjk3MjI1NTQwMDQ2MjI2NjQwMD5cbuKggOKAoiDQmtCw0YHRgtC-0LzQvdCw0Y8g0YDQvtC70Ywg0L3QsCDQstCw0Ygg0LLRi9Cx0L7RgCFcbuKggOKAoiDQntGC0LTQtdC70YzQvdGL0Lkg0YfQsNGCIVxu4qCA4oCiIFZJUCDQvdCwINGB0LXRgNCy0LXRgNC1INC4IDEwMNC6INC60YDQtdC00LjRgtC-0LIhKioiLCJjb2xvciI6MTExNTU5MzgsImF1dGhvciI6eyJuYW1lIjoi0JTQvtC_0L7QvNC-0LbQuCDQvdCw0LwsINCwINC80Lgg0LTQvtC_0L7QvNC-0LbQtdC80L4g0YLQvtCx0ZYhIiwiaWNvbl91cmwiOiJodHRwczovL2Nkbi5kaXNjb3JkYXBwLmNvbS9hdHRhY2htZW50cy8xMDM2OTQ1MzQ3NjgwOTQwMDQyLzEwOTQyNjk2MTA2NjYyNTAzNzEvYzM0MGI4Y2MtNTg3Yy00YzM5LWExNjItNTJjNDQ5OWEzZWUxLmdpZiJ9LCJpbWFnZSI6eyJ1cmwiOiJodHRwczovL21lZGlhLmRpc2NvcmRhcHAubmV0L2F0dGFjaG1lbnRzLzk2MzY5OTkxMDUwMTM2Nzg0OC8xMTI3MjIzOTQ2MjAzMzgxODAxLzMuZ2lmIn19XSwiYXR0YWNobWVudHMiOltdfX1dfQ");


    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        /*final EmbedBuilder builder = new EmbedBuilder()
                .setAuthor("Помоги нам, а мы поможем тебе !",null,"https://images-ext-1.discordapp.net/external/Jv5aKwEoeUzafSBR1a0tqLrXoPTZYKPSnJtVLDi2wN4/https/emoji.discord.st/emojis/c340b8cc-587c-4c39-a162-52c4499a3ee1.gif")
                .setTitle("Хочешь поделиться своим бустом с нашим сервером?")
                .setDescription("Нету возможности купить __випку навсегда__?\n" +
                        "Тогда закинь **буст** нашему [дискорд серверу!](https://discord.gg/Gd8t9ufReQ)\n\n" +
                        "__**И получи плюшки в виде:**__\n" +
                        "⠀**• Роль " + event.getGuild().getRoleById(BOOSTER_ROLE_ID).getAsMention() + "**\n" +
                        "⠀**• Кастомная роль на ваш выбор!**\n" +
                        "⠀**• Отдельный чат!**\n" +
                        "⠀**• VIP на сервере и 100к кредитов!**\n\n" +
                        "__**Заводите двигатель и приготовьтесь к** ✨[Б У С Т И Н Г У](https://support.discord.com/hc/ru/articles/360028038352-%D0%91%D1%83%D1%81%D1%82%D0%B8%D0%BD%D0%B3-%D1%81%D0%B5%D1%80%D0%B2%D0%B5%D1%80%D0%B0-) ✨ !__")
                .setImage("https://cdn.discordapp.com/attachments/976095234180120576/994552115118751824/Retro_Opener_2.gif")
                .setColor(new Color(169, 57, 226));*/
        event.getMessage().delete().queue(action -> event.getChannel().sendMessageEmbeds(messageEmbeds).queue());
    }

}
