package org.patriot.listener.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.jetbrains.annotations.NotNull;
import org.patriot.Constants;
import org.patriot.listener.PatriotListener;

import java.util.EnumSet;
import java.util.Objects;

public class StuffLookingModuleListener extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "StuffLookingModuleListener";
    }

    @Override
    public String getModuleDescription() {
        return "Listening when user request to be stuff";
    }




    private final MessageEmbed adminEmbed = new EmbedBuilder()
            .setTitle("• Открыт набор на роль Админа!")
            .setDescription("<:admin:1040238108035260457><@&978385182849630249> - данная должность отвечает за порядок на сервере и выдает наказания нарушителям.")
            .setColor(3454719)
            .addField("Что тебя ждет:","" +
                    "<:bluedot:1040199768774823966> Оплата работы в виде скинов\n" +
                    "<:bluedot:1040199768774823966> Система рангов Администрации\n" +
                    "<:bluedot:1040199768774823966> Возможность получить ценный опыт и карьерный рост\n" +
                    "<:bluedot:1040199768774823966> Уникальная роль в дискорде", false)
            .addField("Требование:", "" +
                    "<:bluedot:1040199768774823966> Возрастное ограничение: 15+\n" +
                    "<:bluedot:1040199768774823966> Опыт на других проектах - приветствуется!\n" +
                    "<:bluedot:1040199768774823966> Наиграть на сервере: 35 часов\n" +
                    "<:bluedot:1040199768774823966> Отсутствие Блокировки в сообществе STEAM\n" +
                    "<:bluedot:1040199768774823966> 300+ наигранных часов в CS:GO",false)
            .addField("Заинтересовала данная должность?","<:bluedot:1040199768774823966> В таком случае, нажимай на кнопку ниже!",false)
            .setFooter("Если ваша заявка соответствует нашим требованиям, то мы вам напишем!")
            .setImage("https://cdn.discordapp.com/attachments/976095234180120576/1040281513524207646/Comp_2_2.gif").build();


    private final MessageEmbed eventMakerEmbed = new EmbedBuilder()
            .setTitle("• Открыт набор на роль Инвентера!")
            .setDescription("<:inventmaker:1040238121742241853><@&979035645248938034> - данная должность отвечает за организацию \nмножества мероприятий и ивентов!")
            .addField("От тебя потребуется:","<:bluedot:1040199768774823966> Приятное времяпровождение в дружном коллективе.\n<:bluedot:1040199768774823966> Возможность получить ценный опыт и карьерный рост.\n<:bluedot:1040199768774823966> Зарплата в виде серверной валюты, нитро, реальные деньги,\n роли и многое другое!",false)
            .addField("Что тебя ждет:","<:bluedot:1040199768774823966>Приятное времяпровождение в дружном коллективе.\n<:bluedot:1040199768774823966> Возможность получить ценный опыт и карьерный рост.\n<:bluedot:1040199768774823966> Зарплата в виде серверной валюты, нитро, реальные деньги,\nроли и многое другое!",false)
            .addField("Заинтересовала данная должность?","<:bluedot:1040199768774823966>В таком случае, нажимай на кнопку ниже!",false)
            .setFooter("Если ваша заявка соответствует нашим требованиям, то мы вам напишем!")
            .setImage("https://cdn.discordapp.com/attachments/963699910501367848/1043969475717898390/1231231231.gif").build();


    @Override
    public void onSelectMenuInteraction(@NotNull SelectMenuInteractionEvent event) {
        super.onSelectMenuInteraction(event);
        if (!event.getSelectMenu().getId().equals("looking_stuff_menu")) return;

        switch (event.getValues().get(0)) {
            case "1": event.replyEmbeds(adminEmbed).addActionRow(Button.of(ButtonStyle.SECONDARY,"stuff_looking_create_req_for_be_admin","ㅤㅤㅤㅤㅤㅤㅤㅤСоздать запрос на должностьㅤㅤㅤㅤㅤㅤㅤㅤ")).setEphemeral(true).queue(); break;
            case "2": event.replyEmbeds(eventMakerEmbed).addActionRow(Button.of(ButtonStyle.SECONDARY,"stuff_looking_create_req_for_be_contentmaker","ㅤㅤㅤㅤㅤㅤㅤㅤСоздать запрос на должностьㅤㅤㅤㅤㅤㅤㅤㅤ")).setEphemeral(true).queue(); break;
        }
    }


    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        super.onButtonInteraction(event);

        //Admin
        if (event.getButton().getId().equals("stuff_looking_create_req_for_be_admin"))
            event.replyModal(getModal(1, "Запрос на администратора", "Ссылка на стим", "Ссылка на Телеграм (для связи)", "Ник в дискорде", "Сколько вам лет?", "Сколько отыграли на сервере?").build()).queue();

        //Contentmaker
        if (event.getButton().getId().equals("stuff_looking_create_req_for_be_contentmaker"))
            event.replyModal(getModal(2, "Запрос на eventmaker", "Ссылка на стим", "Ссылка на Телеграм (для связи)", "Ник в дискорде", "Сколько вам лет?", "Сколько отыграли на сервере?").build()).queue();

    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        super.onModalInteraction(event);
        if (!event.getModalId().startsWith("stuff_modal")) return;

        //Generating ticket message
        StringBuilder buffer = new StringBuilder();
        EmbedBuilder builder = new EmbedBuilder();
        String stuff = Objects.equals(event.getModalId().split(" ")[2], String.valueOf(1)) ? "администратора" : "ивентмейкера";
        builder.setTitle("Запрос от " + event.getUser().getAsTag() + " " + "на должность" + " " + stuff);

        for (ModalMapping mapping : event.getValues()) {
            buffer.append("`")
                    .append(mapping.getId())
                    .append(":")
                    .append("`")
                    .append(" ")
                    .append(mapping.getAsString())
                    .append("\n");
        }
        builder.setDescription(buffer);

        //Creating ticket
        event.getGuild().getCategoryById(LOOKING_FOR_STUFF_CATEGORY_ID)
                .createTextChannel(event.getUser().getName())

                //For @everyone closed channel
                .addPermissionOverride(event.getGuild().getPublicRole(),null, EnumSet.of(Permission.VIEW_CHANNEL))
                //For @suppoert role
                .addPermissionOverride(event.getGuild().getRoleById(SUPPORTERS_ROLE_ID), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), null)
                //For requester
                .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), null)

                .queue(ticketChannel ->
                    event.replyEmbeds(new EmbedBuilder().setTitle("Запрос создан, нажмите на кнопку ниже, чтобы перейти").build())
                            .addActionRow(Button.of(ButtonStyle.LINK, ticketChannel.getJumpUrl(), "Перейти в канал")).setEphemeral(true).queue(action ->
                                   ticketChannel.sendMessageEmbeds(builder.build()).setContent("@everyone")
                                           .addActionRow(
                                                   Button.of(ButtonStyle.DANGER,"reports_close_ticket " + event.getMember().getId(),"Закрыть тикет!"),
                                                   Button.of(ButtonStyle.PRIMARY,"reports_open_ticket " + event.getMember().getId(),"Открыть тикет!"),
                                                   Button.of(ButtonStyle.DANGER,"reports_delete_ticket", "Удалить тикет")
                                           )
                                           .queue()
                            )
                );
    }

    //Cool utility to create modals faster
    private Modal.Builder getModal(int tag, String name, String... vals) {
        Modal.Builder mdl = Modal.create("stuff_modal" + " " + name + " " + tag, name);
        for (String s : vals) {
            TextInput.Builder builder = TextInput.create(s, s, TextInputStyle.SHORT);
            mdl.addActionRows(ActionRow.of(builder.build()));
        }
        return mdl;
    }
}
