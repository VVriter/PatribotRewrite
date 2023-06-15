package org.patriot.minegame;

import net.dv8tion.jda.api.entities.emoji.Emoji;

public interface Cards {
    Card c2 = Card.builder().name("Черва 2").value(2).suit(Suit.CHERVA).emoji(Emoji.fromFormatted("<:2c:1118270494584602664>")).build();
    Card c3 = Card.builder().name("Черва 3").value(3).suit(Suit.CHERVA).emoji(Emoji.fromFormatted("<:3c:1118271695413854239>")).build();
    Card c4 = Card.builder().name("Черва 4").value(3).suit(Suit.CHERVA).emoji(Emoji.fromFormatted("<:4c:1118270516696989849>")).build();
    Card c5 = Card.builder().name("Черва 5").value(3).suit(Suit.CHERVA).emoji(Emoji.fromFormatted("<:5c:1118270525689577573>")).build();
    Card c6 = Card.builder().name("Черва 6").value(3).suit(Suit.CHERVA).emoji(Emoji.fromFormatted("<:6c:1118270545503469568>")).build();
    Card c7 = Card.builder().name("Черва 7").value(3).suit(Suit.CHERVA).emoji(Emoji.fromFormatted("<:7c:1118270564260393160>")).build();
    Card c8 = Card.builder().name("Черва 8").value(8).suit(Suit.CHERVA).emoji(Emoji.fromFormatted("<:8c:1118270582329450526>")).build();
    Card c9 = Card.builder().name("Черва 9").value(3).suit(Suit.CHERVA).emoji(Emoji.fromFormatted("<:9c:1118270598678855720>")).build();
    Card c10 = Card.builder().name("Черва 3").value(3).suit(Suit.CHERVA).emoji(Emoji.fromFormatted("<:10:1118272334407671859>")).build();
    Card jc = Card.builder().name("Валет черва").value(2).suit(Suit.CHERVA).emoji(Emoji.fromFormatted("<:jc:1118270446266228919>")).build();
    Card qc = Card.builder().name("Королева черва").value(3).suit(Suit.CHERVA).emoji(Emoji.fromFormatted("<:qc:1118270480755998932>")).build();
    Card kc = Card.builder().name("Король черва").value(4).suit(Suit.CHERVA).emoji(Emoji.fromFormatted("<:kc:1118270461407678574>")).build();
    Card ac = Card.builder().name("Туз черва").value(11).suit(Suit.CHERVA).emoji(Emoji.fromFormatted("<:ac:1118270626294153256>")).build();

    Card p2 = Card.builder().name("Пика 2").value(2).suit(Suit.PIKA).emoji(Emoji.fromFormatted("<:2p:1118270499781353482>")).build();
    Card p3 = Card.builder().name("Пика 3").value(3).suit(Suit.PIKA).emoji(Emoji.fromFormatted("<:3p:1118445064155316234>")).build();
    Card p4 = Card.builder().name("Пика 4").value(3).suit(Suit.PIKA).emoji(Emoji.fromFormatted("<:4p:1118445781121245245>")).build();
    Card p5 = Card.builder().name("Пика 5").value(3).suit(Suit.PIKA).emoji(Emoji.fromFormatted("<:5p:1118270533902016552>")).build();
    Card p6 = Card.builder().name("Пика 6").value(3).suit(Suit.PIKA).emoji(Emoji.fromFormatted("<:6p:1118270557008437368>")).build();
    Card p7 = Card.builder().name("Пика 7").value(3).suit(Suit.PIKA).emoji(Emoji.fromFormatted("<:7p:1118270570140795030>")).build();
    Card p8 = Card.builder().name("Пика 8").value(8).suit(Suit.PIKA).emoji(Emoji.fromFormatted("<:8p:1118270590206353532>")).build();
    Card p9 = Card.builder().name("Пика 9").value(3).suit(Suit.PIKA).emoji(Emoji.fromFormatted("<:9p:1118270604940943420>")).build();
    Card p10 = Card.builder().name("Пика 3").value(3).suit(Suit.PIKA).emoji(Emoji.fromFormatted("<:10p:1118270618274644138>")).build();
    Card jp = Card.builder().name("Валет пика").value(2).suit(Suit.PIKA).emoji(Emoji.fromFormatted("<:jp:1118270451165180045>")).build();
    Card qp = Card.builder().name("Королева пика").value(3).suit(Suit.PIKA).emoji(Emoji.fromFormatted("<:qp:1118270491950600302>")).build();
    Card kp = Card.builder().name("Король пика").value(4).suit(Suit.PIKA).emoji(Emoji.fromFormatted("<:kp:1118270468353445898>")).build();
    Card ap = Card.builder().name("Туз пика").value(11).suit(Suit.PIKA).emoji(Emoji.fromFormatted("<:ap:1118270621906911364>")).build();

    Card b2 = Card.builder().name("Бубна 2").value(2).suit(Suit.BUBNA).emoji(Emoji.fromFormatted("<:2b:1118446796272836608>")).build();
    Card b3 = Card.builder().name("Бубна 3").value(3).suit(Suit.BUBNA).emoji(Emoji.fromFormatted("<:3b:1118270504575451236>")).build();
    Card b4 = Card.builder().name("Бубна 4").value(3).suit(Suit.BUBNA).emoji(Emoji.fromFormatted("<:4b:1118447064817340427>")).build();
    Card b5 = Card.builder().name("Бубна 5").value(3).suit(Suit.BUBNA).emoji(Emoji.fromFormatted("<:5b:1118270520312479795>")).build();
    Card b6 = Card.builder().name("Бубна 6").value(3).suit(Suit.BUBNA).emoji(Emoji.fromFormatted("<:6b:1118270537890812056>")).build();
    Card b7 = Card.builder().name("Бубна 7").value(3).suit(Suit.BUBNA).emoji(Emoji.fromFormatted("<:7b:1118270559529222204>")).build();
    Card b8 = Card.builder().name("Бубна 8").value(8).suit(Suit.BUBNA).emoji(Emoji.fromFormatted("<:8b:1118270575354318978>")).build();
    Card b9 = Card.builder().name("Бубна 9").value(3).suit(Suit.BUBNA).emoji(Emoji.fromFormatted("<:9b:1118270594698465420>")).build();
    Card b10 = Card.builder().name("Бубна 3").value(3).suit(Suit.BUBNA).emoji(Emoji.fromFormatted("<:10b:1118270609567269015>")).build();
    Card jb = Card.builder().name("Валет бубна").value(2).suit(Suit.BUBNA).emoji(Emoji.fromFormatted("<:jb:1118270439370797147>")).build();
    Card qb = Card.builder().name("Королева бубна").value(3).suit(Suit.BUBNA).emoji(Emoji.fromFormatted("<:qb:1118270476616212501>")).build();
    Card kb = Card.builder().name("Король бубна").value(4).suit(Suit.BUBNA).emoji(Emoji.fromFormatted("<:kb:1118270457175613440>")).build();
    Card ab = Card.builder().name("Туз бубна").value(11).suit(Suit.BUBNA).emoji(Emoji.fromFormatted("<:tb:1118447619421786222>")).build();

    Card k2 = Card.builder().name("Креста 2").value(2).suit(Suit.CHRESTA).emoji(Emoji.fromFormatted("<:2k:1118448327177015356>")).build();
    Card k3 = Card.builder().name("Креста 3").value(3).suit(Suit.CHRESTA).emoji(Emoji.fromFormatted("<:3k:1118270510023852032>")).build();
    Card k4 = Card.builder().name("Креста 4").value(3).suit(Suit.CHRESTA).emoji(Emoji.fromFormatted("<:4k:1118449647137067060>")).build();
    Card k5 = Card.builder().name("Креста 5").value(3).suit(Suit.CHRESTA).emoji(Emoji.fromFormatted("<:5k:1118270529804185700>")).build();
    Card k6 = Card.builder().name("Креста 6").value(3).suit(Suit.CHRESTA).emoji(Emoji.fromFormatted("<:6k:1118270551362904074>")).build();
    Card k7 = Card.builder().name("Креста 7").value(3).suit(Suit.CHRESTA).emoji(Emoji.fromFormatted("<:7k:1118270567699710074>")).build();
    Card k8 = Card.builder().name("Креста 8").value(8).suit(Suit.CHRESTA).emoji(Emoji.fromFormatted("<:8k:1118270588209873047>")).build();
    Card k9 = Card.builder().name("Креста 9").value(3).suit(Suit.CHRESTA).emoji(Emoji.fromFormatted("<:9k:1118270600725668031>")).build();
    Card k10 = Card.builder().name("Креста 3").value(3).suit(Suit.CHRESTA).emoji(Emoji.fromFormatted("<:10k:1118270614692696114>")).build();
    Card jk = Card.builder().name("Валет креста").value(2).suit(Suit.CHRESTA).emoji(Emoji.fromFormatted("<:jk:1118450335430754314>")).build();
    Card qk = Card.builder().name("Королева креста").value(3).suit(Suit.CHRESTA).emoji(Emoji.fromFormatted("<:qk:1118270485508145162>")).build();
    Card kk = Card.builder().name("Король креста").value(4).suit(Suit.CHRESTA).emoji(Emoji.fromFormatted("<:kk:1118270463261560965>")).build();
    Card ak = Card.builder().name("Туз креста").value(11).suit(Suit.CHRESTA).emoji(Emoji.fromFormatted("<:ak:1118270632069701683>")).build();
}
