package org.patriot.listener.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
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
import java.util.Objects;

public class SupportModule extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "Support listener module";
    }

    @Override
    public String getModuleDescription() {
        return "I just processing all support embed actions";
    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        super.onStringSelectInteraction(event);
        if (!Objects.equals(event.getSelectMenu().getId(), "reports_module_select_menu")) return;

        //I'm just creating modals here for each setting in drop down menu
        switch (event.getValues().get(0)) {
            case "1": event.replyModal(getModal("Скарга на адміна", "Нік адміна", "Посилання на ваш стім", "Опис ситуації").build()).queue(); break;
            case "2": event.replyModal(getModal("Звязок з керівництвом", "Ваш нік", "Опис").build()).queue(); break;
            case "3": event.replyModal(getModal("Поповнення балансу", "Посилання на стім профіль", "Сумма", "Доп інфа").build()).queue(); break;
            case "4": event.replyModal(getModal("Задати питання", "Заголовок питання", "Опис").build()).queue(); break;
            case "5": event.replyModal(getModal("Прийом багів", "Посилання на стім", "Назва сервера", "Опис").build()).queue(); break;
            case "6": event.replyModal(getModal("Зняття муту", "Посилання на стім", "Опис").build()).queue(); break;
            case "7": event.replyModal(getModal("Зняття бану",  "СПосилання на стім", "Опис").build()).queue(); break;
        }
    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        super.onModalInteraction(event);
        if (!event.getModalId().startsWith("reports_modal")) return;

        StringBuilder info = new StringBuilder();
        for (ModalMapping mapping : event.getValues()) {
            info.append(mapping.getId())
                    .append(":")
                    .append(" ")
                    .append(mapping.getAsString())
                    .append("\n");
        }

        final EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Запит на " + event.getModalId().replace("reports_modal ","") + " " + "від" + " " + event.getUser().getAsTag())
                .setThumbnail(event.getUser().getAvatarUrl())
                .setDescription(info.toString());

        event.getGuild().getCategoryById(TEXNICAL_SUPPORT_CATEGORY_ID)
                .createTextChannel(event.getUser().getName() + "-" + "очікує")


                //For @everyone closed channel
                .addPermissionOverride(event.getGuild().getPublicRole(),null, EnumSet.of(Permission.VIEW_CHANNEL))
                //For @suppoert role
                .addPermissionOverride(event.getGuild().getRoleById(SUPPORTERS_ROLE_ID), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), null)
                //For requester
                .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), null)

                .queue(tciketChannel ->
                    tciketChannel.sendMessageEmbeds(embed.build())
                            .addActionRow(
                                    Button.of(ButtonStyle.DANGER,"close_ticket " + event.getMember().getId(),"Закрити тікет!"),
                                    Button.of(ButtonStyle.PRIMARY,"open_ticket " + event.getMember().getId(),"Відкрити тікет!"),
                                    Button.of(ButtonStyle.DANGER,"delete_ticket", "Видалити тікет")
                            )
                            .queue(message -> {
                                        event.replyEmbeds(
                                                new EmbedBuilder()
                                                        .setTitle("Запит створений, натисніть на кнопку нище, щоб перейти")
                                                        .build()
                                        ).addActionRow(Button.of(ButtonStyle.LINK, tciketChannel.getJumpUrl(), "Перейти в канал")).setEphemeral(true).queue(rest ->
                                                message.reply("В данний момент всі Модератори зайняті. Очікуйте, наша команда працює на перемогу." +
                                                                " Слава Україні \uD83C\uDDFA\uD83C\uDDE6!")
                                                        .queue()
                                        );
                                    }
                            )
                );
    }





    //Cool utility to create modals faster
    private Modal.Builder getModal(String name, String... vals) {
        Modal.Builder mdl = Modal.create("reports_modal" + " " + name, name);
        for (String s : vals) {
            TextInput.Builder builder = TextInput.create(s, s, TextInputStyle.SHORT);
            mdl.addActionRows(ActionRow.of(builder.build()));
        }
        return mdl;
    }

}
