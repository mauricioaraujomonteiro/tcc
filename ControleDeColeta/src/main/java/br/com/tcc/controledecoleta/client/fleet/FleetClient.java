package br.com.tcc.controledecoleta.client.fleet;

import br.com.tcc.controledecoleta.client.Notification;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.Map;

public class FleetClient extends Notification<FleetRequest> {

    @Override
    public String getURL() {
        return "http://localhost:9190/feet";
    }

    public RequestMethod getRequestMethod() {
        return RequestMethod.POST;
    }

    public Map<String, String> getHeader() {
        return Collections.emptyMap();
    }
}
