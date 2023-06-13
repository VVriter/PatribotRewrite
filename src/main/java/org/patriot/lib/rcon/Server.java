package org.patriot.lib.rcon;

import com.github.koraktor.steamcondenser.steam.servers.SourceServer;
import lombok.Builder;
import lombok.Getter;
import org.json.JSONObject;

import java.net.InetAddress;

@Builder
public class Server {
    @Getter private String name;
    @Getter private String ip;
    @Getter private String pass;
    @Getter private int port;

    public String exec(String command) throws Exception {
        InetAddress adress = InetAddress.getByName(ip);
        SourceServer server = new SourceServer(adress, port);
        server.rconAuth(pass);
        return server.rconExec(command);
    }

    public JSONObject toJson() {
        return new JSONObject().put("name", name).put("ip", ip).put("pass", pass).put("port", port);
    }
}
