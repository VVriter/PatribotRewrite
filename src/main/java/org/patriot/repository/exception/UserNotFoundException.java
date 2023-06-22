package org.patriot.repository.exception;

import org.patriot.Logger;

public class UserNotFoundException extends Throwable {

    private String id;

    public UserNotFoundException(String message) {
        super(message);
        this.id = message;
    }

    public void printStackTrace() {
        Logger.log("[UserNotFoundException]", "User " + id + " does not exists in db");
    }

}
