package org.patriot.commands.message.vip;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.patriot.commands.ChatCommand;
import org.patriot.lib.discohook.DiscohookUtil;

import java.util.Arrays;
import java.util.List;

public class TelegramVipCommand implements ChatCommand {

    @Override
    public String getName() {
        return "tgvip";
    }

    @Override
    public String getDescription() {
        return "Вызывает ембедку випки телеграма";
    }

    @Override
    public Permission[] getGrantedPermissions() {
        return new Permission[] {Permission.ADMINISTRATOR};
    }

    /*
    private final MessageEmbed[] embeds = {
            new EmbedBuilder()
                    .setImage("https://cdn.discordapp.com/attachments/963699910501367848/1037695726702034986/Typography_43.gif")
                    .build(),
            new EmbedBuilder().setTitle("Получение бесплатного вип на 7 дней • [Телеграмм]")
                .setDescription("__Как это работает?__ " +
                " **Ты получишь привилегию, если будешь" +
                    "<:994816784203006033:1040960249701605376>" +
                    "<:994816784203006033:1040960249701605376>" +
                    "\nследовать инструкции приложенной ниже!**" +
                    "\n\n• Ссылка на канал: https://t.me/patriotcsgo" +
                    "\n• При заполнении заявки будьте внимательными," +
                    " ведь \nесли ты допустишь ошибку, заявка будет" +
                    " проигнорирована.\n• При заполнении заявки вводите" +
                    " данные корректно, так как \nот этого зависит" +
                    " будущее твоей привилегии. \n\n> __Вам нужно " +
                    "нажать на кнопку ниже и заполнить форму__").build()
    };
     */

    List<MessageEmbed> embeds = DiscohookUtil.getEmbedsFromUrl("https://discohook.org/?data=eyJtZXNzYWdlcyI6W3siZGF0YSI6eyJjb250ZW50IjpudWxsLCJlbWJlZHMiOlt7ImNvbG9yIjpudWxsLCJpbWFnZSI6eyJ1cmwiOiJodHRwczovL21lZGlhLmRpc2NvcmRhcHAubmV0L2F0dGFjaG1lbnRzLzk2MzY5OTkxMDUwMTM2Nzg0OC8xMTI3MjIwODU5MjkyMDk0NTQ0LzFfMS5naWYifX0seyJkZXNjcmlwdGlvbiI6Il9f0K_QuiDRhtC1INC_0YDQsNGG0Y7RlD9fXyAqKtCi0Lgg0L7RgtGA0LjQvNCw0ZTRiCDQv9GA0LjQstGW0LvQtdC5LCDRj9C60YnQviDQsdGD0LTQtdGIXG7RgdC70ZbQtNGD0LLQsNGC0Lgg0ZbQvdGB0YLRgNGD0LrRhtGW0ZcsINGJ0L4g0LTQvtC00LDQvdCwINC90LjQttGH0LUqKiEiLCJjb2xvciI6bnVsbCwiZmllbGRzIjpbeyJuYW1lIjoi4oCiICDQndC-0YLQsNGG0ZbRjzoiLCJ2YWx1ZSI6ItCf0YDQuCDQt9Cw0L_QvtCy0L3QtdC90L3RliDQt9Cw0Y_QstC60Lgg0LHRg9C00YzRgtC1INGD0LLQsNC20L3QuNC80LgsINCw0LTQttC1XG7Rj9C60YnQviDRgtC4INC_0YDQuNC_0YPRgdGC0LjRiNGB0Y8g0L_QvtC80LjQu9C60LgsINC30LDRj9Cy0LrQsCDQsdGD0LTQtSDQvdC1INC-0LHRgNC-0LHQu9C10L3QsC4ifSx7Im5hbWUiOiLigKIgINCG0L3RgdGC0YDRg9C60YbRltGPOiIsInZhbHVlIjoiMS4g0KHQv9C-0YfQsNGC0LrRgyDQstCw0Lwg0L_QvtGC0YDRltCx0L3QviDQt9Cw0LnRgtC4INCyINGC0LXQu9C10LPRgNCw0Lw6XG4gaHR0cHM6Ly90Lm1lL3BhdHJpb3Rjc2dvIDtcbjIuINCd0LDRgtC40YHQvdGD0YLQuCDQvdCwINC60L3QvtC_0LrRgyBcItCX0LDQsdGA0LDRgtC4INC_0YDQuNCy0ZbQu9C10LlcIi4g0IYg0LfQsNC_0L7QstC90LjRgtC4INGE0L7RgNC80YMuXG4zLiDQntGH0ZbQutGD0ZTRgtC1INCy0ZbQtNC_0L7QstGW0LTRliDQvNC10L3QtdC00LbQtdGA0LAsINCy0LDQvCDQvNC10L3QtdC00LbQtdGAINCy0ZbQtNC_0L7QstGW0YHRgtGMINC_0YDQvtGC0Y_Qs9C-0LwgNSDRhdCy0LjQu9C40L07XG40LiDQktC4INC-0YLRgNC40LzQsNC70Lgg0LHQtdC30LrQvtGI0YLQvtCy0L3QuNC5INC_0YDQuNCy0ZbQu9C10Lkg0L3QsCDRhtGW0LvQuNC5INGC0LjQttC00LXQvdGMLlxuXG4-IF9f0JTQu9GPINC_0L7Rh9Cw0YLQutGDINCy0LDQvCDRgtGA0LXQsdCwINC90LDRgtC40YHQvdGD0YLQuCDQvdCwINC60L3QvtC_0LrRgyDQvdC40LbRh9C1INGWINC_0YDQvtC50YLQuCDQt9CwINC_0YPQvdC60YLQsNC80Lgg0LIg0ZbQvdGB0YLRgNGD0LrRhtGW0Zcg0LTQu9GPINC-0YLRgNC40LzQsNC90L3RjyDQv9GA0LjQstGW0LvQtdGOLl9fIn1dLCJhdXRob3IiOnsibmFtZSI6ItCe0YLRgNC40LzQsNC90L3RjyDQsdC10LfQutC-0YjRgtC-0LLQvdC-0LPQviDQstGW0L8g0L3QsCA3INC00L3RltCyIOKAoiBb0KLQtdC70LXQs9GA0LDQvF0ifSwiaW1hZ2UiOnsidXJsIjoiaHR0cHM6Ly9jZG4uZGlzY29yZGFwcC5jb20vYXR0YWNobWVudHMvOTYzNjk5OTEwNTAxMzY3ODQ4LzExMDQ0OTg0MDg3MTIwNjEwMTkvMTExMS5wbmcifX1dLCJhdHRhY2htZW50cyI6W119fV19");

    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessageEmbeds(embeds).addActionRow(Button.of(ButtonStyle.SECONDARY, "tgvip", "Забрать Привилегию")).queue();
    }
}
