package br.com.tcc.controledecoleta.component;

import br.com.tcc.controledecoleta.client.Notification;
import br.com.tcc.controledecoleta.client.billing.BillingClient;
import br.com.tcc.controledecoleta.client.billing.BillingRequest;
import br.com.tcc.controledecoleta.client.fleet.FleetClient;
import br.com.tcc.controledecoleta.client.fleet.FleetRequest;
import br.com.tcc.controledecoleta.client.partner.PartnerResponseCommon;
import br.com.tcc.controledecoleta.client.partner.PartnerSendSpec;
import br.com.tcc.controledecoleta.dto.CollectOrderRequestDTO;
import br.com.tcc.controledecoleta.entity.CollectOrder;
import br.com.tcc.controledecoleta.entity.Origin;
import br.com.tcc.controledecoleta.repository.CollectOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.directory.InvalidAttributesException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

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
            CollectOrder collectOrder = createOrder(collectOrderRequestDTO);
            if (collectOrder.getOrigin().equals(Origin.INTERNAL)) collectOrder.setOrderId(generateOrderId());
            final CollectOrder saved = collectOrderRepository.save(collectOrder);

            notifyBilling(saved);
            notifyFleet(saved);

            if (collectOrder.getOrigin().equals(Origin.EXTERNAL)) {
                final Optional<PartnerSendSpec> partner = PartnerSendSpec.getPartner(collectOrderRequestDTO.getPartnerName());
                if (!partner.isPresent()){
                    throw new InvalidAttributesException("Not found an partner with name: "+ collectOrderRequestDTO.getPartnerName());
                }
                final PartnerSendSpec partnerSendSpec = partner.get();
                final PartnerResponseCommon response = partnerSendSpec.getPartnerSendIntegration().send(saved);
                saved.setPartnerName(collectOrderRequestDTO.getPartnerName());
                saved.setOrderId(response.getOrderId());
                collectOrderRepository.save(saved);
            }

            return saved;
        } catch (Exception ex) {
            LOG.error(String.format("Could not save the collect order:  %s", ex));
            return null;
        }
    }

    private void notifyFleet(CollectOrder saved) {
        try {
            Notification notification = new FleetClient();
            notification.send(new FleetRequest());
        } catch (Exception ex) {
            LOG.error(String.format("Could not notify feet system: %s", ex));
        }
    }

    private void notifyBilling(CollectOrder saved) {
        try {
            Notification notification = new BillingClient();
            notification.send(new BillingRequest());
        } catch (Exception ex) {
            LOG.error(String.format("Could not notify billing system: %s", ex));
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

    public String generateOrderId() {

        Random random = new Random(System.currentTimeMillis());
        final int randomInt = random.nextInt();
        Date date = new Date();
        SimpleDateFormat spf = new SimpleDateFormat("yyyymmddhhmmss");
        final String format = spf.format(date);
        return String.format("CO%s%s", format, randomInt);


    }
}
