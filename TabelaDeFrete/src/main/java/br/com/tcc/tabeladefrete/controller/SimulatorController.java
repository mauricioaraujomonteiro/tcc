package br.com.tcc.tabeladefrete.controller;

import br.com.tcc.tabeladefrete.dto.SimulateDTO;
import br.com.tcc.tabeladefrete.service.EstimatedPrice;
import br.com.tcc.tabeladefrete.service.SimulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/simulator")
@CrossOrigin
@RestController
public class SimulatorController {

    private final SimulatorService simulatorService;

    @Autowired
    public SimulatorController(final SimulatorService simulatorService) {
        this.simulatorService = simulatorService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> simulate(@RequestBody SimulateDTO simulateDTO) {

        List<EstimatedPrice> simulationResult = simulatorService.simulate(simulateDTO);
        return new ResponseEntity<>(simulationResult, HttpStatus.OK);
    }

}
