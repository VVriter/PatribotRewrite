package org.patriot.listener.impl.vip;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
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

public class TelegramListener extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "TelegramVipListener";
    }

    @Override
    public String getModuleDescription() {
        return "Ticket system for TelegramVip getting";
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        super.onButtonInteraction(event);
        if (event.getButton().getId().equals("tgvip"))
            event.replyModal(getModal("Отримати безкоштовну VIP", "Посилання на Steam", "Ваш нік в Discord", "Назва сервера CS:GO", "Посилання на Telegram аккаунт").build()).queue();
    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        super.onModalInteraction(event);
        if (!event.getModalId().startsWith("tgvip_modal")) return;

        StringBuilder stringBuffer = new StringBuilder();
        for (ModalMapping mapping : event.getValues()) {
            stringBuffer.append("`")
                    .append(mapping.getId())
                    .append(":")
                    .append("`")
                    .append(" ")
                    .append(mapping.getAsString())
                    .append("\n");
        }

        final EmbedBuilder builder = new EmbedBuilder()
                .setDescription(stringBuffer.toString())
                .setThumbnail(event.getUser().getAvatarUrl());

        event.getGuild().getCategoryById(TELEGRAM_VIP_CATEGORY_ID).createTextChannel(event.getUser().getName() + "-очікує")
                //Для @everyone закритого каналу
                .addPermissionOverride(event.getGuild().getPublicRole(),null, EnumSet.of(Permission.VIEW_CHANNEL))
                //Для ролі @support
                .addPermissionOverride(event.getGuild().getRoleById(SUPPORTERS_ROLE_ID), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), null)
                //Для запитувача
                .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), null)
                .queue(channel -> {
                    event.replyEmbeds(
                            new EmbedBuilder()
                                    .setTitle("Запит створено, натисніть на кнопку нижче, щоб перейти")
                                    .build()
                    ).addActionRow(Button.of(ButtonStyle.LINK, channel.getJumpUrl(), "Перейти до каналу")).setEphemeral(true).queue(action ->
                            channel.sendMessageEmbeds(builder.build()).setContent(event.getUser().getAsMention())
                                    .addActionRow(
                                            Button.of(ButtonStyle.DANGER,"close_ticket " + event.getMember().getId(),"Закрити заявку!"),
                                            Button.of(ButtonStyle.PRIMARY,"open_ticket " + event.getMember().getId(),"Відкрити заявку!"),
                                            Button.of(ButtonStyle.DANGER,"delete_ticket", "Видалити заявку")
                                    )
                                    .queue()
                    );
                });
    }


    //Cool utility to create modals faster
    private Modal.Builder getModal(String name, String... vals) {
        Modal.Builder mdl = Modal.create("tgvip_modal" + " " + name, name);
        for (String s : vals) {
            TextInput.Builder builder = TextInput.create(s, s, TextInputStyle.SHORT);
            mdl.addActionRows(ActionRow.of(builder.build()));
        }
        return mdl;
    }

}
