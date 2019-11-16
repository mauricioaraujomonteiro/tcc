package br.com.tcc.logisticadeentrega.controller;

import br.com.tcc.logisticadeentrega.component.ShipmentComponent;
import br.com.tcc.logisticadeentrega.dto.OrderRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/shipment")
@CrossOrigin
@RestController
public class ShipmentController {

    private final ShipmentComponent shipmentComponent;

    @Autowired
    public  ShipmentController(final ShipmentComponent shipmentComponent) {
        this.shipmentComponent = shipmentComponent;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO order) {

        shipmentComponent.create(order);
        return null;
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateOrder(@RequestBody OrderRequestDTO order) {

        shipmentComponent.update(order);
        return null;
    }
}
