package org.patriot.listener.impl.privilege;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.patriot.Constants;
import org.patriot.listener.PatriotListener;

import java.util.List;

public class PurchasePrivilegeSelectOptions extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "PurchasePrivilegeSelectOptions";
    }

    @Override
    public String getModuleDescription() {
        return "PurchasePrivilegeSelectOptions";
    }

    @Override
    public void onSelectMenuInteraction(@NotNull SelectMenuInteractionEvent event) {
        super.onSelectMenuInteraction(event);
        if (event.getSelectMenu().getId().equals("privilege_select_menu")) {
            MessageEmbed embed = event.getMessage().getEmbeds().get(0);
            EmbedBuilder builder = new EmbedBuilder(embed);
            List<MessageEmbed.Field> fields = new EmbedBuilder(embed).getFields();
            builder.clearFields();
            MessageEmbed.Field field = new MessageEmbed.Field("Привилегия", "`" + new JSONObject(event.getValues().get(0)).getString("name") + "`", true);
            builder.addField(field);
            builder.addField(fields.get(1));
            builder.addField(fields.get(2));

            edit(event, builder);
        }

        if (event.getSelectMenu().getId().equals("privilege_duration_select_menu")) {
            String val = "";
            switch (event.getValues().get(0)) {
                case "1": val = "1 месяц";break;
                case "2": val = "1 год"; break;
                case "3": val = "Пожизненно";break;
            }

            MessageEmbed embed = event.getMessage().getEmbeds().get(0);
            EmbedBuilder builder = new EmbedBuilder(embed);
            List<MessageEmbed.Field> fields = new EmbedBuilder(embed).getFields();
            builder.clearFields();
            MessageEmbed.Field field = new MessageEmbed.Field("Длительность", "`" + val + "`", true);
            builder.addField(fields.get(0));
            builder.addField(field);
            builder.addField(fields.get(2));

            edit(event, builder);
        }

        if (event.getSelectMenu().getId().equals("privilege_server_select_menu")) {
            final JSONObject server = new JSONObject(event.getValues().get(0));
            final String serverName = server.getString("name");

            MessageEmbed embed = event.getMessage().getEmbeds().get(0);
            EmbedBuilder builder = new EmbedBuilder(embed);
            List<MessageEmbed.Field> fields = new EmbedBuilder(embed).getFields();
            builder.clearFields();
            MessageEmbed.Field field = new MessageEmbed.Field("Сервер", "`" + serverName + "`", true);
            builder.addField(fields.get(0));
            builder.addField(fields.get(1));
            builder.addField(field);

            edit(event, builder);
        }
    }


    private void edit(SelectMenuInteractionEvent event, EmbedBuilder builder) {
        ActionRow row = null;

        JSONObject info = new JSONObject()
                .put("p", builder.getFields().get(0).getValue().replace("`", ""))
                .put("d", builder.getFields().get(1).getValue().replace("`", ""))
                .put("s", builder.getFields().get(2).getValue().replace("`", ""));

        if (builder.getFields().get(0).getValue().contains("...") || builder.getFields().get(1).getValue().contains("...") || builder.getFields().get(2).getValue().contains("..."))
            row = ActionRow.of(Button.of(ButtonStyle.SUCCESS, "approve_choose_role|" + info.toString(), "ㅤㅤㅤㅤㅤㅤㅤㅤㅤПодтвердить выборㅤㅤㅤㅤㅤㅤㅤㅤ").withDisabled(true));
        else
            row = ActionRow.of(Button.of(ButtonStyle.SUCCESS, "approve_choose_role|" + info.toString(), "ㅤㅤㅤㅤㅤㅤㅤㅤㅤПодтвердить выборㅤㅤㅤㅤㅤㅤㅤㅤ").withDisabled(false));



        event.editMessageEmbeds(builder.build()).setComponents(
                ActionRow.of(PurchasePrivilegeModuleListener.privilegeSelectMenu.build()),
                ActionRow.of(PurchasePrivilegeModuleListener.privilegeDurationSelectMenu.build()),
                ActionRow.of(PurchasePrivilegeModuleListener.privilegeServerSelectMenu.build()),
                row
        ).queue();
    }

}
