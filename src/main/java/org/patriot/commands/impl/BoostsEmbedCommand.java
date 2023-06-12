package org.patriot.commands.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.patriot.Constants;
import org.patriot.commands.PatriotCommand;

import java.awt.*;

public class BoostsEmbedCommand implements PatriotCommand, Constants {

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

    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        final EmbedBuilder builder = new EmbedBuilder()
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
                .setColor(new Color(169, 57, 226));
        event.getMessage().delete().queue(action -> event.getChannel().sendMessageEmbeds(builder.build()).queue());
    }

}
