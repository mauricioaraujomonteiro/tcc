package br.com.tcc.tabeladefrete.component;

import br.com.tcc.tabeladefrete.model.Rate;
import br.com.tcc.tabeladefrete.repository.RateRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RateReadComponent {

    private final RateRepository rateRepository;

    @Autowired
    public RateReadComponent(final RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public List<Rate> getAll() {
        return Lists.newArrayList(rateRepository.findAll());
    }
}
