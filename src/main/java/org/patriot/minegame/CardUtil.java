package org.patriot.minegame;

import lombok.Getter;

public class CardUtil implements Cards {
    @Getter public static Card[] pokerDeck = {
            c2, c3, c4, c5, c6, c7, c8, c9, c10, jc, qc, kc, ac,

            p2, p3, p4, p5, p6, p7, p8, p9, p10, jp, qp, kp, ap,

            b2, b3, b4, b5, b6, b7, b8, b9, b10, jb, qb, kb, ab,

            k2, k3, k4, k5, k6, k7, k8, k9, k10, jk, qk, kk, ak,
    };

    @Getter public static Card[] durakDeck = {
            c6, c7, c8, c9, c10, jc, qc, kc, ac,

            p6, p7, p8, p9, p10, jp, qp, kp, ap,

            b6, b7, b8, b9, b10, jb, qb, kb, ab,

            k6, k7, k8, k9, k10, jk, qk, kk, ak,
    };
}
