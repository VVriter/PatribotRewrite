package org.patriot.listener.impl;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;
import org.patriot.Constants;
import org.patriot.Main;
import org.patriot.listener.PatriotListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class CommandsProcessor extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "Command listener module";
    }

    @Override
    public String getModuleDescription() {
        return "I just processing all text commands here";
    }


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        final String content = event.getMessage().getContentRaw();
        if (!content.startsWith(PREFIX)) return;
        if (event.getMessage().getAuthor().isBot() || event.getMessage().getAuthor().isSystem()) return;
        Stream.of(Main.getCommandManager().getCHAT_COMMANDS())
                .filter(command -> content.startsWith(PREFIX + command.getName()))
                .filter(command -> Objects.requireNonNull(event.getMember()).hasPermission(command.getGrantedPermissions()))
                .forEach(command -> {
                    try {
                        command.invoke(event, content.split("\\s+"));
                    } catch (Exception e) {
                        command.onException(e, event);
                    }
                });
    }




    //Slash command processor
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);
        Stream.of(Main.getCommandManager().getSLASH_COMMANDS())
                .filter(command -> command.getName().equals(event.getName()))
                .forEach(command -> {
                    try {
                        command.invoke(event);
                    } catch (Exception e) {
                        command.onException(e, event);
                    }
                });
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        super.onGuildReady(event);
        List<CommandData> data = new ArrayList<>();

        Stream.of(Main.getCommandManager().getSLASH_COMMANDS())
                .forEach(command -> {
                    SlashCommandData slashCommandData = Commands.slash(command.getName(), command.getDescription());
                    slashCommandData.addOptions(command.getOptions());
                    slashCommandData.setDefaultPermissions(DefaultMemberPermissions.enabledFor(command.getGrantedPermissions()));
                    slashCommandData.setGuildOnly(true);
                    data.add(slashCommandData);
                });

        CompletableFuture<Void> feature = new CompletableFuture<>();

        event.getGuild().retrieveCommands().queue(commands -> {
            for (Command command : commands)
                command.delete().queue();
            feature.complete(null);
        });

        feature.join();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        event.getGuild().updateCommands().addCommands(data).queue();
    }

}
