package org.patriot.commands.message.minigame;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.patriot.Constants;
import org.patriot.commands.ChatCommand;
import org.patriot.minegame.CardUtil;
import org.patriot.minegame.Cards;

import java.util.Arrays;
import java.util.Collections;

import lombok.val;

public class Point implements ChatCommand, Constants, Cards {

    @Override
    public String getName() {
        return "point";
    }

    @Override
    public String getDescription() {
        return "Игра в очко. " + PREFIX + getName() + " <ставка>";
    }

    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        final EmbedBuilder builder = new EmbedBuilder()
                .setThumbnail("https://cdn.discordapp.com/attachments/1118271110383947877/1118467337385947156/all2.png")
                .setTitle("<:b_:1118270432907374652> Очко!")
                .setDescription("**" + event.getMember().getUser().getName() + "**," + " " + "ваш ход.\n" +
                        "Ставка: **" + args[1] + "**" + "\uD83D\uDCB0");
        val cards = Arrays.asList(CardUtil.getPokerDeck());
        Collections.shuffle(cards);

        StringBuilder content = new StringBuilder();
        int value = 0;
        for (int i = 0; i<2; i++) {
            content.append(cards.get(i).getEmoji().getFormatted()).append(" ");
            value += cards.get(i).getValue();
        }
        builder.addField("Карты игрока (" + value + ")", content.toString(), true);

        StringBuilder content2 = new StringBuilder();
        int value2 = 0;
        for (int i = 2; i<5; i++) {
            content2.append(cards.get(i).getEmoji().getFormatted()).append(" ");
            value2 += cards.get(i).getValue();
        }

        builder.addField("Карты дилера (" + value2 + ")", content2.toString(), true);
        builder.setFooter("Игровое меню вызвано " + event.getMember().getUser().getAsTag(), event.getMember().getUser().getAvatarUrl());

        event.getMessage().replyEmbeds(builder.build())
                .addActionRow(
                        Button.of(ButtonStyle.SECONDARY, "getCards" + " " + event.getMember().getId(), "Взять карты", Emoji.fromFormatted("<:all1:1118270634435289108>")),
                        Button.of(ButtonStyle.SECONDARY, "passCards" + " " + event.getMember().getId(), "Не, я пасс", Emoji.fromFormatted("<:2k380812:1099772188195827733>"))
                )
                .queue();
    }

    @Override
    public void onException(Exception e, MessageReceivedEvent event) {
        event.getMessage().reply(getDescription()).queue();
    }

}
