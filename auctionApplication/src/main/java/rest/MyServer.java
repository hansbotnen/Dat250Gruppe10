package rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("")
public class MyServer extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> map = new HashMap<>();
        map.put("jersey.config.server.disableMoxyJson", true);
        return map;
    }

}