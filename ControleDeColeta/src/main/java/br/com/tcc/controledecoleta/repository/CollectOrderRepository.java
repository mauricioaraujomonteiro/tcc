package br.com.tcc.controledecoleta.repository;

import br.com.tcc.controledecoleta.entity.CollectOrder;
import org.springframework.data.repository.CrudRepository;

public interface CollectOrderRepository extends CrudRepository<CollectOrder, Integer> {
}
