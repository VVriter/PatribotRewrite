package org.patriot.repository;

import lombok.Builder;
import lombok.Getter;

@Builder
public class PatriotUser {

    @Getter private String id;
    @Getter private int money = 0;
    @Getter private int messagesSented = 0;
    @Getter private long timeInVoiceChannel = 0;

}
