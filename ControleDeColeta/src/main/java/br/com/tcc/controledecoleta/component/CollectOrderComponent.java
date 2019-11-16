package br.com.tcc.controledecoleta.component;

import br.com.tcc.controledecoleta.dto.CollectOrderRequestDTO;
import br.com.tcc.controledecoleta.entity.CollectOrder;
import br.com.tcc.controledecoleta.entity.Origin;
import br.com.tcc.controledecoleta.repository.CollectOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CollectOrderComponent {
    public static final Logger LOG = LoggerFactory.getLogger(CollectOrderComponent.class);
    private final CollectOrderRepository collectOrderRepository;

    @Autowired
    public CollectOrderComponent(final CollectOrderRepository collectOrderRepository) {
        this.collectOrderRepository = collectOrderRepository;
    }

    public CollectOrder create(CollectOrderRequestDTO collectOrderRequestDTO) {
        try {
            CollectOrder collectOrder = null;
            if (collectOrderRequestDTO.getOrigin().equals(Origin.INTERNAL)) {
                collectOrder = createOrder(collectOrderRequestDTO);
            }
            return collectOrderRepository.save(collectOrder);
        } catch (Exception ex) {
            LOG.error(String.format("Could not save the collect order:  %s", ex));
            return null;
        }
    }

    private CollectOrder createOrder(CollectOrderRequestDTO collectOrderRequestDTO) {
        CollectOrder order = new CollectOrder();
        order.setDriverName(collectOrderRequestDTO.getDriverName());
        order.setFromPostcode(collectOrderRequestDTO.getFrom());
        order.setOrderDate(new Date());
        order.setOrigin(collectOrderRequestDTO.getOrigin());
        order.setPrice(collectOrderRequestDTO.getPrice());
        order.setStatus(collectOrderRequestDTO.getStatus());
        order.setToPostcode(collectOrderRequestDTO.getTo());
        order.setVehicleIdentifier(collectOrderRequestDTO.getVehicleIdentifier());
        order.setVehicleType(collectOrderRequestDTO.getVehicleType());
        order.setShipmentType(collectOrderRequestDTO.getShipmentType());
        return  order;
    }
}
