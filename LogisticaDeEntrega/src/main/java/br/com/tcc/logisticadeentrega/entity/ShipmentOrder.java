package br.com.tcc.logisticadeentrega.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ShipmentOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderId;
    private Double price;
    @Type(type = "json")
    @Column( columnDefinition = "json" )
    private EventState events;

    public Integer getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public EventState getEvents() {
        if (Objects.isNull(events)) {
            this.events = new EventState();
        }
        return events;
    }

    public void setEvents(EventState events) {
        this.events = events;
    }
}
