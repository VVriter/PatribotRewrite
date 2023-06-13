package org.patriot.lib.rcon.privilege;

import lombok.Builder;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

@Builder
public class Privilege {
    @Getter String name;
    @Getter String group;
    @Getter String description;
    @Getter String imageUrl;
    @Getter String roleId;
    @Getter Price price;

    public JSONObject toJson() {
        return new JSONObject().put("name", name)
                .put("group", group)
                .put("roleId", roleId)
                .put("price", new JSONArray().put(price.getMonthPrice()).put(price.getYearPrice()).put(price.getLifetimePrice()));
    }
}
