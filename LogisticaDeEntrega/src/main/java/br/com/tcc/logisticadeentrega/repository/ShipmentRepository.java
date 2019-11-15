package br.com.tcc.logisticadeentrega.repository;

import br.com.tcc.logisticadeentrega.entity.ShipmentOrder;
import org.springframework.data.repository.CrudRepository;

public interface ShipmentRepository extends CrudRepository<ShipmentOrder, Integer> {
}
