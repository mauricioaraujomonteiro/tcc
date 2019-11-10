package br.com.tcc.tabeladefrete.service;

import br.com.tcc.tabeladefrete.model.VehicleType;

import java.util.Comparator;

public class VehicleComparator implements Comparator<VehicleType> {
    @Override
    public int compare(VehicleType o1, VehicleType o2) {
        return o1.getMaxWeight() < o2.getMaxWeight() ? 1 : 2;
    }
}
