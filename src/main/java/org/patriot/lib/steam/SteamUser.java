package org.patriot.lib.steam;

import lombok.Builder;
import lombok.Getter;

@Builder
public class SteamUser {
    @Getter private String name;
    @Getter private String avatarUrl;
    @Getter private String steamId;
}
