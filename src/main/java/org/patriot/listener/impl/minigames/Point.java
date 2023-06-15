package org.patriot.listener.impl.minigames;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.patriot.Constants;
import org.patriot.listener.PatriotListener;
import org.patriot.minegame.CardUtil;
import org.patriot.minegame.Cards;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Point extends ListenerAdapter implements PatriotListener, Constants, Cards {

    @Override
    public String getModuleName() {
        return "PointListener";
    }

    @Override
    public String getModuleDescription() {
        return "Point";
    }


    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        super.onButtonInteraction(event);
        if ((event.getButton().getId().startsWith("getCards") || event.getButton().getId().startsWith("passCards"))
                && event.getButton().getId().split(" ")[1].equals(event.getUser().getId())) {

            if (event.getButton().getId().startsWith("getCards"))
                processGetCard(event, action -> processComputerAction());


            if (event.getButton().getId().startsWith("passCards"))
                processPassCard(event, action -> processComputerAction());

        } else event.reply("Это не ваша игра :(").setEphemeral(true).queue();
    }

    private void processComputerAction() {

    }

    private void processGetCard(ButtonInteractionEvent event, Consumer<String> action) {
        final EmbedBuilder embedNow = new EmbedBuilder(event.getMessage().getEmbeds().get(0));
        final MessageEmbed.Field field = embedNow.getFields().get(0);
    }

    private void processPassCard(ButtonInteractionEvent event, Consumer<?> action) {
        final EmbedBuilder embedNow = new EmbedBuilder(event.getMessage().getEmbeds().get(0));

    }


    private int getPointsFromString(String s) {
        AtomicInteger points = new AtomicInteger();
        final String[] vals = s.split(" ");
        Stream.of(vals).forEach(emoji ->
                Stream.of(CardUtil.getPokerDeck()).forEach(card -> {
                    if (card.getEmoji().getFormatted().equals(emoji)) points.addAndGet(card.getValue());
                })
        );

        return points.get();
    }

}
