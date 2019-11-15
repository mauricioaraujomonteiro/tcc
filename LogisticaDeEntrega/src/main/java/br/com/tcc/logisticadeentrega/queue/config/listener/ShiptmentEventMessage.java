package br.com.tcc.logisticadeentrega.queue.config.listener;

import br.com.tcc.logisticadeentrega.queue.config.message.ShiptmentNotificationMessage;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShiptmentEventMessage implements ApplicationRunner {

    private JmsTemplate jmsTemplateTopic;
    private static final Logger LOG = LoggerFactory.getLogger(ShiptmentEventMessage.class);

    @Autowired
    public ShiptmentEventMessage(final JmsTemplate jmsTemplate) {
        this.jmsTemplateTopic = jmsTemplate;
    }
    @JmsListener(destination = "shipment_state", containerFactory = "jmsFactoryTopic")
    public void onReceiverQueue(String message) {
        ShiptmentNotificationMessage shiptmentNotificationMessage = new Gson().fromJson(message, ShiptmentNotificationMessage.class);
        System.out.println(Thread.currentThread().getId());
        LOG.info(String.format("Send notification to the customer %s", shiptmentNotificationMessage));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jmsTemplateTopic.convertAndSend("shipment_state", "{user: 'wolmir', usando: 'tópico'}");
    }
}
