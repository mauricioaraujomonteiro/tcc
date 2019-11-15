package br.com.tcc.tabeladefrete.dto;

import javax.validation.constraints.NotNull;

public class SimulateDTO {
    @NotNull
    private Double weight;
    @NotNull
    private String zipCodeFrom;
    @NotNull
    private String zipCodeTo;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getZipCodeFrom() {
        return zipCodeFrom;
    }

    public void setZipCodeFrom(String zipCodeFrom) {
        this.zipCodeFrom = zipCodeFrom;
    }

    public String getZipCodeTo() {
        return zipCodeTo;
    }

    public void setZipCodeTo(String zipCodeTo) {
        this.zipCodeTo = zipCodeTo;
    }
}
