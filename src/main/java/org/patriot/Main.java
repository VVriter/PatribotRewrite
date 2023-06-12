package org.patriot;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.patriot.commands.CommandManager;
import org.patriot.listener.ListenerManager;

import java.util.Arrays;

public class Main implements Constants {

    @Getter private static JDA jda;
    @Getter private static ListenerManager listenerManager;
    @Getter private static CommandManager commandManager;


    public Main() throws InterruptedException {
        Logger.log("startup", "Application starting...");

        //Discord text commands xd, initializing it
        commandManager = new CommandManager();
        //Event listeners of discord processes initializing
        listenerManager = new ListenerManager();

        //Initializing java discord api
        jda = JDABuilder.createDefault(BOT_TOKEN)
                .addEventListeners(listenerManager.getLISTENERS())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build().awaitReady();

        Logger.ds("startup", "Bot started!");
        Logger.log("startup", "Bot started!");
    }

    public static void main(String[] args) {
        try {
            new Main();
        } catch (Exception ex) {
            //Just cool exception handling lol!
            Logger.log("main-exception-handler", ex.getMessage() + ex.getStackTrace());
            jda.getGuildById(GUILD_ID).getTextChannelById(LOGGING_CHANNEL_ID).sendMessage("I catched exched exception. Log: \n\n" +
                    "```" + ex.getMessage() + "```" + "\n" +
                    "```" + Arrays.toString(ex.getStackTrace()) + "```").queue();
        }
    }

}