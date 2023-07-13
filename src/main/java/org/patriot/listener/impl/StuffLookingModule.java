package org.patriot.listener.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.jetbrains.annotations.NotNull;
import org.patriot.Constants;
import org.patriot.listener.PatriotListener;

import java.util.EnumSet;

public class StuffLookingModule extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "StuffLookingModuleListener";
    }

    @Override
    public String getModuleDescription() {
        return "Listening when user request to be stuff";
    }




    private final MessageEmbed adminEmbed = new EmbedBuilder()
            .setTitle("• Відкрита вакансія на роль Адміна!")
            .setDescription("<:admin:1040238108035260457><@&978385182849630249> - ця посада відповідає за порядок на сервері та накладає покарання на порушників.")
            .setColor(3454719)
            .addField("Що тебе чекає:", "<:bluedot:1040199768774823966> Оплата роботи у вигляді скинів\n" +
                    "<:bluedot:1040199768774823966> Система рангів Адміністрації\n" +
                    "<:bluedot:1040199768774823966> Можливість отримати цінний досвід та кар'єрний ріст\n" +
                    "<:bluedot:1040199768774823966> Унікальна роль у дискорді", false)
            .addField("Вимоги:", "<:bluedot:1040199768774823966> Вікове обмеження: 15+\n" +
                    "<:bluedot:1040199768774823966> Досвід на інших проектах - вітається!\n" +
                    "<:bluedot:1040199768774823966> Грати на сервері: 35 годин\n" +
                    "<:bluedot:1040199768774823966> Відсутність блокування в спільноті STEAM\n" +
                    "<:bluedot:1040199768774823966> 300+ годин гри в CS:GO", false)
            .addField("Зацікавила ця посада?", "<:bluedot:1040199768774823966> Тоді натисни на кнопку нижче!", false)
            .setFooter("Якщо ваша заявка відповідає нашим вимогам, ми вам напишемо!")
            .setImage("https://cdn.discordapp.com/attachments/976095234180120576/1040281513524207646/Comp_2_2.gif").build();


    private final MessageEmbed eventMakerEmbed = new EmbedBuilder()
            .setTitle("• Відкрита вакансія на роль Інвентора!")
            .setDescription("<:inventmaker:1040238121742241853><@&979035645248938034> - ця посада відповідає за організацію \nбагатьох заходів та івентів!")
            .addField("Від тебе знадобиться:", "<:bluedot:1040199768774823966> Приємний час проведення в дружньому колективі.\n<:bluedot:1040199768774823966> Можливість отримати цінний досвід та кар'єрний ріст.\n<:bluedot:1040199768774823966> Зарплата у вигляді серверної валюти, нітро, реальні гроші,\nролі та багато іншого!", false)
            .addField("Чого тебе чекає:", "<:bluedot:1040199768774823966> Приємний час проведення в дружньому колективі.\n<:bluedot:1040199768774823966> Можливість отримати цінний досвід та кар'єрний ріст.\n<:bluedot:1040199768774823966> Зарплата у вигляді серверної валюти, нітро, реальні гроші,\nролі та багато іншого!", false)
            .addField("Зацікавила ця посада?", "<:bluedot:1040199768774823966> Тоді натисни на кнопку нижче!", false)
            .setFooter("Якщо ваша заявка відповідає нашим вимогам, ми вам напишемо!")
            .setImage("https://cdn.discordapp.com/attachments/963699910501367848/1043969475717898390/1231231231.gif").build();

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        super.onStringSelectInteraction(event);
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
            event.replyModal(getModal(1, "Запит на адміністратора", "Посилання на Steam", "Посилання на Telegram (для зв'язку)", "Ім'я користувача в Discord", "Скільки вам років?", "Скільки годин ви зіграли на сервері?").build()).queue();

        //Контентмейкер
        if (event.getButton().getId().equals("stuff_looking_create_req_for_be_contentmaker"))
            event.replyModal(getModal(2, "Запит на інвентора", "Посилання на Steam", "Посилання на Telegram (для зв'язку)", "Ім'я користувача в Discord", "Скільки вам років?", "Скільки годин ви зіграли на сервері?").build()).queue();

    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        super.onModalInteraction(event);
        if (!event.getModalId().startsWith("stuff_modal")) return;

        //Створення повідомлення заявки
        StringBuilder buffer = new StringBuilder();
        EmbedBuilder builder = new EmbedBuilder();
        String stuff;

        if (event.getModalId().contains("eventmaker")) stuff = "інвентора";
        else stuff = "адміна";

        builder.setTitle("Запит від " + event.getUser().getAsTag() + " на посаду " + stuff);

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

        //Створення заявки
        event.getGuild().getCategoryById(LOOKING_FOR_STUFF_CATEGORY_ID)
                .createTextChannel(event.getUser().getName() + "-очікує")

                //Для @everyone закритого каналу
                .addPermissionOverride(event.getGuild().getPublicRole(),null, EnumSet.of(Permission.VIEW_CHANNEL))
                //Для ролі @support
                .addPermissionOverride(event.getGuild().getRoleById(SUPPORTERS_ROLE_ID), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), null)
                //Для запитувача
                .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), null)

                .queue(ticketChannel ->
                        event.replyEmbeds(new EmbedBuilder().setTitle("Запит створено, натисніть на кнопку нижче, щоб перейти").build())
                                .addActionRow(Button.of(ButtonStyle.LINK, ticketChannel.getJumpUrl(), "Перейти до каналу")).setEphemeral(true).queue(action ->
                                        ticketChannel.sendMessageEmbeds(builder.build())
                                                .addActionRow(
                                                        Button.of(ButtonStyle.DANGER,"close_ticket " + event.getMember().getId(),"Закрити заявку!"),
                                                        Button.of(ButtonStyle.PRIMARY,"open_ticket " + event.getMember().getId(),"Відкрити заявку!"),
                                                        Button.of(ButtonStyle.DANGER,"delete_ticket", "Видалити заявку")
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
