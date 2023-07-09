package org.patriot.commands.message;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.patriot.commands.ChatCommand;
import org.patriot.lib.discohook.DiscohookUtil;

import java.util.Arrays;
import java.util.List;

public class SupportModuleCallerCommand implements ChatCommand {

    @Override
    public String getName() {
        return "support";
    }

    @Override
    public String getDescription() {
        return "Calls support embed";
    }

    @Override
    public Permission[] getGrantedPermissions() {
        return new Permission[] { Permission.ADMINISTRATOR };
    }

    List<MessageEmbed> builderList = DiscohookUtil.getEmbedsFromUrl("https://discohook.org/?data=eyJtZXNzYWdlcyI6W3siZGF0YSI6eyJjb250ZW50IjpudWxsLCJlbWJlZHMiOlt7ImNvbG9yIjpudWxsLCJpbWFnZSI6eyJ1cmwiOiJodHRwczovL21lZGlhLmRpc2NvcmRhcHAubmV0L2F0dGFjaG1lbnRzLzk2MzY5OTkxMDUwMTM2Nzg0OC8xMTI3MjIwODYwMDcyMjMwOTU3LzFfMy5naWYifX0seyJkZXNjcmlwdGlvbiI6Ijw6cXVlc3Rpb25fbWluaToxMDk0OTg1ODM1NDk1MDUxMzg0PiAgKirQktC40L3QuNC60LvQuCDQv9GA0L7QsdC70LXQvNC4INC3INGB0LXRgNCy0LXRgNC-0Lwg0LDQsdC-INCy0LDQvCDQv9C-0YLRgNGW0LHQvdCwINC00L7Qv9C-0LzQvtCz0LA_KipcblxuPDpiYl93aGl0ZV9kb3QyMjoxMDQwOTUxODU3NTI4NDQ2OTc2PiDQptC10Lkg0LrQsNC90LDQuyDQstC40YHRgtGD0L_QsNGUINGD0L3RltCy0LXRgNGB0LDQu9GM0L3QuNC8IVxuYGBg0KMg0YbRjNC-0LzRgyDQutCw0L3QsNC70ZYg0LLQuCDQvNC-0LbQtdGC0LUg0L_QvtGB0LrQsNGA0LbQuNGC0LjRgdGPINC90LAg0LDQtNC80ZbQvdCwLCDQutGD0L_QuNGC0Lgg0YDQvtC30LHQsNC9LCDQutGD0L_QuNGC0Lgg0YDQvtC30LzRg9GCLCDQt9CyJ9GP0LfQsNGC0LjRgdGPINC3INC60LXRgNGW0LLQvdC40YbRgtCy0L7QvCDQtyDQv9GA0LjQstC-0LTRgyDRgNC10LrQu9Cw0LzQuCDRliDRgi7QtC5gYGBcbjw6YmJfd2hpdGVfZG90MjI6MTA0MDk1MTg1NzUyODQ0Njk3Nj4g0J_RgNCw0LLQuNC70LAg0YnQvtC00L4g0L_QvtC00LDQvdC90Y8g0YLRltC60LXRgtCwOlxuYGBg0KHQutCw0YDQs9C4INC3INC-0LHRgNCw0LfQsNC80Lgg0LLRltC0INCS0LDRgSDQvdC1INGA0L7Qt9Cz0LvRj9C00LDRgtC40LzRg9GC0YzRgdGPLiDQl9Cy0LXRgNC90ZbRgtGMINGD0LLQsNCz0YMsINGJ0L4g0LfQsNGP0LLQutC4INC30LDQu9C40YjQtdC90ZYg0LfQsCDRiNCw0LHQu9C-0L3QvdC-0Y4g0YTQvtGA0LzQvtGOXG7RgNC-0LfQs9C70Y_QtNCw0Y7RgtGM0YHRjyDQvdCw0LHQsNCz0LDRgtC-INGI0LLQuNC00YjQtS4g0JfQsNGP0LLQutC4INCx0LXQtyDQstGW0LTQv9C-0LLRltC00ZYg0LfQsNC60YDQuNCy0LDRjtGC0YzRgdGPLiDQn9C-0YDQvtC20L3RliDRgtCwINCx0LXQt9Cz0LvRg9C30LTRliDRgdC60LDRgNCz0Lgg0LHRg9C00YPRgtGMINC30LDQutGA0LjQstCw0YLQuNGB0Y8u0KHRgtCy0L7RgNC40LLRiNC4INC30LDRj9Cy0LrRgywg0LLQuCDQv9C-0LPQvtC00LbRg9GU0YLQtdGB0Y8g0LfRliDQstGB0YLQsNC90L7QstC70LXQvdC40LzQuCDQv9GA0LDQstC40LvQsNC80Lgg0LLQuNGJ0LUuYGBgXG48OmJiX3doaXRlX2RvdDIyOjEwNDA5NTE4NTc1Mjg0NDY5NzY-0KHRgtCy0L7RgNGO0LnRgtC1INGC0ZbQutC10YIg0YMg0YDQsNC30ZYsINGP0LrRidC-INGDINCS0LDRgSDRlCDRgtC-0YfQvdGWINC00L7QutCw0LfQuFxu0LLQuNC90YPQstCw0YLQvtGB0YLRliDQsNC00LzRltC90LAg0LDQsdC-INCz0YDQsNCy0YbRjyDQsiDRgtGW0Lkg0YfQuCDRltC90YjRltC5INC00ZbRly4iLCJjb2xvciI6bnVsbCwiaW1hZ2UiOnsidXJsIjoiaHR0cHM6Ly9jZG4uZGlzY29yZGFwcC5jb20vYXR0YWNobWVudHMvOTYzNjk5OTEwNTAxMzY3ODQ4LzExMDQ0OTg0MDg3MTIwNjEwMTkvMTExMS5wbmcifX1dLCJhdHRhY2htZW50cyI6W119fV19");


 /*   private final MessageEmbed[] embeds = {
            new EmbedBuilder()
                .setColor(3092790)
                .setImage("https://cdn.discordapp.com/attachments/976095234180120576/1079078543063007252/d0a35e145745f4e2.png").build(),
            new EmbedBuilder()
                .setDescription("<a:10m9:990014551770660876> Возникли проблемы" +
                        " с сервером либо вам нужна помощь?\n\n<:bb_white_dot22:1040951857528446976>Данный" +
                        " канал выступает универсальным! \nВ этом канале вы можете пожаловаться на админа," +
                        " купить разбан, \nкупить размут, связаться с руководством по поводу рекламы и" +
                        " т.д.\n\n<:bb_white_dot22:1040951857528446976> Правила по подачи тикета:\n- Жалобы" +
                        " с оскорблениями от Вас не будут рассматриваться.\n- Обратите внимание, что заявки," +
                        " оставленные по шаблонной форме\nрассматриваются намного быстрее.\n- Неотвеченные" +
                        " заявки закрываются.\n- Пустые и бессмысленные жалобы будут закрываться.\n- Создав заявку," +
                        " вы соглашаетесь с установленным правилами выше.\n\n<:bb_white_dot22:1040951857528446976>" +
                        "Создавайте тикет в случае, если у Вас есть точные доказательства \nвиновности админа" +
                        " или игрока в том или ином действии.")
                .setColor(3092790).build()
    }; */


    private final StringSelectMenu.Builder select = StringSelectMenu.create("reports_module_select_menu");

    public SupportModuleCallerCommand() {
        select.addOption("Жалоба на админа", "1", Emoji.fromFormatted("<:report:1078390433945747559>"));
        select.addOption("Связь с администрацией", "2", Emoji.fromFormatted("<:svyazatsa_s_rukovodstvom:1078390423699083414>"));
        select.addOption("Пополнение баланса", "3", Emoji.fromFormatted("<:donate:1078390428665131008>"));
        select.addOption("Задать вопрос", "4", Emoji.fromFormatted("<:question:1078390432469368883>"));
        select.addOption("Прием багов", "5", Emoji.fromFormatted("<:bags:1078390426081447986>"));
        select.addOption("Снятие мута", "6", Emoji.fromFormatted("<:mute:1078390430741299324>"));
        select.addOption("Снятие бана", "7", Emoji.fromFormatted("<:ban:1078391936685195474>"));
    }

    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        event.getMessage().delete().queue(deleteAction -> event.getChannel().sendMessageEmbeds(builderList).addActionRow(select.build()).queue());
    }

}
