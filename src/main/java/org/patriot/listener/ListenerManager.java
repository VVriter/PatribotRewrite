package org.patriot.listener;

import lombok.Getter;
import org.patriot.Logger;
import org.patriot.listener.impl.CommandsListener;
import org.patriot.listener.impl.OnUserJoinDmListener;
import org.patriot.listener.impl.ReactionRoleModuleListener;
import org.patriot.listener.impl.SupportModuleListener;
import org.patriot.listener.impl.vip.TelegramVipListener;

import java.util.stream.Stream;

public class ListenerManager {

    @Getter
    private final Object[] LISTENERS = {
            new CommandsListener(),
            new OnUserJoinDmListener(),
            new SupportModuleListener(),
            new ReactionRoleModuleListener(),
            new TelegramVipListener()
    };

    public ListenerManager() {
        Logger.log("listener-manager", "Listener manager started, loading modules...");
        Stream.of(LISTENERS).forEach(listener -> Logger.log("listener-manager", "Loaded" + " " + ((PatriotListener) listener).getModuleName() + " " + "module."));
    }
}
