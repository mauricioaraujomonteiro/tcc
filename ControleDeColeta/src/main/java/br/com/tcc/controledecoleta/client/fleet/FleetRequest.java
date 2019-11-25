package br.com.tcc.controledecoleta.client.fleet;

import br.com.tcc.controledecoleta.entity.ShipmentType;
import br.com.tcc.controledecoleta.entity.VehicleType;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;

public class FleetRequest {
    private String orderId;
    private String fromPostcode;
    private String toPostcode;
    private Date orderDate;
    private br.com.tcc.controledecoleta.entity.VehicleType VehicleType;
    private String vehicleIdentifier;
    private String driverName;
    private ShipmentType shipmentType;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("orderId", orderId)
                .append("fromPostcode", fromPostcode)
                .append("toPostcode", toPostcode)
                .append("orderDate", orderDate)
                .append("VehicleType", VehicleType)
                .append("vehicleIdentifier", vehicleIdentifier)
                .append("driverName", driverName)
                .append("shipmentType", shipmentType)
                .toString();
    }
}
