package org.patriot.repository;

import java.lang.reflect.Member;

public interface User {
    Member getMember();
    int getMessagesCount();
    long getTimeInVc();
    int getCredits();
    int getPoints();
}
