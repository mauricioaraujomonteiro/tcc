package br.com.tcc.logisticadeentrega.entity;

public enum OrderState {
    SHIPPED("SHIPPED"),
    ORDERED("ORDERED"),
    COLLECTED("COLLECTED"),
    DELIVERED("DELIVERED");

    private String state;

    OrderState(String state) {
        this.state = state;
    }

    public static OrderState get(String state) {
        return OrderState.valueOf(state);
    }
}
