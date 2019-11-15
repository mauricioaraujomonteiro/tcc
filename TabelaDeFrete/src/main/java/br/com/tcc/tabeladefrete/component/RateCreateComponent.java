package br.com.tcc.tabeladefrete.component;

import br.com.tcc.tabeladefrete.model.Rate;
import br.com.tcc.tabeladefrete.model.VehicleType;
import br.com.tcc.tabeladefrete.repository.RateRepository;
import br.com.tcc.tabeladefrete.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RateCreateComponent {

    private static final Logger LOG = LoggerFactory.getLogger(RateCreateComponent.class);
    private final RateRepository rateRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public RateCreateComponent(final RateRepository rateRepository, final VehicleRepository vehicleRepository) {
        this.rateRepository = rateRepository;
        this.vehicleRepository = vehicleRepository;
    }
    public Rate save(Rate rate) {
        Integer vehicleId = Integer.valueOf(rate.getVehicleType().getId());
        Optional<VehicleType> foundVehicle =
                vehicleRepository.findById(vehicleId);

        if (!foundVehicle.isPresent()) {
            throw new IllegalArgumentException(String.format("Could not find the vehicle with id: %s", vehicleId));
        }

        try {
            return this.rateRepository.save(rate);
        } catch (Exception ex){
            LOG.info("Could not save rate: {}", ex);
            return null;
        }
    }
}
