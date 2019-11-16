package br.com.tcc.controledecoleta.dto;

import br.com.tcc.controledecoleta.entity.*;

public class CollectOrderRequestDTO {

    private Origin origin;
    private String from;
    private String to;
    private Double price;
    private VehicleType vehicleType;
    private String vehicleIdentifier;
    private String driverName;
    private ShipmentType shipmentType;
    private Status status;

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public br.com.tcc.controledecoleta.entity.VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(br.com.tcc.controledecoleta.entity.VehicleType vehicleType) {
       this.vehicleType = vehicleType;
    }

    public String getVehicleIdentifier() {
        return vehicleIdentifier;
    }

    public void setVehicleIdentifier(String vehicleIdentifier) {
        this.vehicleIdentifier = vehicleIdentifier;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public ShipmentType getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShipmentType shipmentType) {
        this.shipmentType = shipmentType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
