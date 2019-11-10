package br.com.tcc.tabeladefrete.service;

import br.com.tcc.tabeladefrete.dto.SimulateDTO;
import br.com.tcc.tabeladefrete.model.Rate;
import br.com.tcc.tabeladefrete.model.VehicleType;
import br.com.tcc.tabeladefrete.repository.RateRepository;
import br.com.tcc.tabeladefrete.repository.VehicleRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SimulatorService {
    private final RateRepository rateRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public SimulatorService(final RateRepository rateRepository, final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
        this.rateRepository = rateRepository;
    }

    public List<EstimatedPrice> simulate(SimulateDTO simulateDTO) {
        List<VehicleType> vehiclesvehicles = Lists.newArrayList(vehicleRepository.findAll());
        return findBestOption(vehiclesvehicles, simulateDTO);
}

    private List<EstimatedPrice> findBestOption(List<VehicleType> vehicles, SimulateDTO simulateDTO) {
        ArrayList<Rate> rates = Lists.newArrayList(rateRepository.findAll());
        final Random randomDistance =  new Random(System.currentTimeMillis());

        Map<VehicleType, Integer> vehicleVsQuantity = new TreeMap<>(new VehicleComparator());
        vehicles.stream().forEach(vehicle -> {

            Integer count = (int) (simulateDTO.getWeight() / vehicle.getMaxWeight());
            if (count == 0) {
                count = 1;
            }
            vehicleVsQuantity.put(vehicle, count);
        });

        int distance = randomDistance.nextInt(300);
        List<EstimatedPrice> estimatedPrices = new ArrayList<>();
        vehicleVsQuantity.forEach((vehicle, quantity) -> {

            Optional<Rate> foundRate = rates.stream()
                    .filter(rate -> rate.getVehicleType().getId() == vehicle.getId())
                    .findFirst();

            if (foundRate.isPresent()) {
                Double priceByDistance = foundRate.get().getPriceByDistance();
                Double estimatedValue = (priceByDistance * distance) * quantity;

                EstimatedPrice estimatedPrice = new EstimatedPrice();
                estimatedPrice.setEstimatedPrice(estimatedValue);
                estimatedPrice.setVehicleType(vehicle);
                estimatedPrice.setNumberOfVehicle(quantity);
                estimatedPrice.setDistance(distance);
                estimatedPrices.add(estimatedPrice);
            }

        });

        estimatedPrices.sort((EstimatedPrice p1, EstimatedPrice p2) -> p1.getEstimatedPrice() < p2.getEstimatedPrice() ? -1 :1);
        return estimatedPrices;
    }

}
