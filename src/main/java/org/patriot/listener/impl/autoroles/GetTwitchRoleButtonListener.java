package org.patriot.listener.impl.autoroles;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.patriot.Constants;
import org.patriot.listener.PatriotListener;

public class GetTwitchRoleButtonListener extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "GetTwitchRoleButtonListener";
    }

    @Override
    public String getModuleDescription() {
        return "Just processing twich role button and giving role xd";
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        super.onButtonInteraction(event);
        if (!event.getButton().getId().equals("stream_watch_button")) return;

        final Role role = event.getGuild().getRoleById(TWITCH_ROLE_ID);
        event.getGuild().addRoleToMember(event.getMember(), role).queue(action ->
            event.reply("Вам выдана роль " + role.getAsMention()).setEphemeral(true).queue()
        );
    }

}
