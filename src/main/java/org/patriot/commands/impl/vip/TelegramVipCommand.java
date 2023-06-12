package org.patriot.commands.impl.vip;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.patriot.commands.PatriotCommand;

import java.util.Arrays;

public class TelegramVipCommand implements PatriotCommand {

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

    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessageEmbeds(Arrays.asList(embeds)).addActionRow(Button.of(ButtonStyle.SECONDARY, "tgvip", "Забрать Привилегию")).queue();
    }
}
