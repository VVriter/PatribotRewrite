package org.patriot.listener.impl.privilege;

import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.patriot.Constants;
import org.patriot.lib.fondy.Currency;
import org.patriot.lib.fondy.FondyClient;
import org.patriot.lib.fondy.Lang;
import org.patriot.lib.rcon.Server;
import org.patriot.lib.rcon.privilege.Privilege;
import org.patriot.lib.steam.SteamClient;
import org.patriot.listener.PatriotListener;

import java.util.Arrays;

public class PurchasePrivilegeSteamSet extends ListenerAdapter implements PatriotListener, Constants {
    @Override
    public String getModuleName() {
        return "PurchasePrivilegeSteamSet";
    }

    @Override
    public String getModuleDescription() {
        return "PurchasePrivilegeSteamSet";
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        super.onButtonInteraction(event);
        if (!event.getButton().getId().startsWith("approve_choose_role")) return;

        final JSONObject info = new JSONObject(event.getButton().getId().split("\\|")[1]);
        event.replyModal(getModal("Введіть посилання на ваш стім", info, "Посилання").build()).queue();
    }


    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        super.onModalInteraction(event);
        if (!event.getModalId().startsWith("purchase_steam_modal")) return;

        try {
            SteamClient steamClient = new SteamClient(event.getValues().get(0).getAsString());
            final EmbedBuilder builder = new EmbedBuilder(event.getMessage().getEmbeds().get(0));
            builder.setFooter("Стім: " + steamClient.getSteamUser().getSteamId() + " | " + steamClient.getSteamUser().getName(), steamClient.getSteamUser().getAvatarUrl());


            final JSONObject info = new JSONObject(event.getModalId().split("\\|")[1]);
            final Privilege privilege = getPrivilegeByName(info.getString("p"));
            final String price = getMoneyToPay(privilege, info.getString("d"));
            final Server server = getServerByName(info.getString("s"));


            final FondyClient fondyClient = new FondyClient.Builder()
                    .setMerchantId(MERCHANT_ID)
                    .setSignatureProtector(SIGNATURE_PROTECTOR)
                    .setCurrency(Currency.UAH)
                    .setLang(Lang.RUSSIAN)
                    .setMoneyAmount(price)
                    .setDescription("Оплата привілегії " + privilege.getName() + " на сервері " + server.getName())
                    .build();

            event.editMessageEmbeds(builder.build())
                    .setComponents(
                            ActionRow.of(Button.of(ButtonStyle.LINK, fondyClient.generatePaymentUrl(), "ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤОплатитиㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ")),
                            ActionRow.of(Button.of(ButtonStyle.SECONDARY, "pc|" + fondyClient.getOrderId() + "|" + server.getName() + "|" + privilege.getName() + "|" + info.getString("d") + "|" + steamClient.getSteamUser().getSteamId(), "ㅤㅤㅤㅤㅤㅤㅤㅤㅤПідтвердити оплатуㅤㅤㅤㅤㅤㅤㅤㅤㅤ"))
                    )
                    .queue();
        } catch (Exception e) {
            event.reply("Ви ввели НЕВІРНІ дані. Будь ласка, натисніть на кнопку ще раз і введіть правильні дані.")
                    .setEphemeral(true).queue();
        }
    }


    @SneakyThrows
    private String getMoneyToPay(Privilege privilege, String duration) {
        switch (duration) {
            case "1 місяць": return String.valueOf(privilege.getPrice().getMonthPrice());
            case "1 рік": return String.valueOf(privilege.getPrice().getYearPrice());
            case "Пожиттєво": return String.valueOf(privilege.getPrice().getLifetimePrice());
            default: throw new Exception("duration " + duration + " not found");
        }
    }


    @SneakyThrows
    private Privilege getPrivilegeByName(String name) {
        for (Privilege privilege : privileges) {
            if (privilege.getName().equals(name)) return privilege;
        }

        throw new Exception("Privilege " + name + " not found");
    }

    @SneakyThrows
    private Server getServerByName(String name) {
        for (Server server : servers)
            if (server.getName().equals(name)) return server;

        throw new Exception("Server " + name + " not found");
    }



    //Cool utility to create modals faster
    private Modal.Builder getModal(String name, JSONObject o, String... vals) {
        Modal.Builder mdl = Modal.create("purchase_steam_modal" + "|" + o.toString(), name);
        for (String s : vals) {
            TextInput.Builder builder = TextInput.create(s, s, TextInputStyle.SHORT);
            mdl.addActionRows(ActionRow.of(builder.build()));
        }
        return mdl;
    }


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


    final Privilege[] privileges = {
            bhop,
            vip,
            premium,
            admin,
            mainAdmin,
            moderator
    };
}
