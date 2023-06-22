package org.patriot.commands.message;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.patriot.commands.ChatCommand;

import java.util.Arrays;

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


    private final MessageEmbed[] embeds = {
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
    };


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
        event.getMessage().delete().queue(deleteAction -> event.getChannel().sendMessageEmbeds(Arrays.asList(embeds)).addActionRow(select.build()).queue());
    }

}
