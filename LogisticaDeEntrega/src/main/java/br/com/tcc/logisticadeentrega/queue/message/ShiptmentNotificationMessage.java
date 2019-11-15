package br.com.tcc.logisticadeentrega.queue.message;

import br.com.tcc.logisticadeentrega.entity.OrderState;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ShiptmentNotificationMessage {
    private String name;
    private String email;
    private String cellphone;
    private OrderState orderState;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Override
    public String toString() {
        return "ShiptmentNotificationMessage{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", orderState=" + orderState +
                '}';
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public OrderState getOrderState() {
        return orderState;
    }
}
