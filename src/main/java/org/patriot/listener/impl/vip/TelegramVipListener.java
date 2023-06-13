package org.patriot.listener.impl.vip;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
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

public class TelegramVipListener extends ListenerAdapter implements PatriotListener, Constants {

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
            event.replyModal(getModal("Получить бесплатную випку!", "Ссылка на стим", "Ваш ник в Discord", "Название сервера CS:GO", "Ссылка на Telegram аккаунт").build()).queue();

        if (event.getButton().getId().startsWith("tgvip_close_ticket")) {
            final String userId = event.getButton().getId().split(" ")[1];
            try {
                final Member member = event.getGuild().getMemberById(userId);
                event.getChannel().asTextChannel().getManager().putPermissionOverride(member,null, EnumSet.of(Permission.MESSAGE_SEND, Permission.VIEW_CHANNEL)).queue(action ->
                        event.reply("Модератор " + event.getMember().getAsMention() + " закрыл тикет.").queue()
                );
            } catch (Exception e) {
                event.reply("Произошла ошибка, не удалось получить юзера.").setEphemeral(true).queue();
            }
        }

        if (event.getButton().getId().startsWith("tgvip_open_ticket")) {
            final String userId = event.getButton().getId().split(" ")[1];
            try {
                final Member member = event.getGuild().getMemberById(userId);
                event.getChannel().asTextChannel().getManager().putPermissionOverride(member, EnumSet.of(Permission.MESSAGE_SEND, Permission.VIEW_CHANNEL), null).queue(action ->
                        event.reply("Модератор  " + event.getMember().getAsMention() + " открыл тикет.").queue()
                );
            } catch (Exception e) {
                event.reply("Произошла ошибка, не удалось получить юзера.").setEphemeral(true).queue();
            }
        }

        if (event.getButton().getId().equals("tgvip_delete_ticket"))
            event.getChannel().delete().queue();

    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        super.onModalInteraction(event);
        if (!event.getModalId().startsWith("tgvip_modal")) return;

        StringBuffer stringBuffer = new StringBuffer();
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

        event.getGuild().getCategoryById(TELEGRAM_VIP_CATEGORY_ID).createTextChannel(event.getUser().getName() + " ticket")
                //For @everyone closed channel
                .addPermissionOverride(event.getGuild().getPublicRole(),null, EnumSet.of(Permission.VIEW_CHANNEL))
                //For @suppoert role
                .addPermissionOverride(event.getGuild().getRoleById(SUPPORTERS_ROLE_ID), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), null)
                //For requester
                .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), null)
                .queue(channel -> {
                    event.replyEmbeds(
                            new EmbedBuilder()
                                    .setTitle("Запрос создан, нажмите на кнопку ниже, чтобы перейти")
                                    .build()
                    ).addActionRow(Button.of(ButtonStyle.LINK, channel.getJumpUrl(), "Перейти в канал")).setEphemeral(true).queue(action ->
                            channel.sendMessageEmbeds(builder.build()).setContent("@everyone " + event.getUser().getAsMention())
                                    .addActionRow(
                                            Button.of(ButtonStyle.DANGER,"tgvip_close_ticket " + event.getMember().getId(),"Закрыть тикет!"),
                                            Button.of(ButtonStyle.PRIMARY,"tgvip_open_ticket " + event.getMember().getId(),"Открыть тикет!"),
                                            Button.of(ButtonStyle.DANGER,"tgvip_delete_ticket", "Удалить тикет")
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
