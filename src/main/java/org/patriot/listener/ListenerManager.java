package org.patriot.listener;

import lombok.Getter;
import org.patriot.Logger;
import org.patriot.listener.impl.CommandsListener;
import org.patriot.listener.impl.OnUserJoinDmListener;

public class ListenerManager {

    @Getter
    private final Object[] LISTENERS = {
            new CommandsListener(),
            new OnUserJoinDmListener()
    };

    public ListenerManager() {
        Logger.log("listener-manager", "Listener manager started, loading modules...");
    }
}
