package br.com.tcc.tabeladefrete.controller;

import br.com.tcc.tabeladefrete.component.RateCreateComponent;
import br.com.tcc.tabeladefrete.component.RateReadComponent;
import br.com.tcc.tabeladefrete.dto.RateDTO;
import br.com.tcc.tabeladefrete.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/rate")
public class RateController {

    private final RateReadComponent rateReadComponent;
    private final RateCreateComponent rateCreateComponent;

    @Autowired
    public RateController(final RateReadComponent rateReadComponent, final RateCreateComponent rateCreateComponent) {
        this.rateReadComponent = rateReadComponent;
        this.rateCreateComponent = rateCreateComponent;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getRates() {
        return new ResponseEntity<>(rateReadComponent.getAll(), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> createRate(@RequestBody RateDTO rateDTO) {

        Rate rate = rateDTO.convertToRate();
        Rate savedRate = rateCreateComponent.save(rate);

        if (null == savedRate) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(savedRate, HttpStatus.CREATED);
    }

}
