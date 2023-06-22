package org.patriot.commands.message;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.patriot.commands.ChatCommand;

import java.util.Arrays;

public class StuffLookingCallerCommand implements ChatCommand {
    @Override
    public String getName() {
        return "lookingstuff";
    }

    @Override
    public String getDescription() {
        return "Вызываю ембедк по поиску стаффа";
    }

    @Override
    public Permission[] getGrantedPermissions() {
        return new Permission[] {Permission.ADMINISTRATOR};
    }

    private final MessageEmbed[] embeds = {
            new EmbedBuilder()
                    .setDescription("<:bb_white_dot22:1040951857528446976> Нравится сервер и ты хочешь попасть к нам в команду?\n" +
                            "<:bb_white_dot22:1040951857528446976> Рассмотрите должности нашего стаффа, нажав на кнопку ниже, и выберите ту, которая заинтересует тебя!\n" +
                            "<:bb_white_dot22:1040951857528446976><:Curator:1040238114133782559><@&1003665645973540914> - примет заявку и обработает, после успешном зачислении, " +
                            "Вас проверят на знания правил и на знания проверки на читы. Пускай в пройдут отбор лучшие!")
                    .setColor(3553599)
                    .setAuthor("Набор в команду \"Patriot\"",null,"https://cdn3.emoji.gg/emojis/6239-jigglingclouds.gif")
                    .setFooter("Будьте готовы работать в коллективе и помогать друг другу!")
                    .setImage("https://cdn.discordapp.com/attachments/976095234180120576/1040955398926123089/nabor_c_sstaff_slowversion.gif").build()
    };

    private final StringSelectMenu.Builder selectMenu = StringSelectMenu.create("looking_stuff_menu");

    public StuffLookingCallerCommand() {
        selectMenu.addOption("Администратор","1","Описание роли администратора", Emoji.fromFormatted("<:admin:1040238108035260457>"));
        selectMenu.addOption("Создатель ивентов","2","Описание роли создателя ивентов", Emoji.fromFormatted("<:admin:1040238108035260457>"));
    }

    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        event.getMessage().delete().queue(action ->
            event.getChannel().sendMessageEmbeds(Arrays.asList(embeds))
                    .addActionRow(selectMenu.build())
                    .queue()
        );
    }
}
