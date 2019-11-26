package br.com.tcc.controledecoleta.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class CollectOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Origin origin;
    private Date orderDate;
    private String fromPostcode;
    private String toPostcode;
    private Double price;
    @Enumerated(EnumType.STRING)
    private VehicleType VehicleType;
    private String vehicleIdentifier;
    private String driverName;
    @Enumerated(EnumType.STRING)
    private ShipmentType shipmentType;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String partnerName;
    private String orderId;

    public Integer getId() {
        return id;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getFromPostcode() {
        return fromPostcode;
    }

    public void setFromPostcode(String fromPostcode) {
        this.fromPostcode = fromPostcode;
    }

    public String getToPostcode() {
        return toPostcode;
    }

    public void setToPostcode(String toPostcode) {
        this.toPostcode = toPostcode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public br.com.tcc.controledecoleta.entity.VehicleType getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(br.com.tcc.controledecoleta.entity.VehicleType vehicleType) {
        VehicleType = vehicleType;
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

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
