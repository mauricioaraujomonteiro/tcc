package br.com.tcc.controledecoleta.client.billing;

import br.com.tcc.controledecoleta.client.Notification;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.Map;

public class BillingClient extends Notification<BillingRequest> {

    @Override
    public String getURL() {
        return "http://localhost:9190/billing";
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.POST;
    }

    @Override
    public Map<String, String> getHeader() {
        return Collections.emptyMap();
    }
}
