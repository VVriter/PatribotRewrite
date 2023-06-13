package org.patriot;

public class Test implements Constants{
    public static void main(String[] args) throws Exception {
        String res = awp_1.exec("sm_addvip \"STEAM_1:1:572541173\" \"[Moderator]\" \"60000\"");
        System.out.println(res);
    }
}
