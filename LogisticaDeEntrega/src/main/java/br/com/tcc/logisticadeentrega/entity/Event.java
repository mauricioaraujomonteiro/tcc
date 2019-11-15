package br.com.tcc.logisticadeentrega.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Event {

    private OrderState orderState;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd'T'HH:mm:ss.SSSXXX")
    private Date date;

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
