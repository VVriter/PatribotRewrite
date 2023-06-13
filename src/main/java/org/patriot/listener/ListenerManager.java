package org.patriot.listener;

import lombok.Getter;
import org.patriot.Logger;
import org.patriot.listener.impl.*;
import org.patriot.listener.impl.autoroles.GamesRolePickModuleListener;
import org.patriot.listener.impl.autoroles.GetTwitchRoleButtonListener;
import org.patriot.listener.impl.autoroles.ReactionRoleModuleListener;
import org.patriot.listener.impl.privilege.PurchasePrivilegeModuleListener;
import org.patriot.listener.impl.privilege.PurchasePrivilegeSelectOptions;
import org.patriot.listener.impl.privilege.PurchasePrivilegeSteamSet;
import org.patriot.listener.impl.privilege.PurchasePriviligeCheckPayment;
import org.patriot.listener.impl.vip.TelegramVipListener;
import java.util.stream.Stream;

public class ListenerManager {

    @Getter
    private final Object[] LISTENERS = {
            new CommandsListener(),
            new OnUserJoinDmListener(),
            new SupportModuleListener(),
            new ReactionRoleModuleListener(),
            new TelegramVipListener(),
            new GetTwitchRoleButtonListener(),
            new StuffLookingModuleListener(),
            new GamesRolePickModuleListener(),

            new PurchasePrivilegeModuleListener(),
            new PurchasePrivilegeSelectOptions(),
            new PurchasePrivilegeSteamSet(),
            new PurchasePriviligeCheckPayment(),
            new EmbedExecutor(),
            new IssuesModuleListener()
    };

    public ListenerManager() {
        Logger.log("listener-manager", "Listener manager started, loading modules...");
        Stream.of(LISTENERS).forEach(listener -> Logger.log("listener-manager", "Loaded" + " " + ((PatriotListener) listener).getModuleName() + " " + "module."));
    }

}
