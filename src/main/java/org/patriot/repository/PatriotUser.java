package org.patriot.repository;

import lombok.Getter;

public class PatriotUser {

    @Getter
    private String id;

    @Getter
    private int money;

    public PatriotUser(String id) {
        this.id = id;
        this.money = 0;
    }

    public PatriotUser(String id, int money) {
        this.id = id;
        this.money = money;
    }

}
