package org.patriot.commands.message.autoroles;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.patriot.Constants;
import org.patriot.commands.ChatCommand;

import java.util.Arrays;

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
            event.getChannel().sendMessageEmbeds(Arrays.asList(embeds)).addActionRow(selectMenu.build()).queue()
        );
    }

}
