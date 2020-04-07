package br.com.tcc.tabeladefrete.dto;

import br.com.tcc.tabeladefrete.model.DistanceType;
import br.com.tcc.tabeladefrete.model.Rate;
import br.com.tcc.tabeladefrete.model.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties
public class RateDTO {

    @NotNull
    private Double priceByDistance;
    @NotNull
    private DistanceType distanceType;
    @NotNull
    private Double weigth;
    @NotNull
    private VehicleType vehicleType;
    @NotNull
    private Double tax;

    public Double getPriceByDistance() {
        return priceByDistance;
    }

    public void setPriceByDistance(Double priceByDistance) {
        this.priceByDistance = priceByDistance;
    }

    public DistanceType getDistanceType() {
        return distanceType;
    }

    public void setDistanceType(DistanceType distanceType) {
        this.distanceType = distanceType;
    }

    public Double getWeigth() {
        return weigth;
    }

    public void setWeigth(Double weigth) {
        this.weigth = weigth;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    @JsonIgnore
    public Rate convertToRate() {
        Rate rate = new Rate();
        rate.setPriceByDistance(getPriceByDistance());
        rate.setDistanceType(getDistanceType());
        rate.setTax(getTax());
        rate.setPriceByDistance(getPriceByDistance());
        rate.setVehicleType(getVehicleType());

        return rate;
    }
}
