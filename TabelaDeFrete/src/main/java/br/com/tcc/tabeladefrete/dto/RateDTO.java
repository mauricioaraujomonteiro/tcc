package br.com.tcc.tabeladefrete.dto;

import br.com.tcc.tabeladefrete.model.DistanceType;
import br.com.tcc.tabeladefrete.model.Rate;
import br.com.tcc.tabeladefrete.model.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class RateDTO {
    private Integer id;
    private Double priceByDistance;
    private DistanceType distanceType;
    private Double weigth;
    private VehicleType vehicleType;
    private Double tax;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Rate convertToRate() {
        Rate rate = new Rate();

        rate.setPriceByDistance(getPriceByDistance());
        rate.setDistanceType(getDistanceType());
        rate.setTax(getTax());
        rate.setPriceByDistance(getPriceByDistance());
        rate.setVehicleType(getVehicleType());
        rate.setWeigth(getWeigth());

        return rate;
    }
}
