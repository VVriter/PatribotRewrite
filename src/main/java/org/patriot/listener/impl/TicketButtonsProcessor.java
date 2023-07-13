package org.patriot.listener.impl;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.patriot.Logger;
import org.patriot.Main;
import org.patriot.listener.PatriotListener;

import java.util.EnumSet;

import static org.patriot.Constants.SUPPORTERS_ROLE_ID;

public class TicketButtonsProcessor extends ListenerAdapter implements PatriotListener {

    @Override
    public String getModuleName() {
        return "TicketButtonsProcessor";
    }

    @Override
    public String getModuleDescription() {
        return "TicketButtonsProcessor";
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        super.onButtonInteraction(event);

        //Check for user can use this button
        if (canUse(event.getMember(), event)) {

            //Close ticket button handler
            if (event.getButton().getId().startsWith("close_ticket")) {
                final String userId = event.getButton().getId().split(" ")[1];
                try {
                    final Member member = event.getGuild().getMemberById(userId);
                    event.getChannel().asTextChannel().getManager().putPermissionOverride(member,null, EnumSet.of(Permission.MESSAGE_SEND, Permission.VIEW_CHANNEL)).queue(action ->
                            event.reply("Модератор " + event.getMember().getAsMention() + " закрив тікет.").queue(act ->
                                    Logger.ds("ticket-management", "Модератор " + event.getMember().getAsMention() + " закрив тікет.\n Інфа про тікет:\n`Ім'я канала:` " + event.getChannel().getName().replace("відкритий", "").replace("очікує", "").replace("закритий", ""), actioned -> {
                                        event.getChannel().asTextChannel().getManager().setName(event.getChannel().asTextChannel().getName().replace("відкритий", "").replace("очікує", "").replace("закритий", "") + "-закритий")
                                                .queue();
                                    })
                            )
                    );
                } catch (Exception e) {
                    event.reply("Произошла ошибка, не удалось получить юзера.").setEphemeral(true).queue();
                }
            }

            //Open ticket button handler
            if (event.getButton().getId().startsWith("open_ticket")) {
                final String userId = event.getButton().getId().split(" ")[1];
                try {
                    final Member member = event.getGuild().getMemberById(userId);
                    event.getChannel().asTextChannel().getManager().putPermissionOverride(member, EnumSet.of(Permission.MESSAGE_SEND, Permission.VIEW_CHANNEL), null).queue(action ->
                            event.reply("Модератор  " + event.getMember().getAsMention() + " відкрив тікет.").queue(act ->
                                    Logger.ds("ticket-management", "Модератор " + event.getMember().getAsMention() + " відкрив тікет.\n Інфа про тікет:\n`І'мя канала:` " + event.getChannel().getName().replace("открыт", "").replace("ожидает", "").replace("закрыт", ""), actioned -> {
                                        event.getChannel().asTextChannel().getManager().setName(event.getChannel().asTextChannel().getName().replace("открыт", "").replace("відкритий", "").replace("очікує", "").replace("закритий", "") + "-відкритий")
                                                .queue();
                                    })
                            )
                    );
                } catch (Exception e) {
                    event.reply("Произошла ошибка, не удалось получить юзера.").setEphemeral(true).queue();
                }
            }


            //Delete ticket button handler
            if (event.getButton().getId().startsWith("delete_ticket"))
                event.getChannel().delete().queue();


        } else event.reply("Ви не можете використовувати цю кнопку, ви не адміністратор.").setEphemeral(true).queue();

    }



    //Check for user can use button
    private boolean canUse(Member member, ButtonInteractionEvent event) {
        return member.getRoles().contains(event.getGuild().getRoleById(SUPPORTERS_ROLE_ID)) || member.hasPermission(Permission.ADMINISTRATOR);
    }
}
