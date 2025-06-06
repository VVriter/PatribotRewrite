package org.patriot.listener.impl.privilege;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.json.JSONObject;
import org.patriot.Constants;
import org.patriot.listener.PatriotListener;

import java.util.List;

public class PurchaseSelectOptions extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "PurchasePrivilegeSelectOptions";
    }

    @Override
    public String getModuleDescription() {
        return "PurchasePrivilegeSelectOptions";
    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        super.onStringSelectInteraction(event);
        if (event.getSelectMenu().getId().equals("privilege_select_menu")) {
            MessageEmbed embed = event.getMessage().getEmbeds().get(0);
            EmbedBuilder builder = new EmbedBuilder(embed);
            List<MessageEmbed.Field> fields = new EmbedBuilder(embed).getFields();
            builder.clearFields();
            MessageEmbed.Field field = new MessageEmbed.Field("Привілегія", "`" + new JSONObject(event.getValues().get(0)).getString("name") + "`", true);
            builder.addField(field);
            builder.addField(fields.get(1));
            builder.addField(fields.get(2));

            edit(event, builder);
        }

        if (event.getSelectMenu().getId().equals("privilege_duration_select_menu")) {
            String val = "";
            switch (event.getValues().get(0)) {
                case "1": val = "1 місяць";break;
                case "2": val = "1 рік"; break;
                case "3": val = "Пожиттєво";break;
            }

            MessageEmbed embed = event.getMessage().getEmbeds().get(0);
            EmbedBuilder builder = new EmbedBuilder(embed);
            List<MessageEmbed.Field> fields = new EmbedBuilder(embed).getFields();
            builder.clearFields();
            MessageEmbed.Field field = new MessageEmbed.Field("Тривалість", "`" + val + "`", true);
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


    private void edit(StringSelectInteractionEvent event, EmbedBuilder builder) {
        ActionRow row = null;

        JSONObject info = new JSONObject()
                .put("p", builder.getFields().get(0).getValue().replace("`", ""))
                .put("d", builder.getFields().get(1).getValue().replace("`", ""))
                .put("s", builder.getFields().get(2).getValue().replace("`", ""));

        if (builder.getFields().get(0).getValue().contains("...") || builder.getFields().get(1).getValue().contains("...") || builder.getFields().get(2).getValue().contains("..."))
            row = ActionRow.of(Button.of(ButtonStyle.SUCCESS, "approve_choose_role|" + info.toString(), "ㅤㅤㅤㅤㅤㅤㅤㅤㅤПідтвердити вибірㅤㅤㅤㅤㅤㅤㅤㅤ").withDisabled(true));
        else
            row = ActionRow.of(Button.of(ButtonStyle.SUCCESS, "approve_choose_role|" + info.toString(), "ㅤㅤㅤㅤㅤㅤㅤㅤㅤПідтвердити вибірㅤㅤㅤㅤㅤㅤㅤㅤ").withDisabled(false));



        event.editMessageEmbeds(builder.build()).setComponents(
                ActionRow.of(PurchaseModuleListener.privilegeSelectMenu.build()),
                ActionRow.of(PurchaseModuleListener.privilegeDurationSelectMenu.build()),
                ActionRow.of(PurchaseModuleListener.privilegeServerSelectMenu.build()),
                row
        ).queue();
    }


}
