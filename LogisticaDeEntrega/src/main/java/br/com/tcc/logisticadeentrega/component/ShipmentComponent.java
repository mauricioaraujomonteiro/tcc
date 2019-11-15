package br.com.tcc.logisticadeentrega.component;

import br.com.tcc.logisticadeentrega.dto.OrderRequestDTO;
import br.com.tcc.logisticadeentrega.entity.Event;
import br.com.tcc.logisticadeentrega.entity.OrderState;
import br.com.tcc.logisticadeentrega.entity.ShipmentOrder;
import br.com.tcc.logisticadeentrega.queue.listener.ShipmentEventMessageProducer;
import br.com.tcc.logisticadeentrega.queue.message.ShiptmentNotificationMessage;
import br.com.tcc.logisticadeentrega.repository.ShipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Component
public class ShipmentComponent {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentEventMessageProducer shipmentEventMessage;
    private final JmsTemplate jmsTemplateTopic;

    private Logger LOG = LoggerFactory.getLogger(ShipmentComponent.class);
    @Autowired
    public ShipmentComponent(final ShipmentRepository shipmentRepository, final ShipmentEventMessageProducer shipmentEventMessage, final JmsTemplate jmsTemplateTopic) {
        this.shipmentRepository = shipmentRepository;
        this.shipmentEventMessage = shipmentEventMessage;
        this.jmsTemplateTopic = jmsTemplateTopic;
    }

    public ShipmentOrder create(final OrderRequestDTO order) {
        try {
            final Event event = createEvent(order);

            ShipmentOrder shipmentOrder = new ShipmentOrder();
            shipmentOrder.getEvents().add(event);
            shipmentOrder.setOrderId(order.getOrderId());
            shipmentOrder.setPrice(order.getPrice());

            final ShipmentOrder saved = this.shipmentRepository.save(shipmentOrder);
            return saved;
        } catch (Exception ex) {
            LOG.info("could not save the order: {}", ex);
            return null;
        }
    }



    public void update(final OrderRequestDTO order) {

        final Optional<ShipmentOrder> optShipmentOrder = shipmentRepository.findById(order.getId());

        if (!optShipmentOrder.isPresent()) {
            throw new IllegalArgumentException(String.format("Could not find the shipment order with id %s", order.getId()));
        }

        final ShipmentOrder shipmentOrderToUpdate = optShipmentOrder.get();
        final Event event = createEvent(order);
        ShiptmentNotificationMessage shiptmentNotificationMessage = new ShiptmentNotificationMessage();
        shiptmentNotificationMessage.setCellphone("CELLPHONE");
        shiptmentNotificationMessage.setEmail("email");
        shiptmentNotificationMessage.setName("Mauricio");
        shiptmentNotificationMessage.setOrderState(event.getOrderState());

        shipmentOrderToUpdate.getEvents().add(event);

        shipmentRepository.save(shipmentOrderToUpdate);
        shipmentEventMessage.sendMessage(shiptmentNotificationMessage);

    }

    private Event createEvent(final OrderRequestDTO order) {
        OrderState orderState = OrderState.get(order.getStatus());

        if (Objects.isNull(orderState)) {
            throw new IllegalArgumentException(String.format("OrderState not found: %s", order.getStatus()));
        }
        final Event event = new Event();
        event.setOrderState(orderState);
        event.setDate(new Date());
        return event;
    }
}
