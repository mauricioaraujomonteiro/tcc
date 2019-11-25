package br.com.tcc.controledecoleta.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

public abstract class Notification<T> {

    private Logger LOG = LoggerFactory.getLogger(Notification.class);

    public void send(T t) {
        LOG.info (String.format("Sending request to: %s", getURL()));
    }

    public abstract String getURL();

    public abstract RequestMethod getRequestMethod();

    public abstract Map<String, String> getHeader();
}
