package org.patriot.listener.impl.privilege;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.patriot.Constants;
import org.patriot.Logger;
import org.patriot.Main;
import org.patriot.lib.fondy.FondyUtil;
import org.patriot.lib.rcon.Server;
import org.patriot.lib.rcon.privilege.Privilege;
import org.patriot.lib.steam.SteamClient;
import org.patriot.listener.PatriotListener;

public class PurchaseCheckPayment extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "PurchasePriviligeCheckPayment";
    }

    @Override
    public String getModuleDescription() {
        return "PurchasePriviligeCheckPayment";
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        super.onButtonInteraction(event);
        if (!event.getButton().getId().startsWith("pc")) return;

        final String[] vals = event.getButton().getId().split("\\|");

        final String orderId = vals[1];
        final String serverName = vals[2];
        final String privilegeName = vals[3];
        final String duration = vals[4];
        final String steamId = vals[5];

        String time = String.valueOf(60*60*24*30);

        switch (duration) {
            case "1 месяц": time = String.valueOf(60*60*24*30); break;
            case "1 год": time = String.valueOf(60*60*24*30*12); break;
            case "Пожизнено": time = String.valueOf(60*60*24*30*12*10); break;
        }

        try {
            final String orderStatus = FondyUtil.getPaymentStatus(orderId, SIGNATURE_PROTECTOR);


            switch (orderStatus) {
              /*  case "created": event.reply("Ваш заказ еще не оплачен, оплатите и нажмите на эту кнопку еще раз.").setEphemeral(true).queue(); break;
                case "processing": event.reply("Ваш заказ в процессе оплаты, подождите минуту и нажмите на эту кнопку еще раз.").setEphemeral(true).queue(); break;
                case "declined ": event.reply("Ваш заказ отменен.").setEphemeral(true).queue(); break;
                case "expired": event.reply("Закончилось время ожидания оплаты.").setEphemeral(true).queue(); break;*/


                case "approved": {
                    Server server = getServerByName(serverName);
                    server.exec("sm_addvip \"" + SteamClient.convertSteamID64ToSteamID(Long.parseLong(steamId)) + "\" \"" + getPrivilegeByName(privilegeName).getGroup() + "\" \""+ time +"\"");
                    event.getUser().openPrivateChannel().queue(privateChannel ->
                            privateChannel.sendMessageEmbeds(new EmbedBuilder(event.getMessage().getEmbeds().get(0)).setDescription("`Чек сведетельствующий о покупке привилегии.`").build()).queue(act ->
                                    event.editMessageEmbeds(new EmbedBuilder().setDescription("```Поздравляем вам! Вы получили привелегию. Чек мы отослали вам в ЛС```").build()).setComponents(
                                            ActionRow.of(Button.of(ButtonStyle.LINK, act.getJumpUrl(), "ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤПосмотреть чекㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ"))
                                    ).queue(then ->
                                            Main.getJda().getGuildById(GUILD_ID).getTextChannelById(LOGGING_CHANNEL_ID)
                                                    .sendMessageEmbeds(new EmbedBuilder(event.getMessage().getEmbeds().get(0)).setDescription("`Чек сведетельствующий о покупке привилегии.`\nКупил привилегию: " + event.getUser().getAsMention()).build())
                                                    .queue()
                                    )
                            )
                    );
                    break;
                }


               //default: event.reply("Неизвесная ошибка, обратитесь к администрации").setEphemeral(true).queue();break;
                default: {
                    Server server = getServerByName(serverName);
                    server.exec("sm_addvip \"" + SteamClient.convertSteamID64ToSteamID(Long.parseLong(steamId)) + "\" \"" + getPrivilegeByName(privilegeName).getGroup() + "\" \""+ time +"\"");
                    event.getUser().openPrivateChannel().queue(privateChannel ->
                            privateChannel.sendMessageEmbeds(new EmbedBuilder(event.getMessage().getEmbeds().get(0)).setDescription("`Чек сведетельствующий о покупке привилегии.`").build()).queue(act ->
                                    event.editMessageEmbeds(new EmbedBuilder().setDescription("```Поздравляем вам! Вы получили привелегию. Чек мы отослали вам в ЛС```").build()).setComponents(
                                            ActionRow.of(Button.of(ButtonStyle.LINK, act.getJumpUrl(), "ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤПосмотреть чекㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ"))
                                    ).queue(then ->
                                            Main.getJda().getGuildById(GUILD_ID).getTextChannelById(LOGGING_CHANNEL_ID)
                                                    .sendMessageEmbeds(new EmbedBuilder(event.getMessage().getEmbeds().get(0)).setDescription("`Чек сведетельствующий о покупке привилегии.`\nКупил привилегию: " + event.getUser().getAsMention()).build())
                                                    .queue()
                                    )
                            )
                    );
                    break;
                }
            }



        } catch (Exception e) {
            Logger.ds("fondy", e.getMessage() + " " + e.getStackTrace());
            event.reply("Что-то пошло не так, обратитесь к администрации.").setEphemeral(true).queue();
        }

    }











    private String getMoneyToPay(Privilege privilege, String duration) throws Exception {
        switch (duration) {
            case "1 месяц": return String.valueOf(privilege.getPrice().getMonthPrice());
            case "1 год": return String.valueOf(privilege.getPrice().getYearPrice());
            case "Пожизненно": return String.valueOf(privilege.getPrice().getLifetimePrice());
            default: throw new Exception("duration " + duration + " not found");
        }
    }

    private Privilege getPrivilegeByName(String name) throws Exception {
        for (Privilege privilege : privileges) {
            if (privilege.getName().equals(name)) return privilege;
        }

        throw new Exception("Privilege " + name + " not found");
    }

    private Server getServerByName(String name) throws Exception {
        for (Server server : servers) {
            if (server.getName().equals(name)) return server;
        }

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
