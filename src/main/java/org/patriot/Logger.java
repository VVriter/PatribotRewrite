package org.patriot;

public class Logger implements Constants{

    public static void log(String tag, Object info) {
        System.out.println("[" + tag.toUpperCase() + "]" + " " + info.toString());
    }

    public static void log(Object info) {
        System.out.println("[" + "PATRIBOT".toUpperCase() + "]" + " " + info.toString());
    }

    public static void ds(String tag, Object info) {
        Main.getJda().getGuildById(GUILD_ID).getTextChannelById(LOGGING_CHANNEL_ID).sendMessage("[" + tag.toUpperCase() + "]" + " " + info.toString()).queue();
    }

}
