package org.patriot.commands.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.patriot.Constants;
import org.patriot.commands.PatriotCommand;

import java.util.Arrays;

public class PurchasePrivilegeModuleCallerCommand implements PatriotCommand, Constants {

    @Override
    public String getName() {
        return "purchasePrivilege";
    }

    @Override
    public String getDescription() {
        return "Вызывает окно, где можно купить привилегию.";
    }

    @Override
    public Permission[] getGrantedPermissions() {
        return new Permission[] { Permission.ADMINISTRATOR };
    }

    private final MessageEmbed[] embeds = {
        new EmbedBuilder()
                .setImage("https://cdn.discordapp.com/attachments/963699910501367848/1037695726257446932/Typography_34.gif")
                .build(),
        new EmbedBuilder()
                .setTitle("Реквизиты для поддержки проекта")
                .setDescription("**```elixir\n#Доброго времени суток, дорогой патриот!" +
                "\nХотите купить привилегию на лучшем Украинском\nсервере и при этом помочь ЗСУ?```** **```" +
                "\nТогда купите любую привилегию на проекте \"Патриот\"```**")
                .addField("MONOBANK","**5408810040520107**",true)
                .addField("СКИНАМИ","[Ссылка на трейд](https://steamcommunity.com/tradeoffer/new/?partner=1145082347&token=WYctqDjj)",true)
                .build()
    };

    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        event.getMessage().delete().queue(action ->
            event.getChannel().sendMessageEmbeds(Arrays.asList(embeds))
                    .addActionRow(Button.of(ButtonStyle.SECONDARY, "purchase_priviligie_btn", "Приобрести привилегию"))
                    .queue()
        );
    }

}
