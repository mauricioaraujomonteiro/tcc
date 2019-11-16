package br.com.tcc.controledecoleta.controller;

import br.com.tcc.controledecoleta.component.CollectOrderComponent;
import br.com.tcc.controledecoleta.dto.CollectOrderRequestDTO;
import br.com.tcc.controledecoleta.dto.CollectOrderResponseDTO;
import br.com.tcc.controledecoleta.entity.CollectOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/collect")
@CrossOrigin
@RestController
public class CollectController {

    private final CollectOrderComponent collectOrderComponent;

    @Autowired
    public CollectController(final CollectOrderComponent collectOrderComponent) {
        this.collectOrderComponent = collectOrderComponent;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> order(@RequestBody final CollectOrderRequestDTO collectOrderRequest) {
        try {
            final CollectOrder order = collectOrderComponent.create(collectOrderRequest);
            if (null == order) {
                return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
            }
            CollectOrderResponseDTO response = new CollectOrderResponseDTO();
            response.setCreated(order.getOrderDate());
            response.setId(order.getId());
            response.setStatus(order.getStatus());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getCause(), HttpStatus.BAD_GATEWAY);
        }
    }
}
