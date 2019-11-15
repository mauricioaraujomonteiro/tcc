package br.com.tcc.logisticadeentrega.queue.listener;

import br.com.tcc.logisticadeentrega.queue.message.ShiptmentNotificationMessage;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShipmentEventMessageConsumer {

    public static final String SHIPMENT_STATE = "shipment_state";
    private JmsTemplate jmsTemplateTopic;
    private static final Logger LOG = LoggerFactory.getLogger(ShipmentEventMessageConsumer.class);

    @Autowired
    public ShipmentEventMessageConsumer(final JmsTemplate jmsTemplate) {
        this.jmsTemplateTopic = jmsTemplate;
    }

    @JmsListener(destination = SHIPMENT_STATE, containerFactory = "jmsFactoryTopic")
    public void onReceiverQueue(String message) {
        ShiptmentNotificationMessage shiptmentNotificationMessage = new Gson().fromJson(message, ShiptmentNotificationMessage.class);
        System.out.println(Thread.currentThread().getId());
        LOG.info(String.format("Send notification to the customer %s", shiptmentNotificationMessage));
    }



}
