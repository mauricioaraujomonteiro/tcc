package br.com.tcc.controledecoleta.client.billing;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;

public class BillingRequest {
    private String orderId;
    private Double price;
    private Date orderDate;

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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("orderId", orderId)
                .append("price", price)
                .append("orderDate", orderDate)
                .toString();
    }
}
