package br.com.tcc.tabeladefrete.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity(name = "tabela_de_frete")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double priceByDistance;
    @Enumerated(EnumType.STRING)
    private DistanceType distanceType;
    private Double weigth;
    @OneToOne
    @NotFound(action= NotFoundAction.EXCEPTION)
    private VehicleType vehicleType;
    private Double tax;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer version;

    public Integer getId() {
        return id;
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
}
