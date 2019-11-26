package br.com.tcc.tabeladefrete.service;

import br.com.tcc.tabeladefrete.dto.SimulateDTO;
import br.com.tcc.tabeladefrete.model.Rate;
import br.com.tcc.tabeladefrete.model.VehicleType;
import br.com.tcc.tabeladefrete.repository.RateRepository;
import br.com.tcc.tabeladefrete.repository.VehicleRepository;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class SimulatorService {
    private final RateRepository rateRepository;
    private final VehicleRepository vehicleRepository;
    private static Cache cache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();
    private static final String CACHE_KEY_RATE = "RATE";
    private static final String CACHE_KEY_VEHICLE = "VEHICLE";

    @Autowired
    public SimulatorService(final RateRepository rateRepository, final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
        this.rateRepository = rateRepository;
    }

    public List<EstimatedPrice> simulate(SimulateDTO simulateDTO) {
        LoggerFactory.getLogger(SimulatorService.class).info(System.getProperty("server.port"));
        if (null == cache.getIfPresent(CACHE_KEY_VEHICLE)) {
            List<VehicleType> vehicles = Lists.newArrayList(vehicleRepository.findAll());
            cache.put(CACHE_KEY_VEHICLE, vehicles);
        }

        final List<VehicleType> vehicles = (List<VehicleType>)cache.getIfPresent(CACHE_KEY_VEHICLE);
        if (vehicles.isEmpty()) {
            return null;
        }
        return findBestOption(vehicles, simulateDTO);
}

    private List<EstimatedPrice> findBestOption(List<VehicleType> vehicles, SimulateDTO simulateDTO) {
        if (null == cache.getIfPresent(CACHE_KEY_RATE)) {
            ArrayList<Rate> rates = Lists.newArrayList(rateRepository.findAll());
            cache.put(CACHE_KEY_RATE, rates);
        }
        final List<Rate> rates = (List<Rate>)cache.getIfPresent(CACHE_KEY_RATE);

        final Random randomDistance =  new Random(System.currentTimeMillis());

        Map<VehicleType, Integer> vehicleVsQuantity = new TreeMap<>(new VehicleComparator());
        vehicles.stream().forEach(vehicle -> {

            double count = Math.ceil(simulateDTO.getWeight() / vehicle.getMaxWeight());

            vehicleVsQuantity.put(vehicle, (int) count);
        });

        int distance = randomDistance.nextInt(300);
        List<EstimatedPrice> estimatedPrices = new ArrayList<>();
        vehicleVsQuantity.forEach((vehicle, quantity) -> {

            final List<Rate> collect = rates.stream()
                    .filter(rate -> rate.getVehicleType().getId() == vehicle.getId())
                    .collect(Collectors.toList());


            collect.forEach(rate -> {
                Double priceByDistance = rate.getPriceByDistance();
                Double estimatedValue = (priceByDistance * distance) * quantity;

                EstimatedPrice estimatedPrice = new EstimatedPrice();
                estimatedPrice.setRateId(rate.getId());
                estimatedPrice.setEstimatedPrice(estimatedValue);
                estimatedPrice.setVehicleType(vehicle);
                estimatedPrice.setNumberOfVehicle(quantity);
                estimatedPrice.setDistance(distance);
                estimatedPrice.setOrigin(Origin.INTERNAL.name());
                estimatedPrices.add(estimatedPrice);
            });


        });

        estimatedPrices.sort((EstimatedPrice p1, EstimatedPrice p2) -> p1.getEstimatedPrice() < p2.getEstimatedPrice() ? -1 :1);
        return estimatedPrices;
    }

    public void compareAgainstPartner() {
        //todo - check if the partner has a better price.
    }

}
