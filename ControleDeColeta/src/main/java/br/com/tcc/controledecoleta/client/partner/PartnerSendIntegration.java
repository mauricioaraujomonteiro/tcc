package br.com.tcc.controledecoleta.client.partner;

import br.com.tcc.controledecoleta.entity.CollectOrder;

public interface PartnerSendIntegration {
    PartnerResponseCommon send(CollectOrder collectOrder);
}
