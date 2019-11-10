package br.com.tcc.tabeladefrete.component;

import br.com.tcc.tabeladefrete.model.Rate;
import br.com.tcc.tabeladefrete.repository.RateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RateCreateComponent {

    private static final Logger LOG = LoggerFactory.getLogger(RateCreateComponent.class);
    private final RateRepository rateRepository;

    @Autowired
    public RateCreateComponent(final RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }
    public Rate save(Rate rate) {
        try {
            return this.rateRepository.save(rate);
        } catch (Exception ex){
            LOG.info("Could not save rate: {}", ex);
            return null;
        }
    }
}
