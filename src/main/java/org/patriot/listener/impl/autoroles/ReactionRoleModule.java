package org.patriot.listener.impl.autoroles;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.patriot.listener.PatriotListener;

public class ReactionRoleModule extends ListenerAdapter implements PatriotListener {

    @Override
    public String getModuleName() {
        return "ReactionRoleModuleListener";
    }

    @Override
    public String getModuleDescription() {
        return "Giving roles when users chooses it!";
    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        super.onStringSelectInteraction(event);
        if (!event.getInteraction().getId().equals("role_switcher")) return;

        Role role = event.getGuild().getRoleById(event.getValues().get(0));
        event.getGuild().addRoleToMember(event.getMember(), role).queue(action ->
                event.replyEmbeds(new EmbedBuilder().setDescription("```Раді тебе бачити на проекті \"Патріот\"!```\n" +
                                "Роль " + role.getAsMention() + " отримана!")
                        .setColor(3553599).build()).setEphemeral(true).queue()
        );
    }

}
