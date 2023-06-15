package org.patriot.minegame;

import lombok.Builder;
import lombok.Getter;
import net.dv8tion.jda.api.entities.emoji.Emoji;

@Builder
public class Card {
    @Getter private String name;
    @Getter private Emoji emoji;
    @Getter private int value;
    @Getter private Suit suit;
}
