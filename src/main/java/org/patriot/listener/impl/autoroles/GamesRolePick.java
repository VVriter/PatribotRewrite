package org.patriot.listener.impl.autoroles;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.patriot.Constants;
import org.patriot.listener.PatriotListener;

public class GamesRolePick extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "GamesRolePickModuleListener";
    }

    @Override
    public String getModuleDescription() {
        return "Giving roles";
    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        super.onStringSelectInteraction(event);
        if (!event.getSelectMenu().getId().equals("game_role_picker")) return;
        final Role role = event.getGuild().getRoleById(event.getValues().get(0));

        event.getGuild().addRoleToMember(event.getMember(), role).queue(action ->
                event.reply("Выдал тебе роль " + role.getAsMention()).setEphemeral(true).queue()
        );

    }

}
