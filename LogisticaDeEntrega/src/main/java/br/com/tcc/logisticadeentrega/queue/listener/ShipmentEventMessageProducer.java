package br.com.tcc.logisticadeentrega.queue.listener;

import br.com.tcc.logisticadeentrega.queue.message.ShiptmentNotificationMessage;
import com.google.gson.Gson;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShipmentEventMessageProducer {

    public static final String SHIPMENT_STATE = "shipment_state";
    private JmsTemplate jmsTemplateTopic;
    private static final Logger LOG = LoggerFactory.getLogger(ShipmentEventMessageProducer.class);

    @Autowired
    public ShipmentEventMessageProducer(final JmsTemplate jmsTemplateTopic) {
        this.jmsTemplateTopic = jmsTemplateTopic;
    }

    public void sendMessage(ShiptmentNotificationMessage message) {
        this.jmsTemplateTopic.convertAndSend(new ActiveMQTopic(SHIPMENT_STATE), new Gson().toJson(message));
    }

}
