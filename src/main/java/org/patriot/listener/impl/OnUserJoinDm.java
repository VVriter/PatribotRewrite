package org.patriot.listener.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.patriot.listener.PatriotListener;

public class OnUserJoinDm extends ListenerAdapter implements PatriotListener {

    @Override
    public String getModuleName() {
        return "OnUserJoinDmListener";
    }

    @Override
    public String getModuleDescription() {
        return "I just dms to user when he joins to server xd";
    }



    private final EmbedBuilder builder = new EmbedBuilder()
            .setTitle("Раді бачити тебе на нашому проекті!")
            .setDescription("• Постійний онлайн.\n• Швидке завантаження + Висока FPS\n• 128 Тікрейт і мінімальний пінг.\n• Приємна і дружня атмосфера.\n• Унікальні та оригінальні плагіни.\n• Справедлива і адекватна команда проекту.\n\nМи — Унікальність. Приєднуйся!\n\n💎 Купівля привілеїв: [patriot-csgo.com](https://patriot-csgo.com/)\n\n📊 Статистика гравців: [patriot-csgo.com/toppoints](https://patriot-csgo.com/toppoints/)\n📘 Правила проекту: [patriot-csgo.com/rules](https://patriot-csgo.com/rules/)\n\nОновлення щотижня! \nМи чекаємо тебе у нас на сервері!")
            .setColor(2996732)
            .setAuthor("Патріот - найкращий проект CSGO серверів","https://patriot-csgo.com/")
            .setImage("https://cdn.discordapp.com/attachments/963699910501367848/1078376302026960996/-2.png");


    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        super.onGuildMemberJoin(event);
        try {
            event.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessageEmbeds(builder.build()).queue());
        } catch (Exception ignored) { /*I'm ignoring this exception cuz of some users can close own dms*/ }
    }

}
