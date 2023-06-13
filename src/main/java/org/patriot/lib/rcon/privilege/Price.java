package org.patriot.lib.rcon.privilege;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Price {
    @Getter int monthPrice;
    @Getter int yearPrice;
    @Getter int lifetimePrice;
}
