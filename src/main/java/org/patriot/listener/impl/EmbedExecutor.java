package org.patriot.listener.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.patriot.Constants;
import org.patriot.Logger;
import org.patriot.listener.PatriotListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EmbedExecutor extends ListenerAdapter implements PatriotListener, Constants {

    @Override
    public String getModuleName() {
        return "EmbedExecutor";
    }

    @Override
    public String getModuleDescription() {
        return "EmbedExecutor";
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        if (event.getAuthor().isBot()) return;
        if (!(event.getMessage().getContentRaw().startsWith("{") && event.getMessage().getContentRaw().endsWith("}"))) return;
        if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) return;

        try {
            JSONObject object = new JSONObject(event.getMessage().getContentRaw());
            JSONArray jsonArray = object.getJSONArray("embeds");
            List<JSONObject> embedsJsons = new ArrayList<>();
            for (int i = 0; i<jsonArray.length(); i++) {
                embedsJsons.add(jsonArray.getJSONObject(i));
            }

            List<EmbedBuilder> builderList = new ArrayList<>();
            EmbedBuilder builder = null;

            for (JSONObject obj : embedsJsons) {
                builder = new EmbedBuilder();

                if (obj.has("title"))
                    builder.setTitle(obj.get("title").toString());

                if (obj.has("description"))
                    builder.setDescription(obj.get("description").toString());

                if (obj.has("color") && obj.get("color").toString() != "null") {
                    builder.setColor(Integer.valueOf(obj.get("color").toString()));
                }

                if (obj.has("fields")) {
                    JSONArray fieldsJsonArray = obj.getJSONArray("fields");
                    for (int i = 0; i<fieldsJsonArray.length(); i++) {
                        JSONObject fieldObj = fieldsJsonArray.getJSONObject(i);
                        if (fieldObj.has("name") && fieldObj.has("value") && fieldObj.has("inline")) {
                            boolean isInline = fieldObj.get("inline").toString() == "true";
                            builder.addField(fieldObj.get("name").toString(), fieldObj.get("value").toString(), isInline);
                        } else if (fieldObj.has("name") && fieldObj.has("value")) {
                            builder.addField(fieldObj.get("name").toString(), fieldObj.get("value").toString(), false);
                        }
                    }
                }

                if (obj.has("author")) {
                    JSONObject fieldObj = obj.getJSONObject("author");
                    if (fieldObj.has("name"))
                        builder.setAuthor(fieldObj.get("name").toString());
                    else if (fieldObj.has("name") && fieldObj.has("url"))
                        builder.setAuthor(fieldObj.get("name").toString(), fieldObj.get("url").toString());
                    else
                        builder.setAuthor(fieldObj.get("name").toString(), fieldObj.get("url").toString(), fieldObj.get("icon_url").toString());
                }

                if (obj.has("footer")) {
                    JSONObject fieldObj = obj.getJSONObject("footer");
                    if (!fieldObj.has("icon_url"))
                        builder.setFooter(fieldObj.get("text").toString());
                    else if (fieldObj.has("icon_url"))
                        builder.setFooter(fieldObj.get("text").toString(), fieldObj.get("icon_url").toString());
                }


                if (obj.has("image")) {
                    JSONObject imgeObj = obj.getJSONObject("image");
                    builder.setImage(imgeObj.get("url").toString());
                }

                if (obj.has("thumbnail")) {
                    JSONObject imgeObj = obj.getJSONObject("thumbnail");
                    builder.setThumbnail(imgeObj.get("url").toString());
                }

                if (obj.has("timestamp")) {
                    builder.setTimestamp(new Date().toInstant());
                }

                builderList.add(builder);
                builder = null;
            }

            String content = null;
            if (object.has("content")) {
                content = object.get("content").toString();
            }

            if (content != "null") {
                event.getMessage().getChannel().sendMessage(content).queue();
            } if (content == "null" && !builderList.isEmpty()) {
                for (EmbedBuilder buldr : builderList) {
                    if (!buldr.isEmpty())
                        event.getMessage().getChannel().sendMessageEmbeds(buldr.build()).queue();
                }
            } if (content != "null" && !builderList.isEmpty()) {
                for (EmbedBuilder buldr : builderList) {
                    if (!buldr.isEmpty())
                        event.getMessage().getChannel().sendMessageEmbeds(buldr.build()).queue();
                }
            }

            event.getMessage().delete().queue();
        } catch (JSONException e) {
            event.getMessage().reply("Это не JSON объект, переверь все на ошибки, спасибо!").queue();
        } catch (Exception e) {
            event.getMessage().reply("Что-то пошло не так - чекай канал логгинга").queue();
            Logger.ds("EMBED-PROCESSOR", e.getMessage() + " " + Arrays.toString(e.getStackTrace()));
        }
    }

}
