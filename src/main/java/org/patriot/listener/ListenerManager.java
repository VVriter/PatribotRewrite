package org.patriot.listener;

import lombok.Getter;
import org.patriot.Logger;
import org.patriot.listener.impl.*;
import org.patriot.listener.impl.autoroles.GamesRolePick;
import org.patriot.listener.impl.autoroles.GetTwitchRoleButton;
import org.patriot.listener.impl.autoroles.ReactionRoleModule;
import org.patriot.listener.impl.privilege.PurchaseModuleListener;
import org.patriot.listener.impl.privilege.PurchaseSelectOptions;
import org.patriot.listener.impl.privilege.PurchasePrivilegeSteamSet;
import org.patriot.listener.impl.privilege.PurchaseCheckPayment;
import org.patriot.listener.impl.vip.TelegramListener;
import java.util.stream.Stream;

public class ListenerManager {

    @Getter
    private final Object[] LISTENERS = {
            new Commands(),
            new OnUserJoinDm(),
            new SupportModule(),
            new ReactionRoleModule(),
            new TelegramListener(),
            new GetTwitchRoleButton(),
            new StuffLookingModule(),
            new GamesRolePick(),

            new PurchaseModuleListener(),
            new PurchaseSelectOptions(),
            new PurchasePrivilegeSteamSet(),
            new PurchaseCheckPayment(),
            new EmbedExecutor(),
            new IssuesModule()
    };

    public ListenerManager() {
        Logger.log("listener-manager", "Listener manager started, loading modules...");
        Stream.of(LISTENERS).forEach(listener -> Logger.log("listener-manager", "Loaded" + " " + ((PatriotListener) listener).getModuleName() + " " + "module."));
    }

}
