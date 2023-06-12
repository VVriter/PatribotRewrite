package org.patriot.listener.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.patriot.listener.PatriotListener;

public class OnUserJoinDmListener extends ListenerAdapter implements PatriotListener {

    @Override
    public String getModuleName() {
        return "OnUserJoinDmListener";
    }

    @Override
    public String getModuleDescription() {
        return "I just dms to user when he joins to server xd";
    }



    private final EmbedBuilder builder = new EmbedBuilder()
            .setTitle("Рады видеть тебя на нашем проекте!")
            .setDescription("• Постоянный онлайн.\n• Быстрая загрузка + Высокий FPS\n• 128 Тикрейт и минимальный пинг.\n• Приятная и дружеская атмосфера.\n• Уникальные и оригинальные плагины.\n• Справедливая и адекватная команда проекта.\n\nМы — Уникальность. Присоединяйся!\n\n💎 Покупка привилегий: [patriot-csgo.com](https://patriot-csgo.com/)\n\n📊 Статистика игроков: [patriot-csgo.com/toppoints](https://patriot-csgo.com/toppoints/)\n📘 Правила проекта: [patriot-csgo.com/rules](https://patriot-csgo.com/rules/)\n\nОбновления каждую неделю! \nМы ждем тебя у нас на сервере!")
            .setColor(2996732)
            .setAuthor("Патриот - лучший проект CSGO серверов","https://patriot-csgo.com/")
            .setImage("https://cdn.discordapp.com/attachments/963699910501367848/1078376302026960996/-2.png");



    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        super.onGuildMemberJoin(event);
        try {
            event.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessageEmbeds(builder.build()).queue());
        } catch (Exception ignored) { /*I'm ignoring this exception cuz of some users can close own dms*/ }
    }

}
