package org.patriot.lib.steam;

import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.patriot.Constants;

public class SteamClient implements Constants {

    @Getter SteamUser steamUser;

    public SteamClient(String id) throws Exception {
        id = id.replace(" ", "");
        id = id.replace("https://steamcommunity.com/profiles/", "");
        id = id.replace("/", "");


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=" + STEAM_API_KEY  + "&steamids=" + id)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        JSONObject jsonResponce = new JSONObject(responseBody);
        JSONObject info = jsonResponce.getJSONObject("response").getJSONArray("players").getJSONObject(0);

        steamUser = SteamUser.builder()
                .name(info.getString("personaname"))
                .avatarUrl(info.getString("avatarfull"))
                .steamId(id)
                .build();
    }




    public static String convertSteamID64ToSteamID(long steamID64) {
        long z = (steamID64 - 76561197960265728L) / 2;
        long y = steamID64 % 2;
        return "STEAM_0:" + y + ":" + z;
    }
}
