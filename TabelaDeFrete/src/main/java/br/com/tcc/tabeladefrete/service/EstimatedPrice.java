package br.com.tcc.tabeladefrete.service;

import br.com.tcc.tabeladefrete.model.VehicleType;

public class EstimatedPrice {
    private Double estimatedPrice;
    private VehicleType vehicleType;
    private Integer numberOfVehicle;
    private Integer distance;
    private Integer rateId;
    private String origin;

    public Double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(Double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getNumberOfVehicle() {
        return numberOfVehicle;
    }

    public void setNumberOfVehicle(Integer numberOfVehicle) {
        this.numberOfVehicle = numberOfVehicle;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setRateId(Integer id) {
        this.rateId = id;
    }

    public Integer getRateId() {
        return rateId;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }
}
