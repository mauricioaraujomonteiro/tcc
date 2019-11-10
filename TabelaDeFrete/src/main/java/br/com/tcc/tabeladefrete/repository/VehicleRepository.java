package br.com.tcc.tabeladefrete.repository;

import br.com.tcc.tabeladefrete.model.VehicleType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRepository extends CrudRepository<VehicleType, Integer> {
    List<VehicleType> findByMaxWeight(Double maxWeight);
}
