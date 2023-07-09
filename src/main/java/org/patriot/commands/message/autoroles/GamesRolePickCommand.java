package org.patriot.commands.message.autoroles;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.patriot.Constants;
import org.patriot.commands.ChatCommand;
import org.patriot.lib.discohook.DiscohookUtil;

import java.util.Arrays;
import java.util.List;

public class GamesRolePickCommand implements ChatCommand, Constants {

    @Override
    public String getName() {
        return "gamerolepicker";
    }

    @Override
    public String getDescription() {
        return "Вызывает ембедку где можно выбрать нроль с какой игры ты пришел";
    }

    @Override
    public Permission[] getGrantedPermissions() {
        return new Permission[] { Permission.ADMINISTRATOR };
    }

    /*
    private final MessageEmbed[] embeds = {
            new EmbedBuilder()
                    .setImage("https://cdn.discordapp.com/attachments/976095234180120576/1041055335957397676/123123.gif")
                    .setColor(3553599).build(),
            new EmbedBuilder()
                    .setTitle("Под этим сообщением вы можете выбрать себе роль,\n нажав на соотвествующую роли кнопку в меню выбора")
                    .addField("<:994816784203006033:1040960249701605376>","" +
                                    "<:9gameicon1:1041414089416654930> - Valorant\n" +
                                    "<:9gameicon2:1041414091643834419> - CSGO\n" +
                                    "<:9gameicon3:1041414093837451324> - Brawl Stars\n" +
                                    "<:9gameicon4:1041414096135921664> - DOTA 2"
                            ,true)
                    .addField("<:994816784203006033:1040960249701605376>","" +
                            "<:9gameicon5:1041414098019172374> - Genshin Impact\n" +
                            "<:9gameicon6:1041414100414115860> - Minecraft\n" +
                            "<:9gameicon7:1041414087432753162> - League Of Legends\n" +
                            "<:9gameicon8:1041416572650143784> - Brawlhalla",true)
                    .setColor(3553599).build()
    };
     */

    List<MessageEmbed> embeds = DiscohookUtil.getEmbedsFromUrl("https://discohook.org/?data=eyJtZXNzYWdlcyI6W3siZGF0YSI6eyJjb250ZW50IjpudWxsLCJlbWJlZHMiOlt7ImNvbG9yIjpudWxsLCJpbWFnZSI6eyJ1cmwiOiJodHRwczovL2Nkbi5kaXNjb3JkYXBwLmNvbS9hdHRhY2htZW50cy85NjM2OTk5MTA1MDEzNjc4NDgvMTEyNzI1ODY4MzQwODEzMDA4OS82LmdpZiJ9fSx7ImRlc2NyaXB0aW9uIjoi0J_RltC0INGG0LjQvCDQv9C-0LLRltC00L7QvNC70LXQvdC90Y_QvCDQstC4INC80L7QttC10YLQtSDQstC40LHRgNCw0YLQuCDRgdC-0LHRliDRgNC-0LvRjCwg0L3QsNGC0LjRgdC90YPQstGI0Lgg0L3QsCDQstGW0LTQv9C-0LLRltC00L3RgyDRgNC-0LvRliDQutC90L7Qv9C60YMg0LIg0LzQtdC90Y4g0LLQuNCx0L7RgNGDLiIsImNvbG9yIjpudWxsLCJmaWVsZHMiOlt7Im5hbWUiOiI8Ojk5NDgxNjc4NDIwMzAwNjAzMzoxMDQwOTYwMjQ5NzAxNjA1Mzc2PiIsInZhbHVlIjoiPDo5Z2FtZWljb24xOjEwNDE0MTQwODk0MTY2NTQ5MzA-IC0gVmFsb3JhbnRcbjw6OWdhbWVpY29uMjoxMDQxNDE0MDkxNjQzODM0NDE5PiAtIENTR09cbjw6OWdhbWVpY29uMzoxMDQxNDE0MDkzODM3NDUxMzI0PiAtIEJyYXdsIFN0YXJzXG48OjlnYW1laWNvbjQ6MTA0MTQxNDA5NjEzNTkyMTY2ND4gLSBET1RBIDIiLCJpbmxpbmUiOnRydWV9LHsibmFtZSI6Ijw6OTk0ODE2Nzg0MjAzMDA2MDMzOjEwNDA5NjAyNDk3MDE2MDUzNzY-IiwidmFsdWUiOiI8OjlnYW1laWNvbjU6MTA0MTQxNDA5ODAxOTE3MjM3ND4gLSBHZW5zaGluIEltcGFjdFxuPDo5Z2FtZWljb242OjEwNDE0MTQxMDA0MTQxMTU4NjA-IC0gTWluZWNyYWZ0XG48OjlnYW1laWNvbjc6MTA0MTQxNDA4NzQzMjc1MzE2Mj4gLSBMZWFndWUgT2YgTGVnZW5kc1xuPDo5Z2FtZWljb244OjEwNDE0MTY1NzI2NTAxNDM3ODQ-IC0gQnJhd2xoYWxsYSIsImlubGluZSI6dHJ1ZX1dLCJhdXRob3IiOnsibmFtZSI6ItCS0LjQsdGW0YAg0ZbQs9GA0L7QstC-0Zcg0YDQvtC70ZY6In0sImltYWdlIjp7InVybCI6Imh0dHBzOi8vY2RuLmRpc2NvcmRhcHAuY29tL2F0dGFjaG1lbnRzLzk2MzY5OTkxMDUwMTM2Nzg0OC8xMTA0NDk4NDA4NzEyMDYxMDE5LzExMTEucG5nIn19XSwiYXR0YWNobWVudHMiOltdfX1dfQ");

    private final StringSelectMenu.Builder selectMenu = StringSelectMenu.create("game_role_picker");

    public GamesRolePickCommand() {
        selectMenu.addOption("Valorant", VALORANT_ROLE_ID, Emoji.fromFormatted("<:9gameicon1:1041414089416654930>"));
        selectMenu.addOption("CS:GO", CSGO_ROLE_ID, Emoji.fromFormatted("<:9gameicon2:1041414091643834419>"));
        selectMenu.addOption("BrawlStars", BRAWL_STARS_ROLE_ID, Emoji.fromFormatted("<:9gameicon3:1041414093837451324>"));
        selectMenu.addOption("Dota2", DOTA_ROLE_ID, Emoji.fromFormatted("<:9gameicon4:1041414096135921664>"));
        selectMenu.addOption("GenshinImpact", GENSHIN_ROLE_ID,Emoji.fromFormatted("<:9gameicon5:1041414098019172374>"));
        selectMenu.addOption("LeagueOfLegends", LEGUE_OF_LEGUENDS_ROLE_ID, Emoji.fromFormatted("<:9gameicon7:1041414087432753162>"));
        selectMenu.addOption("Brawhala", BRAWHALA_ROLE_ID, Emoji.fromFormatted("<:9gameicon8:1041416572650143784>"));
    }

    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        event.getMessage().delete().queue(action ->
            event.getChannel().sendMessageEmbeds(embeds).addActionRow(selectMenu.build()).queue()
        );
    }

}
