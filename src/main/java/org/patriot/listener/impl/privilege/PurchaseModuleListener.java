package org.patriot.listener.impl.privilege;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jetbrains.annotations.NotNull;
import org.patriot.Constants;
import org.patriot.lib.rcon.Server;
import org.patriot.lib.rcon.privilege.Privilege;
import org.patriot.listener.PatriotListener;

import java.util.stream.Stream;

public class PurchaseModuleListener extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "PurchasePrivilegeModuleListener";
    }

    @Override
    public String getModuleDescription() {
        return "PurchasePrivilegeModuleListener";
    }

    final Privilege[] privileges = {
            bhop,
            vip,
            premium,
            admin,
            mainAdmin,
            moderator
    };

    final Server[] servers = {
            awp_1,
            awp_2,
            awp_3,
            awp_4,
            awp_5,
            mirage_1,
            mirage_2,
            mirage_3,
            mirage_4,
            mirage_5,
            _public
    };

    public static final StringSelectMenu.Builder privilegeSelectMenu  = StringSelectMenu.create("privilege_select_menu").setPlaceholder("Виберіть привілегію");
    public static final StringSelectMenu.Builder privilegeDurationSelectMenu  = StringSelectMenu.create("privilege_duration_select_menu").setPlaceholder("Виберіть тривалість");
    public static final StringSelectMenu.Builder privilegeServerSelectMenu  = StringSelectMenu.create("privilege_server_select_menu").setPlaceholder("Виберіть сервер");

    public PurchaseModuleListener() {
        Stream.of(privileges).forEach(privilege -> privilegeSelectMenu.addOption(privilege.getName(), privilege.toJson().toString()));
        Stream.of(servers).forEach(server -> privilegeServerSelectMenu.addOption(server.getName(), server.toJson().toString()));

        privilegeDurationSelectMenu.addOption("1 місяць", "1");
        privilegeDurationSelectMenu.addOption("1 рік", "2");
        privilegeDurationSelectMenu.addOption("Пожиттєво", "3");
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        super.onButtonInteraction(event);
        if (!event.getButton().getId().equals("purchase_priviligie_btn")) return;


        final EmbedBuilder builder = new EmbedBuilder()
                .setDescription("Привіт " + event.getUser().getAsMention() + "!\n" +
                        "`Будь ласка, заповніть цю форму за допомогою кнопок під цим`\n`повідомленням, щоб придбати привілегію.`")
                .addField("Привілегія", "`Не вибрано...`", true)
                .addField("Тривалість", "`Не вибрано...`", true)
                .addField("Сервер", "`Не вибрано...`", true);

        event.replyEmbeds(builder.build())
                .addActionRow(privilegeSelectMenu.build())
                .addActionRow(privilegeDurationSelectMenu.build())
                .addActionRow(privilegeServerSelectMenu.build())
                .setEphemeral(true)
                .queue();
    }

}
