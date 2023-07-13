package org.patriot.listener.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;
import org.patriot.Constants;
import org.patriot.listener.PatriotListener;
import java.awt.*;

public class IssuesModule extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "IssuesModuleListener";
    }

    @Override
    public String getModuleDescription() {
        return "IssuesModuleListener";
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        super.onButtonInteraction(event);
        if (event.getButton().getId().equals("create_issue_button")) {
            final TextInput.Builder field1 = TextInput.create("1","Посилання на ваш Steam профіль", TextInputStyle.SHORT);
            final TextInput.Builder field3 = TextInput.create("3","Заголовок ідеї", TextInputStyle.SHORT);
            final TextInput.Builder field4 = TextInput.create("4","Ваша ідея", TextInputStyle.PARAGRAPH);
            event.replyModal(Modal.create("predlozkaModal","Запропонувати ідею!")
                    .addActionRows(ActionRow.of(field1.build()), ActionRow.of(field3.build()), ActionRow.of(field4.build()))
                    .build()).queue();
        }
    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        super.onModalInteraction(event);
        if (event.getModalId().equals("predlozkaModal")) {
            final TextChannel channel = event.getGuild().getTextChannelById(ISSUES_CHANNEL_ID);
            final EmbedBuilder builder = new EmbedBuilder()
                    .setAuthor("Ідея від " + event.getMember().getUser().getAsTag(), null, event.getMember().getUser().getAvatarUrl())
                    .setThumbnail(event.getMember().getUser().getAvatarUrl())
                    .addField(event.getValue("3").getAsString(),event.getValue("4").getAsString(),false)
                    .addField("<:steam:1043105714412978217> Steam профіль",event.getValue("1").getAsString(),true)
                    .setColor(Color.WHITE);
            channel.sendMessageEmbeds(builder.build()).queue(message-> {
                event.replyEmbeds(new EmbedBuilder().setDescription("```Вашу ідею успішно створено, натисніть на кнопку нижче, щоб переглянути її.```").build()).addActionRow(Button.of(ButtonStyle.LINK, message.getJumpUrl(),"Перейти до вашої ідеї!")).setEphemeral(true).queue(e-> {
                    message.addReaction(Emoji.fromFormatted("<:emoji_yes:1043103796030623835>")).queue();
                    message.addReaction(Emoji.fromFormatted("<:no_emoji:1043105690677424188>")).queue();
                });
            });
        }
    }


}
