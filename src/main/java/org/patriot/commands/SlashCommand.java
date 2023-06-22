package org.patriot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public interface SlashCommand {
    String getName();
    default String getDescription() {
        return "Эта команда не имеет описания.";
    }
    default Permission[] getGrantedPermissions() {
        return new Permission[] {
                Permission.MESSAGE_SEND
        };
    }
    void invoke(SlashCommandInteractionEvent event);
    default OptionData[] getOptions() {
        return new OptionData[] {};
    }
    default void onException(Exception e, SlashCommandInteractionEvent event) {

    }
}
