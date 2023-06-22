package org.patriot.commands.message;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.patriot.commands.ChatCommand;

import java.util.Arrays;

public class IssuesModuleCallerCommand implements ChatCommand {

    @Override
    public String getName() {
        return "issues";
    }

    @Override
    public String getDescription() {
        return "Вызывает ембедку предложки";
    }

    @Override
    public Permission[] getGrantedPermissions() {
        return new Permission[] { Permission.ADMINISTRATOR };
    }


    private final MessageEmbed[] embeds = {
            new EmbedBuilder()
                    .setImage("https://cdn.discordapp.com/attachments/963699910501367848/1042821391034097768/5.png")
                    .setAuthor("Предложите свою идею • Мы всегда их читаем!",null,"https://cdn.discordapp.com/emojis/1038788642040189030.gif?size=96&quality=lossless").build(),
            new EmbedBuilder()
                    .addField("Что вы получите за хорошую идею?","<:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376><:994816784203006033:1040960249701605376> <:994816784203006033:1040960249701605376>\n**• 50.000 кредитов\n• Премиум на 7 дней\n**\n__Чтоб предложить идею нажмите на кнопку ниже!__",true)
                    .build()
    };

    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        event.getMessage().delete().queue(action ->
            event.getChannel().sendMessageEmbeds(Arrays.asList(embeds))
                    .addActionRow(Button.of(ButtonStyle.SECONDARY,"create_issue_button","ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤПредложить идею!ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ"))
                    .queue()
        );
    }

}
