package org.patriot.lib.discohook;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.*;

public final class DiscohookUtil {
    public static List<MessageEmbed> getEmbedsFromUrl(String url) {
        String encodedData = url.split("data=")[1];
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedData);
        String decodedData = new String(decodedBytes, StandardCharsets.UTF_8);
        JSONObject d = new JSONObject(decodedData);
        JSONArray jsonArray = d.getJSONArray("messages").getJSONObject(0).getJSONObject("data").getJSONArray("embeds");

        System.out.println(jsonArray);

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

        List<MessageEmbed> toReturn = new ArrayList<>();

        for (EmbedBuilder builder1 : builderList)
            toReturn.add(builder1.build());

        return toReturn;
    }
}
