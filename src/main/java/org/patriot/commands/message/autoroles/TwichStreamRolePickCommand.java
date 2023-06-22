package org.patriot.commands.message.autoroles;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.patriot.commands.ChatCommand;

import java.util.Arrays;

public class TwichStreamRolePickCommand implements ChatCommand {

    @Override
    public String getName() {
        return "twich";
    }

    @Override
    public String getDescription() {
        return "Вызывает ембде и кнопочку чтобы получить роль стримера";
    }

    @Override
    public Permission[] getGrantedPermissions() {
        return new Permission[] { Permission.ADMINISTRATOR };
    }



    private final MessageEmbed[] embeds = {
            new EmbedBuilder()
                    .setImage("https://cdn.discordapp.com/attachments/963699910501367848/1108844991813517352/12.png").build(),
            new EmbedBuilder()
                    .setDescription("<:994816784203006033:1040960249701605376>__Уникальная роль:__ <:twitch_zritel:1108847695667404850> <@&1108846579730894949>\n<:994816784203006033:1040960249701605376>__Возможности:__ Просмотр трансляций, следить за обновлениями канала на [twitch](https://www.twitch.tv/patr1ot_csgo). Следить за началом трансляций. Зарабатывать баллы и тратить их на привилегии.\nУчаствовать в стримах. Вступление в ряды твич банды.")
                    .setImage("https://cdn.discordapp.com/attachments/963699910501367848/1104498408712061019/1111.png")
                    .setAuthor("Получи привилегию \"Админ\" при помощи просмотра стрима").build()
    };


    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {

        event.getMessage().delete().queue(action ->
                event.getChannel()
                    .sendMessageEmbeds(Arrays.asList(embeds))
                    .addActionRow(Button.of(
                            ButtonStyle.SECONDARY,
                            "stream_watch_button",
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀Получить роль⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀")
                    )
                    .queue()
        );

    }
}
