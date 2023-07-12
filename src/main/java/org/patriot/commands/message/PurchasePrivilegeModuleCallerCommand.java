package org.patriot.commands.message;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.patriot.Constants;
import org.patriot.commands.ChatCommand;
import org.patriot.lib.discohook.DiscohookUtil;

import java.util.Arrays;
import java.util.List;

public class PurchasePrivilegeModuleCallerCommand implements ChatCommand, Constants {

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

   /* private final MessageEmbed[] embeds = {
        new EmbedBuilder()
                .setImage("https://cdn.discordapp.com/attachments/963699910501367848/1037695726257446932/Typography_34.gif")
                .build(),
        new EmbedBuilder()
                .setTitle("Реквизиты для поддержки проекта")
                .setDescription("**```elixir\n#Доброго времени суток, дорогой патриот!" +
                "\nХотите купить привилегию на лучшем Украинском\nсервере и при этом помочь ЗСУ?```** **```" +
                "\nТогда купите любую привилегию на проекте \"Патриот\"```**")
                .build()
    };
    */

    private final List<MessageEmbed> embeds = DiscohookUtil.getEmbedsFromUrl("https://discohook.org/?data=eyJtZXNzYWdlcyI6W3siZGF0YSI6eyJjb250ZW50IjpudWxsLCJlbWJlZHMiOlt7ImNvbG9yIjpudWxsLCJpbWFnZSI6eyJ1cmwiOiJodHRwczovL21lZGlhLmRpc2NvcmRhcHAubmV0L2F0dGFjaG1lbnRzLzk2MzY5OTkxMDUwMTM2Nzg0OC8xMTI4MzM3MDgwNjE1OTY4ODM4L1R5cG9ncmFwaHlfMzMuZ2lmP3dpZHRoPTgxMCZoZWlnaHQ9NDU2In19LHsidGl0bGUiOiLQoNC10LrQstGW0LfQuNGC0Lgg0LTQu9GPINC_0ZbQtNGC0YDQuNC80LrQuCDQn9GA0L7QtdC60YLRgy4iLCJkZXNjcmlwdGlvbiI6ImBgYGFybVxuI9CU0L7QsdGA0L7Qs9C-INCy0LXRh9C-0YDQsCwg0J_QsNGC0YDRltC-0YLQtSFcbtCR0LDQttCw0ZTRiCDQv9GA0LjQtNCx0LDRgtC4INCf0YDQuNCy0ZbQu9C10Y4gINC90LAg0L3QsNC50LrRgNCw0YnQvtC80YMg0YPQutGA0LDRl9C90YHRjNC60L7QvNGDINGB0LXRgNCy0LXRgNGWINGWINC_0YDQuCDRhtGM0L7QvNGDINC00L7Qv9C-0LzQvtCz0YLQuCDQl9Ch0KM_YGBgYGBg0KLQvtC00ZYg0L_RgNC40LTQsdCw0LnRgtC1INC70Y7QsdGDINC_0YDQuNCy0ZbQu9C10Y4gINC90LAg0L_RgNC-0LXQutGC0ZYgwqvQn9Cw0YLRgNGW0L7RgsK7IGBgYCIsImNvbG9yIjpudWxsLCJpbWFnZSI6eyJ1cmwiOiJodHRwczovL2Nkbi5kaXNjb3JkYXBwLmNvbS9hdHRhY2htZW50cy85NjM2OTk5MTA1MDEzNjc4NDgvMTEwNDQ5ODQwODcxMjA2MTAxOS8xMTExLnBuZyJ9fV0sImF0dGFjaG1lbnRzIjpbXX19XX0");


    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        event.getMessage().delete().queue(action ->
            event.getChannel().sendMessageEmbeds(embeds)
                    .addActionRow(
                            Button.of(ButtonStyle.SECONDARY, "purchase_priviligie_btn", "Придбати Привілегію", Emoji.fromFormatted("<:whatdoitnext_mini:1094989900832571492>")),
                            Button.of(ButtonStyle.LINK, "https://patriot-csgo.com/shop/", "Сайт", Emoji.fromFormatted("<:8238iconworld:1093455350277087313>")),
                            Button.of(ButtonStyle.LINK, "https://patriot-csgo.com/oferta/", "Оферта", Emoji.fromFormatted("<:book_mini:1095012831407112222>"))
                    )
                    .queue()
        );
    }

}
